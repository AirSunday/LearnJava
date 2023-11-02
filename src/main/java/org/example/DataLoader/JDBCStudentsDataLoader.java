package org.example.DataLoader;

import org.example.Collection.Node;
import org.example.Group.DataGroup;
import org.example.Group.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;                   // Класс для загрузки из файла в БД
import java.util.Map;

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
        String[] tables = {"student", "grade", "group", "subject"};
        for (String table : tables) {
            clearTable(table);
        }
    }
    public void fillGroup(){        // добавляем классы 1 - 11
        for(int i = 1; i <= 11; i++) {
            addGroup(i);
        }
    }
    public void fillSubject(){      // добавляем предметы
        String[] subjects = {"физика", "математика", "русский", "литература", "геометрия", "информатика"};
        for (String subject : subjects) {
            addSubject(subject);
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

                    Person person = new Person(family, name, age, group, physics, mathematics, rus, literature, geometry, informatics);
                    addStudent(person);

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
            clearDB.executeUpdate("DELETE FROM \"" + table + "\"");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addGroup(int group) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement addGroup = connection.prepareStatement(           // ставляем только если раньше не было
                    "INSERT INTO \"group\" (number) VALUES (?)"
            );

            addGroup.setInt(1, group);
            addGroup.execute();
            connection.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addSubject(String subject) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement addSubject = connection.prepareStatement(           // ставляем только если раньше не было
                    "INSERT INTO subject (name) VALUES (?)"
            );

            addSubject.setString(1, subject);
            addSubject.execute();
            connection.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addStudent(Person person) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement findGroupId = connection.prepareStatement(
                    "select id from \"group\" where number = ?"
            );
            PreparedStatement addStudent = connection.prepareStatement(
                    "insert into student (name, family, age, group_id) values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            findGroupId.setInt(1, person.getGroup());
            ResultSet resultSet = findGroupId.executeQuery();

            if (resultSet.next()) {
                int group_id = resultSet.getInt("id");

                addStudent.setString(1, person.getName());
                addStudent.setString(2, person.getFamily());
                addStudent.setInt(3, person.getAge());
                addStudent.setInt(4, group_id);

                int affectedRows = addStudent.executeUpdate();

                if (affectedRows > 0) {
                    ResultSet studentKeys = addStudent.getGeneratedKeys();
                    if (studentKeys.next()) {
                        int student_id = studentKeys.getInt(1); // Извлекаем значение первого сгенерированного ключа
                        addGrade(person, student_id); // Записываем все оценки ученика
                        connection.commit();
                    }
                } else {
                    System.out.println("Rollback");
                    connection.rollback();
                    // Обработка случая, когда вставка не прошла
                }
            }
            else {
                System.out.println("Rollback");
                connection.rollback();
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addGrade(Person person, int student_id) {
        try{
            PreparedStatement findSubjectIdByName = connection.prepareStatement(
                    "select id from subject where name = (?)"
            );
            PreparedStatement addGrade = connection.prepareStatement(
                    "insert into grade (grade, subject_id, student_id) values (?, ?, ?)"
            );

            Map<String, Integer> subjects = new HashMap<>() {{
                put("физика", person.getPhysics());                     //какой-то невероятный костыль
                put("математика", person.getMathematics());             //я ничего не придумал
                put("русский", person.getRus());                        //очень высокая связанность получилась
                put("литература", person.getLiterature());
                put("геометрия", person.getGeometry());
                put("информатика", person.getInformatics());
            }};

            for (Map.Entry<String, Integer> entry : subjects.entrySet()) {
                String subject = entry.getKey();
                int grade = entry.getValue();

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
            }

            addGrade.executeBatch();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
