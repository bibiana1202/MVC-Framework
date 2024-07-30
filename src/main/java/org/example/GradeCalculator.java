package org.example;

import java.util.GregorianCalendar;
import java.util.List;

public class GradeCalculator {
    //일급 컬렉션 사용
    private final Courses courses;
//    private final List<Course> courses ;

    public GradeCalculator(List<Course> courses){
        this.courses = new Courses(courses);
    }

    public GradeCalculator(Courses courses){
        this.courses = courses;
    }
    /**
     * 요구사항
     * 평균학점 계산 방법 = (학점수 * 교과목 평점) 의 합계/수강신청 총학점 수
     * 일급 컬렉션 사용
     */
    /**
     * 핵심 포인트
     * */
    // 이수한 과목 전달하여 평균학점 계산을 요청한다. ---> 학점 계산기 에게 요청
    //                                         ---> (학점수 * 교과목 평점) 의 합계 ----> 과목(코스) 에게 요청할수 있다.
    //                                         ---> 수강신청 총학점수             ----> 과목(코스) 에게 요청할수 있다.

    // 즉, 객체들끼리 메시지 전달을 통해서 자신이 다 모두 처리하는 것이 아니라 해당 값을 가진 객체에게 작업을 위임, 메시지를 통해서 결과값을 받아오고
    // 이를 통해서 최종적인 결과를 얻는 객체지향적인 프로그램 ~~ 끝~!

    public double calculateGrade() {
        //(학점수 * 교과목 평점) 의 합계
        double totalMultipliedCreditAndCourseGrade =courses.multiplyCreditAndCourseGrade();

        // 수강신청 총 학점수
        int totalCompletedCredit =courses.calculateTotalCompletedCredit();


        return totalMultipliedCreditAndCourseGrade/totalCompletedCredit;

    }
}
