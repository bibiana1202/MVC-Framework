package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// dispatcherServlet 만들기! => 프론트 컨트롤러 패턴 :모든 요청을 받는 디스페쳐서블릿이 요청 uri path 를 가지고 적절한 컨트롤러를 찾아서 실행해주는 프론트 컨트롤러 패턴
// 톰캣이 해당 파일을 실행하려면 해당파일이 servlet 이어야 하므로 extends 를 하고 http servlet을 상속
@WebServlet("/") // 해당 dispatcherServlet 은 web survlet이라는 어노테이션을 이용하여 모든 경로에서 입력하더라도 dispatcherServlet이 호출되도록 설정
public class DispatcherServlet extends HttpServlet {
    // 로그로 모든 요청을 받아도 로그가 찍히는지 확인
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    // 모든 요청을 받은 DispatcherServlet은 request mapping 에게
    // 톰캣이 HTTP survlet을 싱글턴, 객체 한개로 만드는데 그때 init 메소드가 호출 = 서블릿이 만들어 지면서
    private RequestMappingHandlerMapping rmhm;

    //톰캣이 서블릿을 생성할때
    // 해당 RequestMappingHandlerMapping 이 map을 초기화 하도록
    @Override
    public void init() throws ServletException {
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init(); // 초기화
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started"); // 어떤 경로(ex.http://localhost:8080/asdfg) 여도 dispatcherServlet 이 호출되는것을 확인 가능

        try {
            // 요청된 경로(getRequestURI)이 들어오면 해당 객체에서 작업을 위임
            // 작업 :  요청 uri 에 대한 처리할수 있는 핸들러(컨트롤러)를 달라하면 반환
            // *핸들러 매핑을 통해서 핸들러(컨트롤러)를 찾고,
            Controller handler =  rmhm.findHandler(request.getRequestURI());

            // handler 에서 적절한 컨트롤러를 받으면, 컨트롤러에게 작업을 위임
            // *핸들러에게 작업을 위임한다.
            String viewName = handler.handleRequest(request,response);

            // viewName이 return 된후!
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName); // viewName 으로 request 에서 얻어온다.
            requestDispatcher.forward(request,response); // requestDispatcherd 에게 viewName 으로 forward 해달라고 함. 이번에 들어온 request,response를 전달하면서

        } catch (Exception e) {
            log.error("exception occured: [{}]",e.getMessage(),e);
            throw new ServletException(e);
        }


    }

}
