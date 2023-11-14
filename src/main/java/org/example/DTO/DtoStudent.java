package org.example.DTO;

import java.text.DecimalFormat;

public class DtoStudent {                           // Класс для представления Ученика

    private String name;
    private String family;
    private Integer age;
    private Integer group;
    private Double midGrade;

    public DtoStudent(String name, String family, Integer age, Integer group, Double midGrade){
        this.name = name;
        this.family = family;
        this.age = age;
        this.group = group;
        this.midGrade = midGrade;
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
        DecimalFormat df = new DecimalFormat("#.###");
        System.out.print(family + " " + name +
                "; age: " + age +
                "; group: " + group +
                "; mid grade: " + df.format(midGrade));
    }
}

