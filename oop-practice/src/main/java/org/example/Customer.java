package org.example;

public class Customer {

    public void order(String menuName ,Menu menu, Cooking cooking)
    {
        // 메뉴를 통해  전달된 메뉴이름에 해당하는 메뉴 항목을 선택,

        MenuItem menuItem = menu.choose(menuName);

        // 요리사에게 해당 메뉴아이템에 대한 요리를 만들어 달라고 요청
        Cook cook = cooking.makeCook(menuItem);
    }
}
