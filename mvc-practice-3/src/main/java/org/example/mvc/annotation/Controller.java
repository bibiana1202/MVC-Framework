package org.example.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation 형태로 구현해 주세요!!(*)
// 이제 인터페이스 형태가 아니라 annotation 형태로 바뀐것.(Annotation Controller!)
@Target({ElementType.TYPE}) //annotation 의 target 은 ElementType.TYPE 으로 해야지만 이 annotiation 이 클래스에 붙일수 있는 것이다.
@Retention(RetentionPolicy.RUNTIME) // retention 유지기간
public @interface Controller {
}
