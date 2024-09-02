package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// homeController 는 Controller 인터페이스를 구현해줍니다.
public class HomeController implements Controller{

    // implement Method : 해당 인터페이스(Controller) 가진 메소드를 생성
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home.jsp"; // home 컨트롤러가 호출되면은 home 이라는 화면을 노출해 달라는 표현
    }
}
