package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        // Reflection 에서 제공해주는 ReflectionUtils에 getAllConstructors 메소드 = class-type 객체의 모든 constructor를 가지고 온다.
        // 단, Inject Annotation에 붙은 생성자만 가지고 온다.
        Set<Constructor> injectedConstrutors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));

        if(injectedConstrutors.isEmpty()){ // inject가 붙은 constructor가 없으면 null
            return null;
        }
        return injectedConstrutors.iterator().next(); // 있으면 그 생성자를 리턴
    }
}
