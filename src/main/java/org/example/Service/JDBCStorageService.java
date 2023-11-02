package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DataLoader.JDBCStudentsDataLoader;
import org.example.model.Student;

import java.sql.*;
import java.text.DecimalFormat;

public class JDBCStorageService implements StorageService {

    @Override
    public void fillDB(){
        JDBCStorageService.TransactionScript.getInstance().fillDB();
    }
    @Override
    public Double getMidGradeStudentByGroup(int group){
        return JDBCStorageService.TransactionScript.getInstance().getMidGradeStudentByGroup(group);
    }
    @Override
    public LinkedList<Student> printExcellentPersonsByOlderAge(int age){
        return JDBCStorageService.TransactionScript.getInstance().printExcellentPersonsByOlderAge(age);
    }
    @Override
    public LinkedList<Student> printPersonByFamily(String family){
        return JDBCStorageService.TransactionScript.getInstance().printMidGradeByFamily(family);
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

        public Double getMidGradeByStudentId(String student_id){                //получить среднюю оценку ученика
            try {
                PreparedStatement findGradesByStudent = connection.prepareStatement(
                        "select * from grade where student_id = " + student_id
                );

                try(ResultSet resultSet = findGradesByStudent.executeQuery()){
                    double sum = 0;
                    int countSubjects = 0;
                    while (resultSet.next()) {          // Получаем список всех оценок ученика и считаем среднюю
                        int grade = resultSet.getInt("grade");
                        sum += grade;
                        countSubjects++;
                    }

                    if(countSubjects != 0) {
                        DecimalFormat df = new DecimalFormat("#.###");
                        String formattedValue = df.format(sum / countSubjects);
                        return Double.parseDouble(formattedValue.replace(',', '.'));
                    }
                    return 0.0;
                }
            }
            catch (SQLException e){
                e.printStackTrace();
                return 0.0;
            }
        }

        public Double getMidGradeStudentByGroup(int group){             // Подсчет средней оуенки по группе
            try (PreparedStatement studentsByGroup = connection.prepareStatement(
                    "select * from students s \n" +
                            "inner join \"group\" g on s.group = g.id \n" +
                            "where g.number = ?");) {

                studentsByGroup.setInt(1, group);
                try(ResultSet resultSet = studentsByGroup.executeQuery();){
                    double sum = 0;
                    int countStudent = 0;
                    while (resultSet.next()) {      //Рассмотрим каждого студента отдельно
                        // Получаем id студента
                        String student_id = String.valueOf(resultSet.getInt("id"));

                        Double grade = getMidGradeByStudentId(student_id);   // Узнаем среднюю оценку студента
                        sum += grade;
                        countStudent++;
                    }
                    if(countStudent != 0) {
                        DecimalFormat df = new DecimalFormat("#.###");
                        String formattedValue = df.format(sum / countStudent);
                        return Double.parseDouble(formattedValue.replace(',', '.'));
                    }
                    return 0.0;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return 0.0;
            }
        }

        public LinkedList<Student> printExcellentPersonsByOlderAge(int age) {
            LinkedList<Student> students = new LinkedList<>();
            try (PreparedStatement findStudentOrderAge = connection.prepareStatement(   // получаем всех студентов старше age
                    "select * from student s \n" +
                            "inner join \"group\" g on s.group = g.id \n" +
                            "where s.age > " + age );){

                try (ResultSet resultSet = findStudentOrderAge.executeQuery()){
                    while (resultSet.next()){
                        //получаем id ученика
                        String student_id = String.valueOf(resultSet.getInt("id"));
                        Double grade = getMidGradeByStudentId(student_id);   // Узнаем среднюю оценку студента

                        if(grade == 5.0){
                            students.add(new Student(
                                    String.valueOf(resultSet.getInt("id")),
                                    resultSet.getString("name"),
                                    resultSet.getString("family"),
                                    resultSet.getInt("age"),
                                    resultSet.getInt("number"),
                                    grade
                            ));
                        }
                    }
                    return students;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public LinkedList<Student> printMidGradeByFamily(String family){
            LinkedList<Student> students = new LinkedList<>();
            try (PreparedStatement findStudentByFamily = connection.prepareStatement(   // получаем всех студентов старше age
                    "select * from student s \n" +
                            "inner join \"group\" g on s.group = g.id \n" +
                            "where s.family LIKE  \'" + family + "%\'");){          // поиск всех людей по префексу

                try (ResultSet resultSet = findStudentByFamily.executeQuery()){
                    while (resultSet.next()){
                        //получаем id ученика
                        String student_id = String.valueOf(resultSet.getInt("id"));
                        Double grade = getMidGradeByStudentId(student_id);   // Узнаем среднюю оценку студента

                        students.add(new Student(
                                String.valueOf(resultSet.getInt("id")),
                                resultSet.getString("name"),
                                resultSet.getString("family"),
                                resultSet.getInt("age"),
                                resultSet.getInt("number"),
                                grade
                        ));
                    }
                    return students;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
