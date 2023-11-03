package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DTO.DtoStudent;
import org.example.DTO.DtoStudentFast;
import org.example.DataLoader.JDBCStudentsDataLoader;
import org.example.model.ModelStudent;
import org.example.model.ModelStudentFast;

import java.sql.*;
import java.text.DecimalFormat;

public class JDBCStorageService implements StorageService {

    @Override
    public void fillDB(){
        JDBCStorageService.TransactionScript.getInstance().fillDB();
    }
    @Override
    public LinkedList<ModelStudent> getStudentsByGroup(int group){
        return JDBCStorageService.TransactionScript.getInstance().getStudentsByGroup(group);
    }
    @Override
    public LinkedList<ModelStudent> getPersonsByOlderAge(int age){
        return JDBCStorageService.TransactionScript.getInstance().getPersonsByOlderAge(age);
    }
    @Override
    public LinkedList<ModelStudent> getPersonByFamily(String family){
        return JDBCStorageService.TransactionScript.getInstance().getPersonByFamily(family);
    }
    @Override
    public LinkedList<ModelStudentFast> fast_getStudentsByGroup(int group){
        return JDBCStorageService.TransactionScript.getInstance().fast_getStudentsByGroup(group);
    }
    @Override
    public LinkedList<ModelStudentFast> fast_getPersonsByOlderAge(int age){
        return JDBCStorageService.TransactionScript.getInstance().fast_getPersonsByOlderAge(age);
    }
    @Override
    public LinkedList<ModelStudentFast> fast_getPersonByFamily(String family){
        return JDBCStorageService.TransactionScript.getInstance().fast_getPersonByFamily(family);
    }

    public static final class TransactionScript {
        private final String url      = "jdbc:postgresql://localhost:5432/LearnJava";
        private final String login    = "postgres";
        private final String password = "123";
        private Connection connection;
        private static final TransactionScript instance = new TransactionScript();

        public static TransactionScript getInstance() {
            return instance;
        }

        public TransactionScript()
        {
            try {
                connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException e)
            {
                e.printStackTrace();
            };
        }

        public void fillDB() {
            JDBCStudentsDataLoader JDBCStudentsDataLoader = new JDBCStudentsDataLoader(connection);
            JDBCStudentsDataLoader.fillDB();
        }

        public LinkedList<ModelStudent> getStudentsByGroup(int group) {
            return getPerson("select * from student s \n" +
                    "inner join \"group\" g on s.group_id = g.id \n" +
                    "where g.number = " + group);
        }

        public LinkedList<ModelStudent> getPersonsByOlderAge(int age) {
            return getPerson("select * from student s \n" +
                                        "inner join \"group\" g on s.group_id = g.id \n" +
                                        "where s.age >= " + age);
        }

        public LinkedList<ModelStudent> getPersonByFamily(String family){
            return getPerson("select * from student s \n" +
                                        "inner join \"group\" g on s.group_id = g.id \n" +
                                        "where s.family LIKE  \'" + family + "%\'");
        }

        public LinkedList<ModelStudentFast> fast_getStudentsByGroup(int group){

            return fast_getPerson("select * from student s \n" +
                                                "inner join \"group\" g on s.group_id = g.id \n" +
                                                "inner join mid_grade mg on mg.student_id = s.id \n" +
                                                "where g.number = " + group);
        }

        public LinkedList<ModelStudentFast> fast_getPersonsByOlderAge(int age) {
            return fast_getPerson("select * from student s \n" +
                                                "inner join \"group\" g on s.group_id = g.id \n" +
                                                "inner join mid_grade mg on mg.student_id = s.id \n" +
                                                "where s.age >= " + age + "\n" +
                                                "and mg.grade = 5");
        }

        public LinkedList<ModelStudentFast> fast_getPersonByFamily(String family){
            return fast_getPerson("select * from student s \n" +
                    "inner join \"group\" g on s.group_id = g.id \n" +
                    "inner join mid_grade mg on mg.student_id = s.id \n" +
                    "where s.family LIKE  \'" + family + "%\'");
        }

        private LinkedList<ModelStudent> getPerson(String searchLine){
            try {
                connection.setAutoCommit(false);

                LinkedList<ModelStudent> students = new LinkedList<>();

                PreparedStatement studentsByGroup = connection.prepareStatement(searchLine);

                try(ResultSet resultSet = studentsByGroup.executeQuery();){
                    while (resultSet.next()) {      //Рассмотрим каждого студента отдельно
                        DtoStudent student = new DtoStudent(connection,
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("family"),
                                resultSet.getInt("age"),
                                resultSet.getInt("number"));

                        students.add(new ModelStudent(student));
                    }
                }
                connection.commit();
                return students;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        }

        private LinkedList<ModelStudentFast> fast_getPerson(String searchLine){
            try {
                connection.setAutoCommit(false);

                LinkedList<ModelStudentFast> students = new LinkedList<>();

                PreparedStatement studentsByGroup = connection.prepareStatement(searchLine);

                try(ResultSet resultSet = studentsByGroup.executeQuery();){
                    while (resultSet.next()) {      //Рассмотрим каждого студента отдельно
                        DtoStudentFast student = new DtoStudentFast(connection,
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("family"),
                                resultSet.getInt("age"),
                                resultSet.getInt("number"),
                                resultSet.getDouble("grade"));

                        students.add(new ModelStudentFast(student));
                    }
                }
                connection.commit();
                return students;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        }
    }
}
