package org.example.DTO;

import org.example.Collection.LinkedList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DtoGrade {
    private String subject;
    private int grade;

    public DtoGrade(Connection connection, int grade_id) {
        try {
            PreparedStatement findStudentById = connection.prepareStatement(
                    "select * from grade g \n" +
                            "inner join subject s on g.subject_id = s.id \n" +
                            "where g.id = " + grade_id
            );

            ResultSet resultSet = findStudentById.executeQuery();
            if (resultSet.next()) {
                this.subject = resultSet.getString("name");
                this.grade = resultSet.getInt("grade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSubject(){
        return subject;
    }
    public int getGrade(){
        return grade;
    }
}
