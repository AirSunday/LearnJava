package org.example.Model;

import org.example.DTO.DtoStudent;

import java.text.DecimalFormat;

public class ModelStudent {                           // Класс для представления Ученика

    private String name;
    private String family;
    private Integer age;
    private Integer group;
    private Double midGrade;

    public ModelStudent(String name, String family, Integer age, Integer group, Double midGrade){
        this.name = name;
        this.family = family;
        this.age = age;
        this.group = group;
        this.midGrade = midGrade;
    }

    public ModelStudent(DtoStudent dtoStudent){
        this.name = dtoStudent.getName();
        this.family = dtoStudent.getFamily();
        this.age = dtoStudent.getAge();
        this.group = dtoStudent.getGroup();
        this.midGrade = dtoStudent.getMidGrade();
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

    public double getMidGrade() {
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

