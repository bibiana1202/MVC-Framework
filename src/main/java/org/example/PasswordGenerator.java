package org.example;

// 펑서녈 인터페이스라 구현체를 굳이 안만들어도 됨.

@FunctionalInterface
public interface PasswordGenerator {
    // 인터페이스를 통해서 구현체 가 어떤것인지는 모르겠어
    String generatePassword();
}
