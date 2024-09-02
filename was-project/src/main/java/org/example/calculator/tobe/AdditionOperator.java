package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;

public class AdditionOperator implements ArithmeticOperator {
    // S +F6 : 오타시 이름 변경

    @Override
    public boolean supports(String operator) {
        return "+".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toInt()+operand2.toInt();
    }
}
