package org.example.DTO;

import org.example.Collection.LinkedList;
import org.example.model.ModelGrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DtoStudentFast {                           // Класс для представления Ученика

    private String name;
    private String family;
    private Integer age;
    private Integer group;
    private Double midGrades;

    public DtoStudentFast(Connection connection, int student_id){

        try {
            PreparedStatement findStudentById = connection.prepareStatement(
                    "select * from student s\n" +
                            "inner join \"group\" g on s.group_id = g.id \n" +
                            "inner join mid_grade mg on mg.student_id = s.id \n" +
                            "where s.id = " + student_id
            );

            ResultSet resultSet = findStudentById.executeQuery();
            if (resultSet.next()) {
                this.name = resultSet.getString("name");
                this.family = resultSet.getString("family");
                this.age = resultSet.getInt("age");
                this.group = resultSet.getInt("number");
                this.midGrades = resultSet.getDouble("grade");;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DtoStudentFast(Connection connection, int student_id, String name, String family, Integer age, Integer group, Double midGrades){
        this.name = name;
        this.family = family;
        this.age = age;
        this.group = group;
        this.midGrades = midGrades;
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

    public Double getMidGrades() {
        return midGrades;
    }
}

