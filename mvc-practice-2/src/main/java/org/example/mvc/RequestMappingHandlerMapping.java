package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

// 역할 : key 는 url path 이다. 즉, 예로들면 users 라는 경로로 들어올때 user Controller를 실행해줘 라는 형태로,key-value 형태로 저장하기 위해서 관리하는 클래스
// [key] /users [value] UserController 관리하는 클래스
// 해당 역할은 DispatcherServlet.java 에서 사용될것!
public class RequestMappingHandlerMapping {
    private Map<String, Controller> mappings = new HashMap<>();

    void init(){
        mappings.put("/",new HomeController()); // 어떠한 경로도 설정해 주지 않으면, homeController 가 실행되게끔 추가
    }

    // urlPath 와 일치하는 Controller 를 return 해주는 메소드
    public Controller findHandler(String uriPath){
        return mappings.get(uriPath);
    }
}
