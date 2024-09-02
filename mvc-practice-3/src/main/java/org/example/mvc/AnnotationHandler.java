package org.example.mvc;

import org.example.mvc.controller.HandlerKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// Annotation 형태로 구현해 주세요!!(*)
public class AnnotationHandler   {

    private final Class<?> clazz;
    private final Method targetMethod;

    // 생성자 생성
    public AnnotationHandler(Class<?> clazz, Method targetMethod) {
        this.clazz = clazz;
        this.targetMethod = targetMethod;

    }

    // handle이 호출되는 순간 targetMethod 호출!!
    public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Reflection ClassType 객체에서 생성자 getDeclaredConstructor 에서 생성자를 가져올수 있다.
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        Object handler = declaredConstructor.newInstance(); // 생성자를 가지고 객체를 만들수 있다.

        // 메소드 호출,타켓 오브젝트가 handler,request,response
        return (String) targetMethod.invoke(handler, request,response);
    }

}
