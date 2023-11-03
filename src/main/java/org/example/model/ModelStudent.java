package org.example.model;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.DTO.DtoGrade;
import org.example.DTO.DtoStudent;

import java.text.DecimalFormat;

public class ModelStudent {                           // Класс для представления Ученика

    private String name;
    private String family;
    private Integer age;
    private Integer group;
    private LinkedList<ModelGrade> grades;

    public ModelStudent(String name, String family, Integer age, Integer group, LinkedList<ModelGrade> grades){
        this.name = name;
        this.family = family;
        this.age = age;
        this.group = group;
        this.grades = grades;
    }

    public ModelStudent(DtoStudent dtoStudent){
        this.name = dtoStudent.getName();
        this.family = dtoStudent.getFamily();
        this.age = dtoStudent.getAge();
        this.group = dtoStudent.getGroup();
        this.grades = new LinkedList<>();

        Node<DtoGrade> dtoGradeNode = dtoStudent.getGrades().getHead();
        while(dtoGradeNode != null){
            this.grades.add(new ModelGrade(dtoGradeNode.getData()));
            dtoGradeNode = dtoGradeNode.getNext();
        }
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

    public LinkedList<ModelGrade> getGrades() {
        return grades;
    }

    public void print(){
        System.out.print(family + " " +
                         name + "; age: " +
                         age + "; group: " +
                         group + "; mid grade: " +
                         getMidGrade());
    }

    public Double getMidGrade(){
        Node<ModelGrade> grade = grades.getHead();

        double sum = 0;
        while (grade != null){
            sum += grade.getData().getGrade();
            grade = grade.getNext();
        }

        DecimalFormat df = new DecimalFormat("#.###");
        String formattedValue = df.format(sum / grades.size());
        return Double.parseDouble(formattedValue.replace(',', '.'));
    }

}

