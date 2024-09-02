package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.IOException;

// GET /calculate?operand1=11&operator=&operand2=55  = 계산을 수행해서 결과를 리턴
// get 요청이고 해당하는 패스 피연산자 2개 연산자 1개를 전달하면 이에대한 결과값을 리턴해주는 웹 어플리케이션
public class Main {
    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        new CustomWebApplicationServer(8080).start();
    }
}
