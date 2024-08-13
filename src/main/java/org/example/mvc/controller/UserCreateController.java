package org.example.mvc.controller;

import org.example.mvc.model.User;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller{
    // UserCreateController 가 호출되면 users 라는 곳으로 유저 리스트 목록으로 이동된 것을 확인 할 수 있다.
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // user 추가
        // 요청으로 받은 userid 와 name 을 가지고 유저를 생성해서 저장하는 코드
        UserRepository.save(new User(request.getParameter("userId"),request.getParameter("name")));
        // users 라는 클라이언트로 응답을 내려즘.
        // 클라이언트는 users로 다시 요청을 보내게 되는데 , RequestHandlerMapping 에서 /users GET 이라는 요청으로 호출되고 그럼 UserListController 가 실행
        return "redirect:/users";
        //users 리다이렉트 해달라고 클라이언트에게 응답을 보낸다. 그러면 클라이언트 에서 get 요청에 users 라는 요청이 다시 들어오게 되고
        // 그럼 dispatchersurvlet이 받아서 핸들러 맵핑(findHandler)에게 다시 한번더 물어본다. get 요청에 users라는 애가 있는지
        // UserlistController 가 있어서 유저릐 리스트를 보여주는 애로 리다이렉트가 되는 것이다.

        // 생성이 되면 클라이언트 보고 users 라는 경로를 가진쪽으로 다시한번 요청을 보내줘라고 응답을 보내준다.
        // 그러면 웹브라우저가 해당 결과 값을 받아서 users라는 경로로 get 요청을 보내게 된다.
        // 그러면 get 요청이 들어오면 DispatcherServlet이 받아서 get 요청에 users라는 것이 해당하는 애가 있니 라고 물어 보면
        // get 요청에 users 라고 해당하는 컨트롤러가 있기 때문에 해당 UserListController 가 실행된다.
    }
}
