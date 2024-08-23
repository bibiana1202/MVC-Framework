package org.example.mvc.annotation;


import org.example.mvc.controller.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// (*) RequestMapping 이라는 annotation 사용!!
@Target({ElementType.METHOD,ElementType.TYPE}) // 해당 annotation 은 method??와 클래스 등등에 붙을수 있다
@Retention(RetentionPolicy.RUNTIME) // retention 유지기간
public @interface RequestMapping {
    String value() default ""; // uriPath

    RequestMethod[] method() default {};


}
