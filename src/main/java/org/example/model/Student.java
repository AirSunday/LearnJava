package org.example.model;

public class Student {                           // Класс для представления Ученика
    private String student_id;
    private String name;
    private String family;
    private Integer age;
    private Integer group;
    private Double midGrade;

    public Student(String student_id, String name, String family, Integer age, Integer group, Double midGrade){
        this.student_id = student_id;
        this.name = name;
        this.family = family;
        this.age = age;
        this.group = group;
        this.midGrade = midGrade;
    }

    public String getStudentId(){ return student_id; }
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
    public Double getMidGrade() { return midGrade; }
}

