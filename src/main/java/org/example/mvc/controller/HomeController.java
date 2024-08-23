package org.example.mvc.controller;

import org.apache.coyote.Request;
import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Annotation 형태로 구현해 주세요!!(*)
@Controller // 이제 인터페이스 형태가 아니라 annotation 형태로 바뀐것.(Annotation Controller!)
public class HomeController //implements Controller // HomeController 는 Controller 인터페이스를 구현해줍니다.
{

    // implement Method : 해당 인터페이스(Controller) 가진 메소드를 생성
//    @Override
    // (*) RequestMapping 이라는 annotation 사용!!
    // (uriPath,Method)
    @RequestMapping(value="/",method = RequestMethod.GET) //value 에는 "/" 경로가(uripath), method 형태는 requestMethod 에 get 할때 이 컨트롤러가 실행되게끔
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
         return "home"; // home 컨트롤러가 호출되면은 home 이라는 화면을 노출해 달라는 표현
    }
}
