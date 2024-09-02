package org.example;


import org.example.calculate.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    // 리팩토링을 함 , 인터페이스를 하나두고, 인터페이스로 구현한 4개의 구현체를 인터페이스로 받는다.
    private static final List<NewArithmeticOperator> arithmeticOperators =List.of(new AdditionOperator(),new SubtractionOperator() , new MultiplicationOperator(), new DivisionOperator());


    // 퍼블릭 인터페이스
    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {

        //C + A + l : 자동정렬

        // 원초적인 사용법
//        if ("+".equals(operator)) {
//            return operand1 + operand2;
//        } else if ("-".equals(operator)) {
//            return operand1 - operand2;
//        } else if ("*".equals(operator)) {
//            return operand1 * operand2;
//        } else if ("/".equals(operator)) {
//            return operand1 / operand2;
//        }

        // 이넘 사용법
//        return ArithmeticOperator.calculate(operand1,operator,operand2);

        // 인터페이스 사용법?
        //해당하는 ArithmeticOperators 에게 또한번의 support, calculate라는 작업을 위임
        // 이러한 구조로 객체들끼리 메시지를 주고받으면서, 이 메소드 호출을 메시지를 전달한다라고 표현
        // 이러한 방식을 객체지향적으로 프로그래밍 한다라고 한다.
        return arithmeticOperators.stream() // 인터페이스를 선언하고 이를 통해 통신하여 작업을 위임한다.
                .filter(arithmeticOperators -> arithmeticOperators.supports(operator)) // 들어온 operator 의 맞는 구현체를 찾은 다음,
                .map(arithmeticOperators -> arithmeticOperators.calculate(operand1,operand2)) // 그 구현체에게 , calculate 라는 작업을 위임, 인자로 받은 operand1,operand2 를 전달하면서, 해당하는 값(calculate)이 int 이기 때문에 map을 해줬다.
                .findFirst() // 첫번째 값을 받고,
                .orElseThrow(()-> new IllegalArgumentException("올바른 사칙연산이 아닙니다.")); // 해당하는 supports 연산자의 해당하는 구현체가 없다면 "올바른~" 예외 발생


//        return 0;
    }
}
