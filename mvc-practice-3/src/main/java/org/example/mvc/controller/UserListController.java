package org.example.mvc.controller;

import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // user list 컨트롤러 에서 list.jsp를 전달할때 users라는 정보도 함께 전달해줘야 한다.
        request.setAttribute("users", UserRepository.findAll());

        // 유저의 목록을 리턴하는 것이기 때문에 list.jsp
        return "/user/list";


    }
}
