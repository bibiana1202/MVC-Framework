package org.example;


//import org.example.password.RandomPasswordGenerator;
// 같은 패키지에 없으면 import 가 생기지만,(org.example.password)
// 같은 패키지에 있으면 import 가 없어진다.(org.example)
public class User {

    public String password;
    // git > show history


    public void initPassword(PasswordGenerator passwordGenerator){
        // as-is
        // 랜덤으로 만들어지는 --> 컨트롤 할수 없음 -> 인터페이스 만들기 PasswordGenerator
        // 내부에서 생성하는경우 에는 강한 결합 -> 해당 부분에서 영향을 많이 받음
        // --> 조금의 결합을 끊기 위해서 상위의 인터페이스(PasswordGenerator)를 뒀다!
        // --> 그렇기 때문에 더이상 랜덤 패스워드 제너레이터에 대해서 의존을 가지는게 아니라 RandomPasswordGenerator 에 import도 없다.
        // ==> 강결합 이었던 것을 더 낮은 결합 패스워드
        // 그래서 실제로 랜덤 패스워드 제너레이터도 들어올수 있고,correctfixed, wrongfixed 패스워드 제너레이터도 들어올수 있음!
        // 해당 부분을 여기서 컨트롤 할수 있더 더 낮은 결합을 가진 설계를 얻을수 있다.
//        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
//        String randomPassword = randomPasswordGenerator.generatePassword();

        // to-be
        // 낮은 결합
        // --> 외부로부터 주입 받아보쟈
        // passwordGenerator 상위 인터페이스 로 받아보쟈
        // 인터페이스를 통해서 구현체가 어떤것인지는 모르겠어 = CorrectFixedPasswordGenerator or WrongFixedPasswordGenerator 가 들어옴
        // ==> 테스트하기 쉬운 코드를 작성하다 보니 랜덤 패스워들 제너레이터에 대한 의존성을 제거할수 있다. == 강 결합을 제거할수 있다. == 낮은 결합도를 가진 설계를 얻을수 있음
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
