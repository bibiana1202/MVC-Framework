package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;

public interface ArithmeticOperator {

    // 두개의 메소드를 가진 인터페이스
    boolean supports(String operator);
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
