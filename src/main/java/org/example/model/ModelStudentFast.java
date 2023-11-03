package org.example.model;

import org.example.DTO.DtoStudentFast;

public class ModelStudentFast {                           // Класс для представления Ученика

    private String name;
    private String family;
    private Integer age;
    private Integer group;
    private Double midGrade;

    public ModelStudentFast(String name, String family, Integer age, Integer group, Double midGrade){
        this.name = name;
        this.family = family;
        this.age = age;
        this.group = group;
        this.midGrade = midGrade;
    }

    public ModelStudentFast(DtoStudentFast dtoStudent){
        this.name = dtoStudent.getName();
        this.family = dtoStudent.getFamily();
        this.age = dtoStudent.getAge();
        this.group = dtoStudent.getGroup();
        this.midGrade = dtoStudent.getMidGrades();
    }
    public String getName(){ return name; }
    public String getFamily(){
        return family;
    }
    public Integer getAge(){
        return age;
    }
    public Integer getGroup(){
        return group;
    }

    public Double getMidGrade() {
        return midGrade;
    }

    public void print(){
        System.out.print(family + " " +
                name + "; age: " +
                age + "; group: " +
                group + "; mid grade: " +
                midGrade);
    }

}

