package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter implements HandlerAdapter {
    // 이 어뎁터는 Controller 를 구현한 구현체여야지만 사용이 가능
    @Override
    public boolean supports(Object handler) {
        // 전달된 핸들러가 Controller 인터페이스를 구현한 구현체라면 지원을 해주겠다
        return (handler instanceof Controller);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Controller 로 구현한 애가 맞다면 --> 컨트롤러 인터페이스를 구현한 애들: ForwardController,HomeController,UserCreateController 등등
        // 이 핸들러를 컨트롤러로 탈캐스팅을 해주고
        // 다음 request,response를 던져서
        // 핸들러의 컨트롤러의 handlerequest를 실행하여 ViewName 을 얻는다.
        String viewName = ((Controller) handler).handleRequest(request, response);

        // 최종적으로는 ModelAndView 객체로 감싸서 리턴할수 있도록
        return new ModelAndView(viewName);
    }
}
