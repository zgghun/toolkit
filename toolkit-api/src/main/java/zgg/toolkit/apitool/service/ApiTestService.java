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
import zgg.toolkit.core.utils.HelpUtils;

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
    public List<GroupVo> findApi() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider scan = new ClassPathScanningCandidateComponentProvider(false);
        scan.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        Set<BeanDefinition> beanDefinitionSet = scan.findCandidateComponents("zgg.toolkit.apitool.**");

        Map<String, GroupVo> groups = new HashMap<>(16);

        for (BeanDefinition beanDefinition : beanDefinitionSet) {
            Class<?> entityClass = ClassUtils.getClass(beanDefinition.getBeanClassName());
            RequestMapping requestMapping = entityClass.getAnnotation(RequestMapping.class);
            if (requestMapping == null) {
                continue;
            }
            String url = requestMapping.value()[0];
            // class上的@RequestMapping name值一样的分为一组,若没有就用class名
            String rmName = requestMapping.name();
            String className = entityClass.getSimpleName();
            String groupName = HelpUtils.isBlank(rmName) ? className : rmName;

            GroupVo vo = new GroupVo();
            vo.setGroupName(groupName);

            List<MethodVo> mvos = new ArrayList<>();
            if (!groups.containsKey(groupName)) {
                mvos = findMethodVo(entityClass, url);
                vo.setMethods(mvos);
                groups.put(groupName, vo);
            }

        }
        return new ArrayList<>(groups.values());
    }

    private List<MethodVo> findMethodVo(Class<?> clazz, String url) {
        Method[] methods = clazz.getDeclaredMethods();
        List<MethodVo> list = new ArrayList<>();
        for (Method method : methods) {
            RequestMapping rm = method.getAnnotation(RequestMapping.class);
            if (rm == null) {
                continue;
            }
            MethodVo methodVo = new MethodVo();
            methodVo.setName(HelpUtils.isBlank(rm.name()) ? method.getName() : rm.name());

            String partUrl = rm.value()[0];
            if (partUrl.startsWith("/")) {
                url += partUrl;
            }
            if (!"".equals(partUrl) && !partUrl.startsWith("/")) {
                url = url + "/" + partUrl;
            }
            methodVo.setUrl(url);

            if (rm.method().length > 0) {
                methodVo.setRequestMethod(rm.method()[0]);
            } else {
                methodVo.setRequestMethod(RequestMethod.POST);
            }
            methodVo.setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);

            List<ParameterVo> params = findParameterVos(method, methodVo);
            methodVo.setParameters(params);
            list.add(methodVo);
        }
        return list;
    }

    private List<ParameterVo> findParameterVos(Method method, MethodVo methodVo) {
        List<ParameterVo> params = new ArrayList<>();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

        Parameter[] parameters = method.getParameters();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        Class<?>[] parameterTypes = method.getParameterTypes();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(RequestBody.class)) {
                methodVo.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            }
            Class<?> parameterType = parameterTypes[i];
            Field[] fields = parameterType.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                String type = field.getType().getSimpleName();
                String[] values = null;
                String hint = null;

                if (field.getType().isEnum()) {
                    Object[] enums = field.getType().getEnumConstants();
                    values = new String[enums.length];
                    for (int index = 0; index < values.length; index++) {
                        values[index] = enums[index].toString();
                    }
                }

                ParameterVo pvo = new ParameterVo();
                pvo.setKey(name);
                pvo.setValue("22222");
                pvo.setDataType(type);
                pvo.setValidInfo("33333");
                params.add(pvo);
            }
        }
        return params;
    }

    private Object prototype(Class<?> parameterType) {
        try {
            Method[] methods1 = parameterType.getMethods();
            for (Method m : methods1) {
                if ("prototype".equals(m.getName())) {
                    Object object = m.invoke(null);
                    if (object != null) return object;
                }
            }
            Object object = parameterType.newInstance();
            fillObjectWithDefault(object);
            return object;
        } catch (Exception ex) {
            return null;
        }
    }

    private void fillObjectWithDefault(Object object) {
        Class<?> targetClass = object.getClass();
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.getType().getClassLoader() != null) {
                    Object value = field.getType().newInstance();
                    fillObjectWithDefault(value);
                    field.set(object, value);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getAnnotation(Annotation annotation) {
        Method[] methods = annotation.annotationType().getMethods();
        String message = "";
        String condition = "";
        for (Method method : methods) {
            try {
                String name = method.getName();
                if ("groups".equals(name)) continue;
                if ("payload".equals(name)) continue;
                if ("toString".equals(name)) continue;
                if ("hashCode".equals(name)) continue;
                if ("annotationType".equals(name)) continue;
                if ("message".equals(name)) message = method.invoke(annotation).toString();
                else condition += method.getName() + "=" + method.invoke(annotation).toString() + ", ";
            } catch (Exception ex) {
            }
        }
        if (condition.length() > 2) condition = condition.substring(0, condition.length() - 2);
        if (condition.length() > 0) condition += ", ";
        return annotation.annotationType().getSimpleName() + "(" + condition + "message=" + message + ")";
    }

    private String getHint(Annotation annotation) {
        Method[] methods = annotation.annotationType().getMethods();
        String message = "";
        String condition = "";
        for (Method method : methods) {
            try {
                String name = method.getName();
                if ("pattern".equals(name)) {
                    return method.invoke(annotation).toString();
                }
            } catch (Exception ex) {
            }
        }
        return null;
    }
}
