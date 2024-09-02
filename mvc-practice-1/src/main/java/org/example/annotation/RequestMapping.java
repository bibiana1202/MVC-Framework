package org.example.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// request mapping 은 클래스에도 붙일수 있지만 메소드 에서도 붙일수 있다.
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String value() default ""; // 어떤 값도 입력하지 않으면 빈 문열
    // request mapping 할 때 get 요청 , post 요청 에 따라 RequestMethod를 설정 할수 있고
    // get 요청에 url path 를 입력할수 있기 때문에 , url path에 해당하는 value 와
    // get 요청인지 post 요청인지에 따른 RequestMethod 를 설정한다.
    RequestMethod[] method() default{} ; // 디폴트 빈값

    // RequestMethod 설정 ==> 간단하게 ~ enum 으로
}
