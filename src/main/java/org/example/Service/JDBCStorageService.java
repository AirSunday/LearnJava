package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DTO.DtoStudent;
import org.example.DataLoader.JDBCStudentsDataLoader;
import org.example.model.ModelStudent;

import java.sql.*;
import java.text.DecimalFormat;

public class JDBCStorageService implements StorageService {

    @Override
    public void fillDB(){
        JDBCStorageService.TransactionScript.getInstance().fillDB();
    }
    @Override
    public Double getMidGradeByGroup(int group){
        return JDBCStorageService.TransactionScript.getInstance().getMidGradeByGroup(group);
    }
    @Override
    public LinkedList<ModelStudent> getExecellentPersonByOlderAge(int age){
        return JDBCStorageService.TransactionScript.getInstance().getExecellentPersonByOlderAge(age);
    }
    @Override
    public LinkedList<ModelStudent> getPersonByFamily(String family){
        return JDBCStorageService.TransactionScript.getInstance().getPersonByFamily(family);
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

        public Double getMidGradeByGroup(int group) {
            try {
                PreparedStatement midGradeByGroup = connection.prepareStatement(
                        "SELECT \n" +
                                "G.number,\n" +
                                "AVG(GR.grade) AS avg_grade\n" +
                                    "FROM student S\n" +
                                        "JOIN \"group\" G ON S.group_id = G.id\n" +
                                        "JOIN grade GR ON S.id = GR.student_id\n" +
                                            "WHERE G.number = " + group + "\n" +
                                            "GROUP BY G.number"
                );

                try(ResultSet resultSet = midGradeByGroup.executeQuery();){
                    if (resultSet.next()) {
                        Double grade = resultSet.getDouble("avg_grade");
                        DecimalFormat df = new DecimalFormat("#.###");
                        String formattedValue = df.format(grade);
                        return Double.parseDouble(formattedValue.replace(',', '.'));
                    }
                }
                return 0.0;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public LinkedList<ModelStudent> getExecellentPersonByOlderAge(int age) {
            return getPerson("SELECT \n" +
                                        "S.id AS student_id, \n" +
                                        "S.name, \n" +
                                        "S.family, \n" +
                                        "S.age, \n" +
                                        "G.number,\n" +
                                        "AVG(GR.grade) AS avg_grade\n" +
                                            "FROM student S\n" +
                                                "JOIN \"group\" G ON S.group_id = G.id\n" +
                                                "JOIN grade GR ON S.id = GR.student_id\n" +
                                                    "WHERE S.age > " + age + "\n" +
                                                    "GROUP BY S.id, G.number\n" +
                                                    "HAVING AVG(GR.grade) = 5\n");
        }

        public LinkedList<ModelStudent> getPersonByFamily(String family){
            return getPerson("SELECT \n" +
                                        "S.id AS student_id, \n" +
                                        "S.name, \n" +
                                        "S.family, \n" +
                                        "S.age, \n" +
                                        "G.number,\n" +
                                        "AVG(GR.grade) AS avg_grade\n" +
                                            "FROM student S\n" +
                                                "JOIN \"group\" G ON S.group_id = G.id\n" +
                                                "JOIN grade GR ON S.id = GR.student_id\n" +
                                                    "WHERE S.family LIKE '" + family + "%'\n" +
                                                    "GROUP BY S.id, G.number");
        }

        private LinkedList<ModelStudent> getPerson(String searchLine){
            try {
                LinkedList<ModelStudent> students = new LinkedList<>();

                PreparedStatement studentsByGroup = connection.prepareStatement(searchLine);

                try(ResultSet resultSet = studentsByGroup.executeQuery();){
                    while (resultSet.next()) {      //Рассмотрим каждого студента отдельно
                        DtoStudent student = new DtoStudent(
                                resultSet.getString("name"),
                                resultSet.getString("family"),
                                resultSet.getInt("age"),
                                resultSet.getInt("number"),
                                resultSet.getDouble("avg_grade"));

                        students.add(new ModelStudent(student));
                    }
                }
                return students;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        }

    }
}
