package org.example.mvc;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.controller.HandlerKey;

import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping{

    private final Object[] basePackage;

    // 핸들키에 해당하는 handelers를 만들고, 초기화 할때 선언됨.
    private Map<HandlerKey,AnnotationHandler> handlers = new HashMap<>();

    // 생성자
    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    // 초기화 하는 메소드 = map(handlers) 을 초기화
    public void initialize(){
        //annotation을 찾기 위해서 리플렉션을 이용
        Reflections reflections = new Reflections(basePackage); // basePackage에 대해서 reflection을 할것이다.

        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);// 해당 클래스 사용 컨트롤러가 붙어져 있는
        // basePackage 밑(org.example)에 있는
        // controller.annotation이 붙어져 있는 클래스들(현재는 homeController만 있음)을 다 가져오는 것

        // annotation이 붙어져 있는 클래스들 foreach를 돌면서 clazz에 있는 모든 method를 foreach를 돌린다.
        // 왜하냐면 일딴 controller 가 붙어있는 HomeController를 가져왔는데
        // 그런 다음에 다시 한번 method에 붙어져 있는 각각 value,method(@RequestMapping) 을 추출해야 하기 때문
        clazzesWithControllerAnnotation.forEach(clazz ->
                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                    // method 중에 RequestMapping 이 붙어져 있는 대상들 requestMappingAnnotation 다 추출
                    RequestMapping requestMappingAnnotation = declaredMethod.getDeclaredAnnotation(RequestMapping.class);
                    //@RequestMapping(value="/",method = RequestMethod.GET)
                    Arrays.stream(getRequestMethods(requestMappingAnnotation)) // method가 여러개 일수 있기 때문
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod,requestMappingAnnotation.value()) //key
                                    , new AnnotationHandler(clazz,declaredMethod)                 //values
                                    // reqeustMethod의 method 와 value의 경로인 AnnotationHandler 가져온다
                                    // AnnotationHandler에는 클래스 정보랑 메소드 정보도 있어야 한다! 그래야 호출을 해준다!

                                    // 초기화 해줄때 HandlerKey에 일치하는 핸들러(AnnotationHandler)는
                                    // 클래스 타입은 clazz여야 하고 method는 declaredMethod를 가진 AnnotationHandelr를 만든다.
                            ));

                })
        );
    }

    // 전달받은 requestMappingAnnotation에 method(GET,POST,PUT,DELETE[RequestMethod]) 리턴
    private RequestMethod[] getRequestMethods(RequestMapping requestMappingAnnotation) {
        return requestMappingAnnotation.method(); // 여러개일수도 있다
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        // 핸들러를 찾아서 넘기는 부분
        return handlers.get(handlerKey); //handler 에서 get 에서 handelerkey를 넘기면 적절한 핸들러를 넘겨주면 된다
        // 핸들러키에 해당하는 annotiationHandelr를 return
    }
}
