package org.example.model;

import java.util.Objects;

public class User {
    private String userid;
    private String name;

    //constructor 에 userid,name 을 가진..
    public User(String userid, String name) {
        this.userid = userid;
        this.name = name;
    }

    public boolean equalsUser(User user) {
        return this.equals(user);
    }

    // 객체끼리 비교해주려면 equals and hash code 를 만들어줘야 !!(?)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userid, user.userid) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, name);
    }
}
