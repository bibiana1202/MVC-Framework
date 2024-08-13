package org.example.mvc.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// 필터 -> 서블릿에서 사용하는 개념 : 스프링이라는 프레임워크 전에 동작하는 역할을 수행

@WebFilter("/*") // 필터를 전체에 적용한다.
public class CharacterEncodingFilter implements Filter {

    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // utf-8 관련해서 캐릭터 인코딩 필터를 추가 해주면 한글이 깨지지 않는다.
        request.setCharacterEncoding(DEFAULT_ENCODING);
        response.setCharacterEncoding(DEFAULT_ENCODING);

        chain.doFilter(request, response); // 다음 필터로 넘겨라 이 요청과 응답을 가지고
    }

    @Override
    public void destroy() {


    }
}
