package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CookingTest {

    @DisplayName("메뉴 에 해당하는 음식을 만든다.")
    @Test
    void makeCookTest() {
        // 요리사 부분에서는 요리사 객체를 만들어
        // 요리사에게 전달할 메뉴 항목을 만듬
        Cooking cooking = new Cooking();
        MenuItem menuItem = new MenuItem("돈가스",5000);

        // 요리사에게 요리를 만들어 달라고 요청하면서 메뉴 항목을 전달
        // 메뉴 항목에 해당하는 요리가 만들어질거고 그 요리가 돈가스가 맞는지 메뉴항목과 일치하는지 검사
        Cook cook = cooking.makeCook(menuItem);

        assertThat(cook).isEqualTo(new Cook("돈가스",5000));
        // cook 이라는 객체들 끼리 비교 할때는 equals and hashcode 가 있어야


    }
}
