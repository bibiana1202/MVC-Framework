package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HandlerKey;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// (1) DispatcherServlet 구현
// dispatcherServlet 만들기! => 프론트 컨트롤러 패턴 :모든 요청을 받는 디스페쳐서블릿이 요청 uri path 를 가지고 적절한 컨트롤러를 찾아서 실행해주는 프론트 컨트롤러 패턴
// 톰캣이 해당 파일을 실행하려면 해당파일이 servlet 이어야 하므로 extends 를 하고 http servlet을 상속
@WebServlet("/") // 해당 dispatcherServlet 은 web survlet이라는 어노테이션을 이용하여 모든 경로에서 입력하더라도 dispatcherServlet이 호출되도록 설정
public class DispatcherServlet extends HttpServlet {
    // 로그로 모든 요청을 받아도 로그가 찍히는지 확인
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    // 모든 요청을 받은 DispatcherServlet은 request mapping 에게
    // 톰캣이 HTTP survlet을 싱글턴, 객체 한개로 만드는데 그때 init 메소드가 호출 = 서블릿이 만들어 지면서
    private RequestMappingHandlerMapping rmhm;

    // 뷰어뎁터 구현
    private List<HandlerAdapter> handlerAdapters;

    // 뷰리절브 구현
    private List<ViewResolver> viewResolvers ;

    //톰캣이 서블릿을 생성할때
    // 해당 RequestMappingHandlerMapping 이 map을 초기화 하도록
    @Override
    public void init() throws ServletException {
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init(); // 초기화

        // 핸들러 어뎁터 초기화
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter());
        // 뷰리절브 초기화
        viewResolvers = Collections.singletonList(new JspViewResolver());


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
fop        log.info("[DispatcherServlet] service started"); // 어떤 경로(ex.http://localhost:8080/asdfg) 여도 dispatcherServlet 이 호출되는것을 확인 가능

        try {
            // 요청된 경로(getRequestURI)이 들어오면 해당 객체에서 작업을 위임
            // 작업 :  요청 uri 에 대한 처리할수 있는 핸들러(컨트롤러)를 달라하면 반환
            // HttpServletRequest 리퀘스트 에서 getmethod를 하면 get 메소드인지 post 메소드 인지 알수 있고,
            // 이 정보를 가지고 RequestMethod 에서 우리가 원하는 형태로 변경하는
            // uri는 어떤건지 까지 해서 HandlerKey를 만들었다.
            // (2) HandlerMapping 구현 *핸들러 매핑을 통해서 핸들러(컨트롤러)를 찾고
            Controller handler = rmhm.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()),request.getRequestURI()));

            // handler 에서 적절한 컨트롤러를 받으면, 컨트롤러에게 작업을 위임
            // *핸들러에게 작업을 위임한다.
//            String viewName = handler.handleRequest(request,response);

            // 뷰 네임이 "redirect:/users" 이렇게 인식함
            // 어떤 경우에는 화면으로 forwarding 되고, 리다이렉트인 경우에는 리다이렉트를 할수있도록 분리가 되어야 함

            // (3) 핸들러 어뎁터 구현
            // *핸들러를 지원하는 어댑터를 찾아서
            HandlerAdapter handlerAdapter =  handlerAdapters.stream()
                                                            .filter(ha -> ha.supports(handler))
                                                            .findFirst()
                                                            .orElseThrow(() -> new ServletException("No adapter for handler["+handler+"]"));

            // 핸들러 어뎁터를 실행
            // 선택된 handler 를 전달해주고 내부에서 핸들러를 실행해준다(컨트롤러를 실행)
            // -> handlerAdapters => SimpleControllerHandlerAdapter
            // 내부 에서 viewName을 받아서 ModelAndView로 감싸서 리턴
            // *어뎁터 내부에서 핸들러를 실행해주면 ModelAndView를 DispatcherSurvlet 에게 return 해준다.
            ModelAndView modelAndView = handlerAdapter.handle(request,response,handler);



            // 뷰 리절버 구현
            // * 뷰 리절버에서 뷰를 선택한 다음 뷰를 랜더링 해주면 된다!!
            for (ViewResolver viewResolver : viewResolvers) {
                //선택된 뷰에 랜더를 하는 것 : 만약에 선택된 뷰가 redirect 뷰면 redirect , jsp면 jsp 페이지를 보여주는것.
                View view = viewResolver.resolveView(modelAndView.getViewName());// viewResolver를 통해서 viewName 전달하면 적절한 뷰가 선택이 될거고
                view.render(modelAndView.getModel(),request,response); // 그러면 랜더를 하여 화면을 그려주는 부분
            }

            // viewName이 return 된후!
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName); // viewName 으로 request 에서 얻어온다.
//            requestDispatcher.forward(request,response); // requestDispatcherd 에게 viewName 으로 forward 해달라고 함. 이번에 들어온 request,response를 전달하면서

        } catch (Exception e) {
            log.error("exception occured: [{}]",e.getMessage(),e);
            throw new ServletException(e);
        }


    }

}
