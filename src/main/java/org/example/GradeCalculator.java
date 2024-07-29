package org.example;

import java.util.List;

public class GradeCalculator {

    private final List<Course> courses ;

    public GradeCalculator(List<Course> courses){
        this.courses = courses;
    }

    public double calculateGrade() {
        return 4.5 ;
        // 다음시간~ 해당 메스드 구현부분 채워놓기
    }
}
