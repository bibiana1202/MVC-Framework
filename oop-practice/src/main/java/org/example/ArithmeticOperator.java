package org.example;

import java.util.Arrays;

public enum ArithmeticOperator {
    // 이넘으로 만들기 => 인터페이스
    // addition ,.. 추상메소드의 override 메소드
    ADDITION("+"){
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return operand1 +operand2;
        }
    },
    SUBTRACTION("-") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return  operand1 - operand2;
        }
    },
    MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return  operand1 * operand2;
        }
    },
    DIVISION("/") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return  operand1 / operand2;
        }
    };

    private final String operator;

    private ArithmeticOperator(String operator) {
        this.operator = operator;
    }



    // 추상 메소드
    public abstract  int arithmeticCalculate(final int operand1, final int operand2);

    // 외부로 노출되는 public Interface
    public static int calculate(int operand1, String operator, int operand2) {
        ArithmeticOperator arithmeticOperator = Arrays.stream(values())
                .filter(v -> v.operator.equals(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다"));

        return arithmeticOperator.arithmeticCalculate(operand1,operand2);




    }
}
