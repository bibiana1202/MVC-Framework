package org.example;

public class User {

    public String password;



    public void initPassword(PasswordGenerator passwordGenerator){
        // 랜덤으로 만들어지는 --> 컨트롤 할수 없음 -> 인터페이스 만들기 PasswordGenerator

//        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
//        String randomPassword = randomPasswordGenerator.generatePassword();
        // --> 외부로부터 주입 받아보쟈
        // passwordGenerator 상위 인터페이스 로 받아보쟈
        // 인터페이스를 통해서 구현체 가 어떤것인지는 모르겠어
        String password = passwordGenerator.generatePassword();
        /**
         * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
         * */

        // alt+shift+click : 다중라인 선택
        // alt+j : 같은 단어 자동 선택
        // 랜덤 만들어지는 password 가 만족하면 if문 통과하여
        // 패스워드를 받고 해당 패스워드가 만족하면 세팅된다.
        if (password.length() >= 8 && password.length() <= 12){
            this.password = password; // password 세팅!
        }

        //UserTest 만드는 단축키 : ctrl + shift + t ( ctrl + n -> go to test)
    }


    // alt + insert : generator 단축키
    // Get
    public String getPassword() {
        return password;
    }
}
