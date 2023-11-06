package org.example.DTO;

public class DtoGrade {
    private String subject;
    private int grade;

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
}
