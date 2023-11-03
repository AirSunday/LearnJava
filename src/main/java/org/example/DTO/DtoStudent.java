package org.example.DTO;

import org.example.Collection.LinkedList;
import org.example.model.ModelGrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DtoStudent {                           // Класс для представления Ученика

    private String name;
    private String family;
    private Integer age;
    private Integer group;
    private LinkedList<DtoGrade> grades;

    public DtoStudent(Connection connection, int student_id){

        try {
            PreparedStatement findStudentById = connection.prepareStatement(
                    "select * from student s\n" +
                            "inner join \"group\" g on s.group_id = g.id \n" +
                            "where s.id = " + student_id
            );

            ResultSet resultSet = findStudentById.executeQuery();
            if (resultSet.next()) {
                this.name = resultSet.getString("name");
                this.family = resultSet.getString("family");
                this.age = resultSet.getInt("age");
                this.group = resultSet.getInt("number");
                setGrades(connection, student_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DtoStudent(Connection connection, int student_id, String name, String family, Integer age, Integer group){
        this.name = name;
        this.family = family;
        this.age = age;
        this.group = group;

        setGrades(connection, student_id);
    }

    private void setGrades(Connection connection, int student_id){
        try {
            PreparedStatement findGradesByStudent = connection.prepareStatement(
                    "select * from grade where student_id = " + student_id
            );

            ResultSet resultSetGrade = findGradesByStudent.executeQuery();
            grades = new LinkedList<>();
            while (resultSetGrade.next()){
                grades.add(
                        new DtoGrade(connection, resultSetGrade.getInt("id"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

    public LinkedList<DtoGrade> getGrades() {
        return grades;
    }
}

