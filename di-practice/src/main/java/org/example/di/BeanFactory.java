package org.example.di;

import org.example.annotation.Inject;
import org.example.controller.UserController;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {


    private final Set<Class<?>> preInstantiatedBeans;

    // classtype 객체로 생성된 instance를 value로 가지는 beans라는 instatnce 변수
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedBeans) {
        this.preInstantiatedBeans = preInstantiatedBeans;  // 인스턴스 변수로 담음
        initialize(); // beanFactory 가 가지는 instance 변수인 beans 를 초기화 해주는 initialize
    }


    @SuppressWarnings("unchecked")
    private void initialize() {
        // instance 변수인 preInstantitatedClazz(classType 객체) 를 하나씩 돌면서,..
        // classtype 객체로 instance를 만들어 준다음 key는 classtype 객체, value를 instatnce로 초기화
        for (Class<?> clazz : preInstantiatedBeans) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    private Object createInstance(Class<?> concreteClass) {
        // 생성자 -> instance를 생성하기 위해서는 생성자가 필요
        Constructor<?> constructor = findConstructor(concreteClass); // classType 객체로 constructor 를 조회해온 다음

        // 파라미터 정보가 필요함
        List<Object> parameters = new ArrayList<>();
         for (Class<?> typeClass : constructor.getParameterTypes()) { // parameter type 에 대한 class-type 객체를 가지고 왔는데,
            // typeClass <- userController 에 대한 생성자 파라미터는 userService!
            // userService는 파라미터가 없다 -> getbean 시에 별도로 다시 생성해줄 필요가 없다.
            parameters.add(getBean(typeClass)); // typeclass 에 대한 instance를 가지고 오는 메소드
        }
        // 최종적으로 instance 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    // constructor를 가지고 오는 코드
    // class 타입 객체를 받아서 Constructor를 return 해주는 코드

    private Constructor<?> findConstructor(Class<?> concreteClass) {
        // class 타입 객체를 보내는데, inject가 붙은 constructor를 가지고 오도록
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(concreteClass);

        if(Objects.nonNull(constructor)){
            return constructor; // 여긴 UserController 가 지나감.
        }
        return concreteClass.getConstructors()[0]; // 이건 ..userService 들어올때 지나가는데 모르겠따.

    }

    private Object getParameterByClass(Class<?> typeClass) {
        Object instanceBean = getBean(typeClass);

        if(Objects.nonNull(instanceBean)){
            return instanceBean;
        }
        //bean 을 호출했는데 아직 userController 에 대한 instance 생성되기전에
        // userservice 에 대해서 bean이 없다!!
        // 그래서 여기서 instance 를 다시 한번 생성
        // 결론 : userController의 instance를 생성하려고 봤더니, userService에 대한 instance가 먼저 필요했고, userService에 대한 instance를 먼저 생성해주는 재기함수를 호출 해줌0
        return createInstance(typeClass);
    }

    // 어떤 class type 객체가 들어올지 모르기 때문에 generic type 으로
    public <T> T getBean(Class<T> requiredType) {
        // 해당 instance 변수에서 인자로 넘어온 요구된 classtype 객체를 key로 전달해서 instance를 반환해주는 메소도
        return (T) beans.get(requiredType);
    }
}
