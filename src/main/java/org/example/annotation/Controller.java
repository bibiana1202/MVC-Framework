package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 요구사항 :  @Controller 어노테이션이 설정돼있는 모든 클래스를 찾아서 출력한다.
// 클래스에다 컨트롤러 어노테이션을 붙이고 싶다.
@Target({ElementType.TYPE}) // type: 클래스나 인터페이스나 어노테이션 타입 이나 이넘 선언에 붙일수 있는 어노테이션
@Retention(RetentionPolicy.RUNTIME) // 유지기간은 RUNTIME 기간 까지 설정
public @interface Controller {
}
