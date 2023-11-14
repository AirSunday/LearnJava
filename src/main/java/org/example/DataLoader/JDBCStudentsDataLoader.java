package org.example.DataLoader;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.DTO.DtoGrade;
import org.example.DTO.DtoStudent;
import org.example.Service.SqlScript;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class JDBCStudentsDataLoader implements JDBCDataLoader {

    Connection connection;

    public JDBCStudentsDataLoader(Connection connection){
        this.connection = connection;
    }
    @Override
    public void fillDB() {
        clearDB();
        fillGroup();
        fillSubject();
        fillStudent();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Обработка исключений
            }
        }
        }
    public void clearDB() {         // очишаем все таблицы
        try {
            connection.setAutoCommit(false);
            String[] tables = {"student", "grade", "group", "subject"};
            for (String table : tables) {
                clearTable(table);
            }
            connection.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void fillGroup(){        // добавляем классы 1 - 12
        try {
            connection.setAutoCommit(false);
            for(int i = 1; i <= 12; i++) {
                addGroup(i);
            }
            connection.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void fillSubject(){      // добавляем предметы
        try {
            connection.setAutoCommit(false);
            String[] subjects = {"физика", "математика", "русский", "литература", "геометрия", "информатика"};
            for (String subject : subjects) {
                addSubject(subject);
            }
            connection.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void fillStudent(){
        System.out.println("Началось чтение файла и запись в БД...");
        String csvFile = "src/main/java/org/example/Data/students.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true; // Флаг для пропуска первой строки

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Пропускаем первую строку
                }

                String[] record = line.split(";");

                if (record.length == 10) {
                    String family = record[0];
                    String name = record[1];
                    int age = Integer.parseInt(record[2]);
                    int group = Integer.parseInt(record[3]);
                    int physics = Integer.parseInt(record[4]);
                    int mathematics = Integer.parseInt(record[5]);
                    int rus = Integer.parseInt(record[6]);
                    int literature = Integer.parseInt(record[7]);
                    int geometry = Integer.parseInt(record[8]);
                    int informatics = Integer.parseInt(record[9]);

                    LinkedList<DtoGrade> grades = new LinkedList<>();
                    grades.add(new DtoGrade("физика", physics));
                    grades.add(new DtoGrade("математика", mathematics));
                    grades.add(new DtoGrade("русский", rus));
                    grades.add(new DtoGrade("литература", literature));
                    grades.add(new DtoGrade("геометрия", geometry));
                    grades.add(new DtoGrade("информатика", informatics));

                    DtoStudent student = new DtoStudent(name, family, age, group, 0.0);

                    addStudent(student, grades);

                } else {
                    System.out.println("Ошибка в записи: Недостаточно столбцов.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Закончилось чтение файла");
    }
    public void clearTable(String table){
        try (
                PreparedStatement clearTable = connection.prepareStatement(
                        SqlScript.TRUNCATE_TABLE
                );
        ){
            clearTable.setString(1, table);
            clearTable.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addGroup(int group) {
        try(
                PreparedStatement addGroup = connection.prepareStatement(
                        SqlScript.INSERT_GROUP
                );
        ){
            addGroup.setInt(1, group);
            addGroup.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addSubject(String subject) {

        try (
                PreparedStatement addSubject = connection.prepareStatement(
                        SqlScript.INSERT_SUBJECT
                );
        ){
            addSubject.setString(1, subject);
            addSubject.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addStudent(DtoStudent student, LinkedList<DtoGrade> grades) {
        try (
                PreparedStatement findGroupId = connection.prepareStatement(
                        SqlScript.SELECT_ID_FROM_GROUP_BY_NUMBER
                );
                PreparedStatement addStudent = connection.prepareStatement(
                        SqlScript.INSERT_STUDENT,
                        Statement.RETURN_GENERATED_KEYS
                );
        ){
            connection.setAutoCommit(false);
            findGroupId.setInt(1, student.getGroup());

            try (
                    ResultSet resultSet = findGroupId.executeQuery();
            ){
                if (resultSet.next()) {
                    int group_id = resultSet.getInt("id");

                    addStudent.setString(1, student.getName());
                    addStudent.setString(2, student.getFamily());
                    addStudent.setInt(3, student.getAge());
                    addStudent.setInt(4, group_id);

                    int affectedRows = addStudent.executeUpdate();

                    if (affectedRows > 0) {
                        ResultSet studentKeys = addStudent.getGeneratedKeys();
                        if (studentKeys.next()) {
                            int student_id = studentKeys.getInt(1); // Извлекаем значение первого сгенерированного ключа
                            addGrade(student_id, grades); // Записываем все оценки ученика
                            connection.commit();
                        }
                    } else {
                        connection.rollback();
                    }
                }
                else {
                    connection.rollback();
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addGrade(int student_id, LinkedList<DtoGrade> grades) {
        try(
                PreparedStatement findSubjectIdByName = connection.prepareStatement(
                        SqlScript.SELECT_ID_FROM_SUBJECT_BY_NAME
                );
                PreparedStatement addGrade = connection.prepareStatement(
                        SqlScript.INSERT_GRADE
                );
        ){

            Node<DtoGrade> modelGradeNode = grades.getHead();

            while (modelGradeNode != null){

                String subject = modelGradeNode.getData().getSubject();
                int grade = modelGradeNode.getData().getGrade();

                findSubjectIdByName.setString(1, subject);
                try(
                        ResultSet resultSet = findSubjectIdByName.executeQuery();
                ){
                    if (resultSet.next()) {
                        int subject_id = resultSet.getInt("id");
                        addGrade.setInt(1, grade);
                        addGrade.setInt(2, subject_id);
                        addGrade.setInt(3, student_id);
                        addGrade.addBatch(); // Добавить операцию в пакет
                    }
                }
                modelGradeNode = modelGradeNode.getNext();
            }
            addGrade.executeBatch();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}