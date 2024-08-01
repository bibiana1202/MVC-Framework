package org.example;

import java.util.List;

import static java.util.Arrays.stream;

public class Courses {
// 일급 콜렉션 : Course를 여러개 가진 리스트 형태로 courses 정보만 인스턴스 변수로 가지는 클래스
    // 변수는 하나만!
    private final List<Course> courses ;

    // 생성자
    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {

        //리팩토링
        return courses.stream()
                .mapToDouble(Course::multiplyCreditAndCourseGrade)
                .sum();

//        //(학점수 * 교과목 평점) 의 합계
//
//        double multipliedCreditAndCourseGrade = 0;
//
//        for(Course course : courses){
//            // getter를 통해서 정보를 가지고 와서 처리하는 방식
//            //multipliedCreditAndCourseGrade +=  course.getCredit() * course.getGradeToNumber();
//            // 해당 데이터를 가진 객체에게 메시지를 던져서 작업을 처리
//            multipliedCreditAndCourseGrade +=  course.multiplyCreditAndCourseGrade();
//
//        }
//        return multipliedCreditAndCourseGrade;

    }

    public int calculateTotalCompletedCredit() {
        // 수강신청 총 학점수
        return courses.stream()
                .mapToInt(Course::getCredit)
                .sum();
    }
}
