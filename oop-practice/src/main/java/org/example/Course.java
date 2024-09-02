package org.example;


public class Course {
    private final String subject; // 과목명
    private final int credit;     // 학점
    private final String grade;      // 성적

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;

    }

    //getter 이용하는 방식(사용하는 곳곳마다 찾아가서 바꿔줘야함)
    //이 아니라 메시지를 전달해서 해당 객체에게 작업을 위임하는 형태로 변경
    public double multiplyCreditAndCourseGrade() {
        return credit * getGradeToNumber(); //+ 1.0;
    }

    public int getCredit() {
        return credit;
    }

    public double getGradeToNumber() {
        double grade = 0;
        switch (this.grade) {
            case "A+":
                grade = 4.5;
                break;
            case "A":
                grade = 4.0;
                break;
            case "B+":
                grade = 3.5;
                break;
            case "B":
                grade = 3.0;
                break;
            case "C+":
                grade = 2.5;
                break;
            case "C":
                grade = 2.0;
                break;
        }
        return grade;
    }


}
