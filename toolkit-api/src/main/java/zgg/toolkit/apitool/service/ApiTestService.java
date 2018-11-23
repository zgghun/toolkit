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
import zgg.toolkit.apitool.model.vo.BasicDataType;
import zgg.toolkit.apitool.model.vo.GroupVo;
import zgg.toolkit.apitool.model.vo.MethodVo;
import zgg.toolkit.apitool.model.vo.ParameterVo;
import zgg.toolkit.core.exception.BaseException;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.core.utils.JsonUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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

            GroupVo vo = new GroupVo();
            vo.setGroupName(groupName);

            List<MethodVo> mvos;
            if (!groups.containsKey(groupName)) {
                mvos = findMethodVo(entityClass, baseUrl);
                vo.setMethods(mvos);
                groups.put(groupName, vo);
            }

        }
        return new ArrayList<>(groups.values());
    }

    private List<MethodVo> findMethodVo(Class<?> clazz, String baseUrl) {
        Method[] methods = clazz.getDeclaredMethods();
        List<MethodVo> list = new ArrayList<>();
        for (Method method : methods) {
            RequestMapping rm = method.getAnnotation(RequestMapping.class);
            if (rm == null) {
                continue;
            }
            String apiName = HelpUtils.isBlank(rm.name()) ? method.getName() : rm.name();
            String fullUrl = baseUrl;
            String partUrl = rm.value()[0];
            if (partUrl.startsWith("/")) {
                fullUrl += partUrl;
            }
            if (HelpUtils.isNotBlank(partUrl) && !partUrl.startsWith("/")) {
                fullUrl = fullUrl + "/" + partUrl;
            }
            RequestMethod requestMethod = (rm.method().length > 0) ? (rm.method()[0]) : RequestMethod.POST;

            MethodVo methodVo = new MethodVo();
            methodVo.setName(apiName);
            methodVo.setUrl(fullUrl);
            methodVo.setRequestMethod(requestMethod);

            List<ParameterVo> params = findParameterVos(method, methodVo);
            methodVo.setParameters(params);

            list.add(methodVo);
        }
        return list;
    }

    private List<ParameterVo> findParameterVos(Method method, MethodVo methodVo) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Parameter[] parameters = method.getParameters();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
        List<ParameterVo> params = new ArrayList<>();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(RequestBody.class)) {
                methodVo.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            } else {
                methodVo.setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            }

            Class<?> paramType = parameterTypes[i];
            if (BasicDataType.contains(paramType)) {

            } else {
                Field[] fields = paramType.getDeclaredFields();
                for (Field field : fields) {
                    String name = field.getName();
                    String type = field.getType().getSimpleName();

                    String[] values = new String[0];
                    if (field.getType().isEnum()) {
                        Object[] enums = field.getType().getEnumConstants();
                        values = new String[enums.length];
                        for (int index = 0; index < values.length; index++) {
                            values[index] = enums[index].toString();
                        }
                    }

                    StringBuilder validInfo = new StringBuilder();
                    Annotation[] annotations = field.getAnnotations();
                    for (Annotation annotation : annotations) {
                        validInfo.append("@");
                        validInfo.append(annotation.annotationType().getSimpleName());
                        validInfo.append(",");
                    }

                    ParameterVo pvo = new ParameterVo();
                    pvo.setKey(name);
                    pvo.setValue(JsonUtils.toJson(values));
                    pvo.setDataType(type);
                    pvo.setValidInfo(validInfo.toString());
                    params.add(pvo);
                }
            }
        }
        return params;
    }

    private void fillParamWithDefault(Object object) {
        Class<?> targetClass = object.getClass();
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.getType().getClassLoader() != null) {
                    Object value = field.getType().newInstance();
                    fillParamWithDefault(value);
                    field.set(object, value);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
