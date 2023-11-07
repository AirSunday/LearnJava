package org.example.Model;

import org.example.DTO.DtoGrade;
import org.example.DTO.DtoStudent;

public class ModelGrade {
    private String subject;
    private int grade;
    private ModelStudent student;

    public ModelGrade(String subject, int grade) {
        this.grade = grade;
        this.subject = subject;
    }

    public ModelGrade(DtoGrade dtoGrade) {
        this.grade = dtoGrade.getGrade();
        this.subject = dtoGrade.getSubject();
        this.student = new ModelStudent(dtoGrade.getStudent());
    }

    public String getSubject(){
        return subject;
    }
    public int getGrade(){
        return grade;
    }
    public ModelStudent getStudent(){
        return student;
    }
}
