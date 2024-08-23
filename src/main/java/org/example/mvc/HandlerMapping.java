package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HandlerKey;

public interface HandlerMapping{
    Object findHandler(HandlerKey handlerKey);
    // 이제는 Controller 인터페이스 뿐만 아니라
    // Controller 가 아니라 Object : 지금은 요 컨트롤러(ex. HomeContorller)가 인터페이스 형태로 되어 있지 때문에 Controller 였는데,
    // annotation 형태로 받을 수 있도록 만들기 위해서 object 로 만들었다...
}
