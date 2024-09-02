package org.example.mvc;

import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

// 역할 : key 는 url path 이다. 즉, 예로들면 users 라는 경로로 들어올때 user Controller를 실행해줘 라는 형태로,key-value 형태로 저장하기 위해서 관리하는 클래스
// [key] /users [value] UserController 관리하는 클래스
// 해당 역할은 DispatcherServlet.java 에서 사용될것!
public class RequestMappingHandlerMapping implements HandlerMapping {
    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    void init(){
        // get 인지 post 인지 구분이 안됨.
//        mappings.put(new HandlerKey(RequestMethod.GET,"/"),new HomeController()); // 어떠한 경로도 설정해 주지 않으면, homeController 가 실행되게끔 추가
        mappings.put(new HandlerKey(RequestMethod.GET,"/users"),new UserListController()); // 컨트롤러 추가
        mappings.put(new HandlerKey(RequestMethod.POST,"/users"),new UserCreateController()); // 컨트롤러 추가
        mappings.put(new HandlerKey(RequestMethod.GET,"/user/form"),new ForwardController("/user/form")); // 컨트롤러 추가 : get 요청인데 user/form 이라고 입력이 되면 /user/form 으로 이동해달라는 부분
        // forward 는 단순하게 /user/form 으로 이동해 달라는 의미

    }

    // urlPath 와 일치하는 Controller 를 return 해주는 메소드
    public Controller findHandler(HandlerKey handlerKey){
        return mappings.get(handlerKey);
    }
}
