package org.example;


import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// url path 와 해당 서블릿 mapping을 위해 annotation 방식을 사용
// calculate 라는 url path가 들어왔을때 해당 서블릿이 실행되게끔 해!
@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {
    // implements Servlet 서블릿 인터페이스를 구현했을때는 init,service,destory,getServletConfig,getServletInfo 메소드를 각각 넣어줘야
    // 즉, 구현을 직접 해줘야 한다. ==> generic 서블릿 나옴!
    // extends GenericServlet generic suvlet : init,service,destory,getServletConfig,getServletInfo는 필요할때만 오버라이드하면 되고, 해당 부분이 필요가 없어짐.
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);

    //HTTPServlet 차이점은?
    // get 요청이 올때는 do get 을 호출하면 되고, 만약에 오버라이드 메소드시 do get, do put, do delete 요청마다 이렇게 메소드들을 저희가 구현해주면 된다.
    // do get 이 구현되어있는 형태가 아닌 저희가 원하는 로직으로 수행하기 위해서는 오버라이드를 해준것이다!!
    // service 메소드는 suvlet container 톰캣이 호출하는 메소드 get 요청이면 doget, head메소드면 do head ,post요청이면 do post즉, 이메소드가 호출되는 것은 맞지만 우리가 get 요청이면 doget을 구현
    // 최종적으로는 httpservlet 을 사용하면 된다!!
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("service");

        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1),operator,new PositiveNumber(operand2));

        PrintWriter writer = response.getWriter();
        writer.println(result);
    }

    //    private ServletConfig servletConfig;

    // 생명 메소드
    // init메소드는 서블릿 컨테이너가 서블릿 컨테이너 가 서블릿 생성후 초기화 작업을 수행하기에 호출하는 메소드
    // 서블릿 객체를 싱글톤으로 관리 즉, 인스턴스 하나만 생성
    // calculatorservlet이라는 클래스를 이메소드를 호출하려면 인스턴스를 만들어야되는데, 이 인스턴스는 하나만 생성하고 공유하는 방식
//    @Override
//    public void init(ServletConfig servletConfig) throws ServletException {
//        log.info("init");
//        // 초기화시 인스턴스 변수에 초기화를 시켜주고
//        this.servletConfig= servletConfig;
//        // 리로스 초기화 필요하다면..
//    }
    // 서비스 메소드만 추상 메소드 이고,나머지 메소드는 오버라이드 하면 된다.
//    @Override
//    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
//        log.info("service");
//        // clinent로 operand1, operator, operand2 라는 파라미터를 던저
//
//        int operand1 = Integer.parseInt(request.getParameter("operand1"));
//        String operator = request.getParameter("operator");
//        int operand2 = Integer.parseInt(request.getParameter("operand2"));
//        //해당 도메인을 이용해서 계산을 시키고
//        int result = Calculator.calculate(new PositiveNumber(operand1),operator,new PositiveNumber(operand2));
//        // 결과값을 client 에 전달해주는 ...
//        PrintWriter writer = response.getWriter();
//        writer.println(result);
//    }
//    @Override
//    public void destroy() {
//        // 자원을 해제해주는 작업이 필요하다면 추가
//        log.info("destroy");
//    }
//
//    // 기타 메소드
//    @Override
//    public ServletConfig getServletConfig() {
//        // 해당 메소드가 만약의 호출될때 servletConfig 를 리턴하는 구조를 많이 사용한다.
//        return this.servletConfig;
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "";
//    }


}