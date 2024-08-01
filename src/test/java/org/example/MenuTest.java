package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class MenuTest {
    @DisplayName("메뉴판에서 메뉴이름에 해당하는 메뉴를 반환한다.")
    @Test
    void chooseTest() {
        // 메뉴에는 여러개의 메뉴항목을 가진다
        Menu menu = new Menu(List.of(new MenuItem("돈가스",5000), new MenuItem("냉면",7000)));

        // 메뉴판에서 메뉴이름에 해당하는 메뉴를 반환한다.
        MenuItem menuItem = menu.choose("돈가스");

        // 그것이 진짜로 맞는건지 돈가스 5000 맞는건지 검증
        assertThat(menuItem).isEqualTo(new MenuItem("돈가스",5000));

    }

    @DisplayName("메뉴판에 없는 메뉴를 선택할 시 예외를 반환한다.")
    @Test
    void chooseTest2() {
        // 메뉴에는 여러개의 메뉴항목을 가진다
        Menu menu = new Menu(List.of(new MenuItem("돈가스",5000), new MenuItem("냉면",7000)));

        assertThatCode(()->menu.choose("통닭"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 메뉴 이름입니다.");
    }
}
