package org.example;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
 * 비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArugumentException 예외를 발생시킨다.
 * 경계조건에 대해 테스트 코드를 작성해야 한다.
 */
public class PasswordValidatorTest
{
    //프로덕션 코드
    //테스트 코드
    //프로덕션 커드 의 패키지 와 테스트 코드의 패키지 맞추어줘야 함

    //ctrl + n
    @DisplayName("비밀번호가 최소 8자 이상 , 12자 이하면 예외가 발생하지 않는다.")
    @Test
    void validatePasswordTest() {
        // given
        String password = "apt12345";
        PasswordValidator passwordValidator = new PasswordValidator();


        // when, then
        assertThatCode(() -> PasswordValidator.validate(password)).doesNotThrowAnyException();

    }

    @DisplayName("비밀번호가 8자미만 또는 12자 초과하는 경우 IllegalArgumentExceeption 예외가 발생한다.")
    @ParameterizedTest // 경계값 체크 Junit5 사용 www.pertikaainululation.net/programming/testing/unit-5-tutorial-writing-parameterized-tests , 실무에서 많이 사용함
    @ValueSource(strings = {"aabbcce","aabbccddeeffg"}) // 예외가 발생하는지 확인
    public void validatePasswordTest2(String value) {
        //given

        PasswordValidator passwordValidator = new PasswordValidator();

        //when , then
        assertThatCode(()-> PasswordValidator.validate(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 최소 8자 이상 12자 이하여야한다.");
    }

    //테스트하기 쉬운코드를 작성하다보면 더 낮은 결합도를 가진 설계를 얻을수 있음
}
