package org.example.mvc;

import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 핸들러어뎁터 = 핸들러리스트
public interface HandlerAdapter {
    boolean supports(Object handler); // 전달된 핸들러를 지원하는 어댑터이냐?

    // 지원하는데~? 수행을 해줄꺼에여~
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
