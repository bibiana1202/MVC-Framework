package org.example;

// 해당 클래스는 passwodGenerator 인터페이스를 구현한 클래스
public class WrongFixedPasswodGenerator implements PasswordGenerator{

    @Override
    public String generatePassword() {
        return "ab"; // 2글자

    }
}
