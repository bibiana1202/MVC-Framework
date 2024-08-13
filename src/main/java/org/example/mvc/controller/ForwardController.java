package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 역할 : 해당 하는 경로로 이동하는것
public class ForwardController implements Controller{


    private final String ForwardUriPath;

    public ForwardController(String forwardUriPath) {
        ForwardUriPath = forwardUriPath;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ForwardUriPath;
    }
}
