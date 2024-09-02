package org.example;

import java.util.List;

public class Menu {
    private final List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuItem choose(String name) {
//        return new MenuItem("돈가스", 5000);
        return this.menuItems.stream()
                .filter(menuItem -> menuItem.matches(name))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("잘못된 메뉴이름 입니다."));
    }
}
