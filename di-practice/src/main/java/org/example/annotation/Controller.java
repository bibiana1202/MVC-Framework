package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // Controller Annotation이 붙을수 있는 Target은 TYPE 이다.
// TYPE : 클래스나 인터페이스나 enum 등 에 붙을수 있는 Annotation 이다.
// Controller 어노테이션을 클래스에 붙일 것이기 때문
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
