package org.example.calculator.domain;

public class PositiveNumber {
    // 양수를 의미하는 객체 value Object
    private final int value;

    public PositiveNumber(int value){
        validate(value);
        this.value = value;
    }

    // value 가 음수면 예외
    private void validate(int value) {
        if(isNegativeNumber(value)){
            throw new IllegalArgumentException("0 또는 음수를 전달할 수 없습니다.");
        }
    }

    // 양수면 객체가 만들어진다.
    // ==> positiveNumer 라는 객체가 만들어졌다는 것은 음수가 아니라는 의미 = 양수!
    private boolean isNegativeNumber(int number) {
        return number <= 0 ;
    }

    // Int 값을 return 해 주는 method
    public int toInt(){
        return value;
    }
}
