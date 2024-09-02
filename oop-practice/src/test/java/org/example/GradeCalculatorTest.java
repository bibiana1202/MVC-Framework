package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 요구사항
 * 평균학점 계산 방법 = (학점수 * 교과목 평점) 의 합계/수강신청 총학점 수
 * 일급 컬렉션 사용
 */
public class GradeCalculatorTest {
    // 학점계산기 도메인 : 이수한 과목, 학점 계산기 객체들이 필요하지 않을까?
    // 학점 계산기가 이수한 과목들을 가지고 학점을 계산하면 되겠다
    // 즉, 학점계산기가 이수한 과목을 인스턴스로 변수로 가지면서 평균학점을 계산할수 있을것 같다.
    // 우리가 이수한 과목에는 어떤것들이 있을 수 있을까? => 객체지향, 자료구조 , 중국어 회화 수업 이수 ==> 객체는 3가지
    // ==> 과목(코스) 라는 클래스로 표현할수 있다.
    /**
     * 핵심 포인트
     * */
    // 이수한 과목 전달하여 평균학점 계산을 요청한다. ---> 학점 계산기 에게 요청
    //                                         ---> (학점수 * 교과목 평점) 의 합계 ----> 과목(코스) 에게 요청할수 있다.
    //                                         ---> 수강신청 총학점수             ----> 과목(코스) 에게 요청할수 있다.



    //(코스에는 해당 자료구조가 3학점인지 2학점인지 정보를 가지고 있다. 
    // 이 코스정보에는 자신이 받은 평점 또한 가지고 있다. 
    // 즉, 이수한 과목들을 전달 받은 학점계산기는 학점수와 교과목 평점을 알고있는 과목 코스에게 
    // 이 합계에대한 요청을 해서 이들에대한 학점을 받고 
    // 또한, 이수한 과목들의 총 학점수 또한 전달받은 이수환 과목들을 통해서 구할수 있기 때문에 
    // 이들을 통해서 각각 (학점수*교과목의 평점)의 합계 왕 수강신청 총학점수를 구하여 나누기만 통한다음에 응답값을 전달하는 구조)
    // 이들을 통해서 적당한 책임 할당


    @DisplayName("평균 학점을 계산한다.")
    @Test
    void calculateGradeTest() {
        // 코스 정보를 만들었으면(Course), 이수한 과목을 이용하는 평균학점 계산기 에 대한 테스트 코드 작성
        List<Course> courses = List.of(new Course("OOP",3,"A+"),
                new Course("자료구조",3,"A+"));

        // 학점 계산기 에게 학점계산기가 생성될때 이수한 과목들을 전달하기
        GradeCalculator gradeCalculator = new GradeCalculator(new Courses(courses));
        // 그리고 전달받은 정보 이수한 과목들을 가지고 GRADE 성적을 계산하기
        double  gradeResult = gradeCalculator.calculateGrade();

        assertThat(gradeResult).isEqualTo(4.5);



    }
}
