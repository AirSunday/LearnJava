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
    @Override
    public Double fast_getMidGradeStudentByGroup(int group){
        return JDBCStorageService.TransactionScript.getInstance().fast_getMidGradeStudentByGroup(group);
    }
    @Override
    public LinkedList<Student> fast_printExcellentPersonsByOlderAge(int age){
        return JDBCStorageService.TransactionScript.getInstance().fast_printExcellentPersonsByOlderAge(age);
    }
    @Override
    public LinkedList<Student> fast_printPersonByFamily(String family){
        return JDBCStorageService.TransactionScript.getInstance().fast_printMidGradeByFamily(family);
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
                connection.setAutoCommit(false);

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
                        connection.commit();
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
            try {
                connection.setAutoCommit(false);

                PreparedStatement studentsByGroup = connection.prepareStatement(
                        "select * from student s \n" +
                                "inner join \"group\" g on s.group_id = g.id \n" +
                                "inner join grade gr on s.id = gr.student_id \n" +
                                "where g.number = ?");

                studentsByGroup.setInt(1, group);
                try(ResultSet resultSet = studentsByGroup.executeQuery();){
                    double sum = 0;
                    int countStudent = 0;
                    while (resultSet.next()) {      //Рассмотрим каждого студента отдельно
                        // Получаем id студента
                        double grade = resultSet.getDouble("grade");   // Узнаем среднюю оценку студента
                        sum += grade;
                        countStudent++;
                    }
                    if(countStudent != 0) {
                        DecimalFormat df = new DecimalFormat("#.###");
                        String formattedValue = df.format(sum / countStudent);

                        connection.commit();
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
            try {
                connection.setAutoCommit(false);

                PreparedStatement findStudentOrderAge = connection.prepareStatement(   // получаем всех студентов старше age
                        "select * from student s \n" +
                                "inner join \"group\" g on s.group_id= g.id \n" +
                                "where s.age > " + age );

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
                    connection.commit();
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
            try {          // поиск всех людей по префексу
                connection.setAutoCommit(false);

                PreparedStatement findStudentByFamily = connection.prepareStatement(   // получаем всех студентов старше age
                        "select * from student s \n" +
                                "inner join \"group\" g on s.group_id = g.id \n" +
                                "where s.family LIKE  \'" + family + "%\'");

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

                    connection.commit();
                    return students;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public Double fast_getMidGradeStudentByGroup(int group){             // Подсчет средней оуенки по группе
            try  {
                connection.setAutoCommit(false);

                PreparedStatement studentsByGroup = connection.prepareStatement(
                        "select * from student s \n" +
                                "inner join \"group\" g on s.group_id = g.id \n" +
                                "inner join mid_grade gr on s.id = gr.student_id \n" +
                                "where g.number = ?");

                studentsByGroup.setInt(1, group);
                try(ResultSet resultSet = studentsByGroup.executeQuery();){
                    double sum = 0;
                    int countStudent = 0;
                    while (resultSet.next()) {      //Рассмотрим каждого студента отдельно
                        // Получаем id студента
                        double grade = resultSet.getDouble("grade");   // Узнаем среднюю оценку студента
                        sum += grade;
                        countStudent++;
                    }
                    if(countStudent != 0) {
                        DecimalFormat df = new DecimalFormat("#.###");
                        String formattedValue = df.format(sum / countStudent);

                        connection.commit();
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

        public LinkedList<Student> fast_printExcellentPersonsByOlderAge(int age) {
            LinkedList<Student> students = new LinkedList<>();
            try {
                connection.setAutoCommit(false);

                PreparedStatement findStudentOrderAge = connection.prepareStatement(   // получаем всех студентов старше age
                        "select * from student s \n" +
                                "inner join \"group\" g on s.group_id= g.id \n" +
                                "inner join mid_grade gr on gr.student_id = s.id \n" +
                                "where s.age > " + age );

                try (ResultSet resultSet = findStudentOrderAge.executeQuery()){
                    while (resultSet.next()){
                        //получаем id ученика
                        String student_id = String.valueOf(resultSet.getInt("id"));
                        double grade = resultSet.getDouble("grade");   // Узнаем среднюю оценку студента

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
                    connection.commit();
                    return students;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public LinkedList<Student> fast_printMidGradeByFamily(String family){
            LinkedList<Student> students = new LinkedList<>();
            try {          // поиск всех людей по префексу
                connection.setAutoCommit(false);

                PreparedStatement findStudentByFamily = connection.prepareStatement(   // получаем всех студентов старше age
                        "select * from student s \n" +
                                "inner join \"group\" g on s.group_id = g.id \n" +
                                "inner join mid_grade gr on gr.student_id = s.id \n" +
                                "where s.family LIKE  \'" + family + "%\'");

                try (ResultSet resultSet = findStudentByFamily.executeQuery()){
                    while (resultSet.next()){
                        //получаем id ученика
                        String student_id = String.valueOf(resultSet.getInt("id"));
                        double grade = resultSet.getDouble("grade");   // Узнаем среднюю оценку студента

                        students.add(new Student(
                                String.valueOf(resultSet.getInt("id")),
                                resultSet.getString("name"),
                                resultSet.getString("family"),
                                resultSet.getInt("age"),
                                resultSet.getInt("number"),
                                grade
                        ));
                    }
                    connection.commit();
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
