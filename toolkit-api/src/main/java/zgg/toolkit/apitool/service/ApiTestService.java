package zgg.toolkit.apitool.service;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.apitool.model.vo.GroupVo;
import zgg.toolkit.apitool.model.vo.MethodVo;
import zgg.toolkit.apitool.model.vo.ParameterVo;
import zgg.toolkit.core.exception.BaseException;
import zgg.toolkit.core.utils.HelpUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by zgg on 2018/11/19
 */
@Service
public class ApiTestService {

    /**
     * 自动获取所有接口
     */
    public List<GroupVo> findApi() {
        ClassPathScanningCandidateComponentProvider scan = new ClassPathScanningCandidateComponentProvider(false);
        scan.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        Set<BeanDefinition> beanDefinitionSet = scan.findCandidateComponents("zgg.toolkit.apitool.**");

        Map<String, GroupVo> groups = new HashMap<>(16);

        for (BeanDefinition beanDefinition : beanDefinitionSet) {
            Class<?> entityClass;
            try {
                entityClass = ClassUtils.getClass(beanDefinition.getBeanClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new BaseException(e.getMessage());
            }
            RequestMapping requestMapping = entityClass.getAnnotation(RequestMapping.class);
            if (requestMapping == null) {
                continue;
            }
            String baseUrl = requestMapping.value()[0];

            // class上的@RequestMapping name值一样的分为一组,若没有就用class名
            String rmName = requestMapping.name();
            String className = entityClass.getSimpleName();
            String groupName = HelpUtils.isBlank(rmName) ? className : rmName;

            List<MethodVo> mvos = this.findMethodVo(entityClass, baseUrl);
            if (!groups.containsKey(groupName)) {
                GroupVo gvo = new GroupVo();
                gvo.setGroupName(groupName);
                gvo.setMethods(mvos);
                groups.put(groupName, gvo);
            } else {
                GroupVo groupVo = groups.get(groupName);
                groupVo.getMethods().addAll(mvos);
            }
        }
        return new ArrayList<>(groups.values());
    }

    private List<MethodVo> findMethodVo(Class<?> clazz, String baseUrl) {
        List<MethodVo> methodVoList = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping == null) {
                continue;
            }
            // api名称
            String apiName = HelpUtils.isBlank(requestMapping.name()) ? method.getName() : requestMapping.name();
            // api地址
            String fullUrl = baseUrl;
            String partUrl = requestMapping.value()[0];
            if (partUrl.startsWith("/")) {
                fullUrl += partUrl;
            }
            if (HelpUtils.isNotBlank(partUrl) && !partUrl.startsWith("/")) {
                fullUrl = fullUrl + "/" + partUrl;
            }
            // 请求方法
            RequestMethod requestMethod = (requestMapping.method().length > 0) ? (requestMapping.method()[0]) : RequestMethod.POST;

            MethodVo methodVo = new MethodVo();
            methodVo.setName(apiName);
            methodVo.setUrl(fullUrl);
            methodVo.setRequestMethod(requestMethod);
            methodVo.setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            // 请求参数
            List<ParameterVo> params = this.findParameterVos(method, methodVo);
            methodVo.setParameters(params);

            methodVoList.add(methodVo);
        }
        return methodVoList;
    }

    private List<ParameterVo> findParameterVos(Method method, MethodVo methodVo) {
        Class<?>[] paramTypes = method.getParameterTypes();
        Parameter[] parameters = method.getParameters();
        // 获取请求参数变量名称
        LocalVariableTableParameterNameDiscoverer paramNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = paramNameDiscoverer.getParameterNames(method);

        List<ParameterVo> params = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(RequestBody.class)) {
                methodVo.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            }

            Class<?> paramType = paramTypes[i];
            String paramName = paramNames[i];
            List<ParameterVo> pvos = generateParamVo(paramType, paramName);
            params.addAll(pvos);
        }
        return params;
    }

    private List<ParameterVo> generateParamVo(Class clazz, String paramName) {
        ArrayList<ParameterVo> paramVoList = new ArrayList<>();
        if (isPrimaryDataType(clazz)) {
            String value = "";
            try {
                value = clazz.newInstance().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 获取参数验证信息
            StringBuilder validInfo = new StringBuilder();
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                validInfo.append("@");
                validInfo.append(annotation.annotationType().getSimpleName());
                validInfo.append(",");
            }
            ParameterVo vo = new ParameterVo(paramName, value, clazz.getSimpleName(), validInfo.toString());
            paramVoList.add(vo);
        } else {
            // 参数是自定义数据类型,继续调用 generateParamVo 方法，直到是基本数据类型
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                this.generateParamVo(field.getClass(), field.getName());
            }
        }
        return paramVoList;
    }

    public static boolean isPrimaryDataType(Class clazz) {
        List<Class> list = Arrays.asList(
                byte.class, Byte.class,
                short.class, Short.class,
                int.class, Integer.class,
                long.class, Long.class,
                float.class, Float.class,
                double.class, Double.class,
                char.class, Character.class,
                boolean.class, Boolean.class,
                String.class,
                Date.class,
                LocalDateTime.class, LocalDate.class, LocalTime.class
                );
        HashSet<Object> set = new HashSet<>();
        set.addAll(list);
        return set.contains(clazz);
    }


}
