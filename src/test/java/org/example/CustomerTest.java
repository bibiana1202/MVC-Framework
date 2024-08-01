package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 음식점에서 음식 주문하는 과정 구현
 * 요구사항
 *  1. 도메인을 구성하는 객체에서 어떤 것들이 있는지 고민
 *      -> 손님, 메뉴판, 돈가스, 냉면, 만두, 요리사, 요리
 *  2. 객체들 간의 관계를 고민
 *      -> 손님   -- 메뉴판
 *      -> 손님   -- 요리사
 *      -> 요리사 -- 요리
 *  3. 동적인 객체를 정적인 타입으로 추상화 해서 도메인 모델링 하기
 *      -> 손님           -- 손님타입
 *      -> 돈가스/냉면/만두 -- 요리타입
 *      -> 메뉴판         -- 메뉴판 타입
 *      -> 메뉴           -- 메뉴 타입
 *  4. 협력을 설계
 *  5. 객체들을 포괄하는 타입에 적절한 책임을 할당
 *  6. 구현
 */
public class CustomerTest {

    @DisplayName("메뉴 이름에 해당하는 요리를 주문을 한다.")
    @Test
    void orderTest() {
        Customer customer = new Customer();

        // 메뉴와 요리사를 전달하여 만두를 주문하는 역할
        Menu menu = new Menu(List.of(new MenuItem("돈가스",5000), new MenuItem("냉면",7000)));
        Cooking cooking = new Cooking();

        // 고객이 만두를 주문하고, 메뉴 항목을 전달하고, 요리사의 정보를 전달한다.
        assertThatCode(() ->customer.order("돈가스",menu,cooking))
                .doesNotThrowAnyException();
    }
}
