package org.example.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View{

    private final String name;

    public JspView(String name) {
        this.name = name;
    }


    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        model.forEach(request::setAttribute);
        // serAttribute에서 name은 users,값은 listof 처럼 세팅했던것을 모델에 값이 있으면 request set attribete 로 모두 세팅해주세요 코드(???)


        //  forward 방식
        // jspView로 화면으로 forward 해주는 부분을 이쪽으로 이동!
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(name); // viewName 으로 request 에서 얻어온다.
        requestDispatcher.forward(request,response); // requestDispatcherd 에게 viewName 으로 forward 해달라고 함. 이번에 들어온 request,response를 전달하면서
    }
}
