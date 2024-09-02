package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

// 목표 : @Controller 어느테이션이 설정돼 있는 모든 클래스를 찾아 출력한다.
public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));


        logger.debug("beans: [{}]",beans);
    }

    // C+A+m : 메소드로 추출하는 단축어
    // list type 인데 Annotation 타입 이다. annotations 을 받는다.
    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections =  new Reflections("org.example"); // org.example 패키지 밑에 있는 모든 클래스 를 대상으로 reflection을 사용한다.

        Set<Class<?>> beans = new HashSet<>();

        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

//        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class)); // beans 에 모든걸 더하는데, reflection에 getTypedAnnotationWith라는 메소드
//        // => reflection 해당 패키지("org.example") 밑에 있는 클래스에 대해서 이러한(Controller) 어노테이션에 붙어 있는 클래스들을 찾는다는 의미
//        // 즉, 해당 패키지 밑에 있는 Controller라는 어노테이션이 붙어져 있는 대상들을 다 찾아서 해당 HashSet 에 담는 코드
//        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
//
        // 모두 등록된 beans 을 반환
        return beans;
    }

    // show 클래스에 대한 모든 정보를 출력해주는 테스트 메소드를 만들기 위함
    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        // 클래스에 대해서 필드, 생성자, 메소드 정보 확인 가능 !!

        // 1) 필드
        logger.debug("User all declared fields : [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList())); // 유저의 선언된 모든 필드를 출력
        // ==> 유저 라는 클래스 이름이 나오고 선언되어 있는 필드는 userid,name

        // 2) 생성자
        logger.debug("User all declared Constructors : [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        // ==> 스트링 두개 를 받는 생성자 하나 밖에 없어!

        //3) 메소드
        logger.debug("User all declared Methods : [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
        // ==> 메소드 출력 되는지 확인 가능 3개!
    }

    //힙 영역에 로드되어 있는 클래스 타입을 객체를 가져오는 방법
    @Test
    void load() throws ClassNotFoundException {
        // 1) class.class : Class<User> clazz 형태로 힙 영역에 로드되어 있는 클래스 타입의 객체를 가져올수 있다
        Class<User> clazz = User.class;

        // 2) instance.getclass
        User user = new User("wew1202","박혜정");        // 객체 생성
        Class<? extends User> clazz2 = user.getClass();

        // 3) class.fullname
        Class<?> clazz3 = Class.forName("org.example.model.User"); // exception 밖으로 던지기 (??)


        // 1) 첫번째 출력
        logger.debug("clazz: [{}]",clazz);

        // 2) 출력
        logger.debug("clazz2: [{}]",clazz2);

        // 3) 출력
        logger.debug("clazz3: [{}]",clazz3);

        // 같은지 확인
        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz3 == clazz).isTrue();
        // 통과 하면 모두 같은 객체라는것 확인 가능!
    }
}
