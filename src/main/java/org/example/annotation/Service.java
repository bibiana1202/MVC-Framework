package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 추가적으로 서비스 어노테이션에 설정되어있는 모든클래스들도 찾고 싶어서 서비스 어노테이션을 만들었다!
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
