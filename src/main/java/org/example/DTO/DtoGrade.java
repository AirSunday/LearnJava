package org.example.DTO;

public class DtoGrade {
    private String subject;
    private int grade;
    private DtoStudent student;

    public DtoGrade() {
    }

    public DtoGrade(String subject, int grade, DtoStudent student) {
        this.grade = grade;
        this.subject = subject;
        this.student = student;
    }

    public DtoGrade(String subject, int grade) {
        this.grade = grade;
        this.subject = subject;
    }

    public String getSubject(){
        return subject;
    }
    public int getGrade(){
        return grade;
    }
    public DtoStudent getStudent(){
        return student;
    }
}
