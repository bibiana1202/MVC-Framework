package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter implements HandlerAdapter {
    // 이 어뎁터는 Controller 를 구현한 구현체여야지만 사용이 가능
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller); // 전달된 핸들러가 Controller 인터페이스를 구현한 구현체라면 지원을 해주겠다
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((Controller) handler).handleRequest(request, response); // Controller 로 구현한 애가 맞다면 이 핸들러를 컨트롤러로 탈캐스팅을 해주고 .handle request를 실행한 다음 request,response를 던지면
        //컨트롤러 인터페이스를 구현한 애들: ForwardController,HomeController,UserCreateController 등등
        return new ModelAndView(viewName);
        // 최종적으로는 ModelAndView 객체로 감싸서 리턴할수 있도록
    }
}
