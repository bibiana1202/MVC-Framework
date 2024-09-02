package org.example;


// 해당 클래스는 passwodGenerator 인터페이스를 구현한 클래스
public class CorrectFixedPasswodGenerator implements PasswordGenerator{

    @Override
    public String generatePassword() {
        return "abcdefgh"; // 8글자 생성해주는 올바르게 고정된 패스워드 제너레이터를 만듬
    }
}
