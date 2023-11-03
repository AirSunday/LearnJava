package org.example.model;

import org.example.DTO.DtoGrade;

public class ModelGrade {
    private String subject;
    private int grade;

    public ModelGrade(String subject, int grade) {
        this.grade = grade;
        this.subject = subject;
    }

    public ModelGrade(DtoGrade dtoGrade) {
        this.grade = dtoGrade.getGrade();
        this.subject = dtoGrade.getSubject();
    }

    public String getSubject(){
        return subject;
    }
    public int getGrade(){
        return grade;
    }
}
