package org.example.mvc.repository;

import org.example.mvc.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// 인메모리를 사용할것 , 자료구조를 이용해서 값을 저장
public class UserRepository {
    private static Map<String, User> users = new HashMap<>();
    public static void save(User user) {
        users.put(user.getUserId(),user);
    }

    public static Collection<User> findAll() {
        return users.values();

    }
}
