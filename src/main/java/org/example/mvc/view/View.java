package org.example.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

// 다형성 이용 --> view 는 jsp 뷰일수 도 있고, redirect 뷰일수도 있기 때문
public interface View {
    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;


}
