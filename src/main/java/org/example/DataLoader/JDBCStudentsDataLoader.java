package org.example.DataLoader;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.model.ModelGrade;
import org.example.model.ModelStudent;

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

                    LinkedList<ModelGrade> grades = new LinkedList<>();
                    grades.add(new ModelGrade("физика", physics));
                    grades.add(new ModelGrade("математика", mathematics));
                    grades.add(new ModelGrade("русский", rus));
                    grades.add(new ModelGrade("литература", literature));
                    grades.add(new ModelGrade("геометрия", geometry));
                    grades.add(new ModelGrade("информатика", informatics));

                    ModelStudent student = new ModelStudent(name, family, age, group, grades);

                    addStudent(student);

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
        try {
            Statement clearDB = connection.createStatement();
            clearDB.executeUpdate("TRUNCATE FROM \"" + table + "\"");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addGroup(int group) {
        try {
            PreparedStatement addGroup = connection.prepareStatement(           // ставляем только если раньше не было
                    "INSERT INTO \"group\" (number) VALUES (?)"
            );

            addGroup.setInt(1, group);
            addGroup.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addSubject(String subject) {
        try {
            PreparedStatement addSubject = connection.prepareStatement(           // ставляем только если раньше не было
                    "INSERT INTO subject (name) VALUES (?)"
            );

            addSubject.setString(1, subject);
            addSubject.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addStudent(ModelStudent student) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement findGroupId = connection.prepareStatement(
                    "select id from \"group\" where number = ?"
            );
            PreparedStatement addStudent = connection.prepareStatement(
                    "insert into student (name, family, age, group_id) values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            findGroupId.setInt(1, student.getGroup());
            ResultSet resultSet = findGroupId.executeQuery();

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
                        addGrade(student, student_id); // Записываем все оценки ученика
                        addMidGrade(student, student_id); // Записываем среднюю оценку ученика
                        connection.commit();
                    }
                } else {
                    connection.rollback();
                    // Обработка случая, когда вставка не прошла
                }
            }
            else {
                connection.rollback();
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addGrade(ModelStudent student, int student_id) {
        try{
            PreparedStatement findSubjectIdByName = connection.prepareStatement(
                    "select id from subject where name = (?)"
            );
            PreparedStatement addGrade = connection.prepareStatement(
                    "insert into grade (grade, subject_id, student_id) values (?, ?, ?)"
            );

            Node<ModelGrade> modelGradeNode = student.getGrades().getHead();

            while (modelGradeNode != null){

                String subject = modelGradeNode.getData().getSubject();
                int grade = modelGradeNode.getData().getGrade();

                findSubjectIdByName.setString(1, subject);
                ResultSet resultSet = findSubjectIdByName.executeQuery();
                if (resultSet.next()) {
                    int subject_id = resultSet.getInt("id");
                    addGrade.setInt(1, grade);
                    addGrade.setInt(2, subject_id);
                    addGrade.setInt(3, student_id);
                    addGrade.addBatch(); // Добавить операцию в пакет
                }
                resultSet.close();

                modelGradeNode = modelGradeNode.getNext();
            }

            addGrade.executeBatch();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addMidGrade(ModelStudent student, int student_id){
        try{
            PreparedStatement addMidGrade = connection.prepareStatement(
                    "insert into mid_grade (grade, student_id) values (?, ?)"
            );

            double grade = student.getMidGrade();

            addMidGrade.setDouble(1, grade);
            addMidGrade.setInt(2, student_id);
            addMidGrade.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
