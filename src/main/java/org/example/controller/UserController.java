package org.example.controller;

import org.example.annotation.Controller;
import org.example.annotation.Inject;
import org.example.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Inject
    // 우리가 만든 DI-Framework 가 정상적으로 동작했다면, userService에 대한 의존성을 주입해줄 것이고,
    // 그러면 userService에는 값이 있을 것이다! null이 아니다!
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public UserService getUserService() {
        return userService;
    }

}
