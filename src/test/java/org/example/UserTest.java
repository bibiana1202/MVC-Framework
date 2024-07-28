package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserTest {

    @DisplayName("패스워드 초기화 한다.")
    @Test // ctrl + n -> test method
    void passwordTest() {
        // given
        User user = new User();

        // when
        //ctrl+ b : 다가가고 다가오는 단축키
        // 랜덤한 애를 주입하면 해당하는 패스워드를 생성할때마다 랜덤하게 생성
//         user.initPassword(new RandomPasswordGenerator());
        // 고정된 올바르게 고정된 패스워드 제너레이터 생성
        // 올바르게 고정된 패스워트 제너레이터 주입!
        user.initPassword(new CorrectFixedPasswodGenerator());
        // then
        assertThat(user.getPassword()).isNotNull();

        //랜덤으로 만들어지는 0~12자 가 조건에 부합하면 패스워드 세팅! , 만족하지않으면 세팅 안된다
    }

    @DisplayName("패스워드가 요구사항에 부합하지 않아 초기화가 되지않는다.")
    @Test // ctrl + n -> test method
    void passwordTest2() {
        // given
        User user = new User();

        // when
        user.initPassword(new WrongFixedPasswodGenerator());
        // then
        assertThat(user.getPassword()).isNull();

        //랜덤으로 만들어지는 0~12자 가 조건에 부합하면 패스워드 세팅! , 만족하지않으면 세팅 안된다
    }
}