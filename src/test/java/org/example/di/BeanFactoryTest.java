package org.example.di;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeanFactoryTest {

    // Reflections 클래스를 필드로 가지도록
    private Reflections reflections;
    // BeanFactory 도 필드로 가지도록
    private BeanFactory beanFactory;

    // @BeforeEach setup 메소드 : 테스트 메소드가 호출되지전에 미리 호출되는 메소드
    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() {
        // reflection 기술을 사용할건데,
        // BasePackage 즉 , org.example 밑에 있는 클래스들을 대상으로 reflection 기술을 사용하겠다.
        reflections = new Reflections("org.example");

        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class); // 우리가 만든 controller,serviece 어노테이션 두개를 전달 해서, annotation 붙은 클래스 타입 객체를 return 해주는 메소드
        //-> UserController, UserService 가 리턴됨.

        beanFactory = new BeanFactory(preInstantiatedClazz);
    }

    // 어노테이션 클래스 타입 객체가 여러개 들어올수 있다는 의미
    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
//        // 컨트롤러,서비스의 annotation을 받아서, org.example 밑에 있는 클래스 중에 컨트롤러(서비스)의 annotation에 붙은 클래스 타입 객체를 조회해오라는 의미의 코드
//        Set<Class<?>> beans = new HashSet<>();
//        for (Class<? extends Annotation> annotation : annotations) {
//            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
//        }
//        return beans; // add 된 set을 리턴
//
//        // controller 어노테이션이 붙은 클래스 객체 - UserController
//        // service 어노테이션이 붙은 클래스 객체 - UseService

        return Arrays.stream(annotations)
                .flatMap(annotation -> reflections.getTypesAnnotatedWith(annotation).stream())
                .collect(Collectors.toSet());
    }

    @Test
    void diTest() {
        // beanFactory(빈을 생성해주는곳) 에서 빈을 하나 꺼낼 것 -> UserController.class 타입 객체 빈을 ! 꺼낼것
        UserController userController = beanFactory.getBean(UserController.class);

//        // beanFactory 에서 userController classtype 의 객체에 대한 instance를 조회하면
//        // UserController 는 있을 것이기 때문에 null은 아닐 것이다.
//        assertThat(userController).isNotNull();
//        assertThat(userController.getUserService()).isNotNull();

        assertNotNull(userController.getUserService());

    }
}