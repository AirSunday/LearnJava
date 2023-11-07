package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DTO.DtoGrade;
import org.example.DTO.DtoGroup;
import org.example.DTO.DtoStudent;
import org.example.DataLoader.JDBCStudentsDataLoader;
import org.example.Model.ModelStudent;

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
    public LinkedList<ModelStudent> getExcellentPersonByOlderAge(int age){
        return JDBCStorageService.TransactionScript.getInstance().getExecellentPersonByOlderAge(age);
    }
    @Override
    public LinkedList<ModelStudent> getPersonByFamily(String family){
        return JDBCStorageService.TransactionScript.getInstance().getPersonByFamily(family);
    }
    @Override
    public DtoGroup getStudentsByGroup(int group, int page){
        return JDBCStorageService.TransactionScript.getInstance().getStudentsByGroup(group, page);
    }
    @Override
    public boolean updateGradeByStudent(DtoGrade dtoGrade){
        return JDBCStorageService.TransactionScript.getInstance().updateGradeByStudent(dtoGrade);
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
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            ;
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
            return getPersons("SELECT \n" +
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
            return getPersons("SELECT \n" +
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

        private LinkedList<ModelStudent> getPersons(String searchLine){
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

        public DtoGroup getStudentsByGroup(int group, int page){
            DtoGroup dtoGroup = new DtoGroup(group);

            try {
                PreparedStatement studentWithMidGradeByGroup = connection.prepareStatement(
                        "SELECT\n" +
                                "S.name,\n" +
                                "S.family,\n" +
                                "S.age,\n" +
                                "G.number AS group_number,\n" +
                                "AVG(GR.grade) AS avg_grade\n" +
                                    "FROM student S\n" +
                                        "JOIN \"group\" G ON S.group_id = G.id\n" +
                                        "JOIN grade GR ON S.id = GR.student_id\n" +
                                            "WHERE G.number = 10\n" +
                                            "GROUP BY S.id, G.number\n" +
                                            "LIMIT 10 OFFSET " + (page-1) * 10 + ";\n"
                );

                try(ResultSet resultSet = studentWithMidGradeByGroup.executeQuery();){
                    while (resultSet.next()) {
                        dtoGroup.add(new DtoStudent(
                                resultSet.getString("name"),
                                resultSet.getString("family"),
                                resultSet.getInt("age"),
                                resultSet.getInt("group_number"),
                                resultSet.getDouble("avg_grade")
                        ));
                    }
                }
                return dtoGroup;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public boolean updateGradeByStudent(DtoGrade dtoGrade){
            try {
                connection.setAutoCommit(false);
                String nameStudent = dtoGrade.getStudent().getName();
                String familyStudent = dtoGrade.getStudent().getFamily();
                int groupStudent = dtoGrade.getStudent().getGroup();
                double newGrade = dtoGrade.getGrade();
                String subject = dtoGrade.getSubject();

                PreparedStatement getGradeId = connection.prepareStatement(
                        "SELECT grade.id\n" +
                                "FROM student S\n" +
                                    "JOIN grade ON grade.student_id = S.id\n" +
                                    "JOIN subject SU ON SU.id = grade.subject_id\n" +
                                    "JOIN \"group\" Gr ON Gr.id = S.group_id\n" +
                                        "WHERE\n" +
                                        "S.name = '" + nameStudent + "'\n" +
                                        "AND S.family = '" + familyStudent + "'\n" +
                                        "AND Gr.number = " + groupStudent + "\n" +
                                        "AND SU.name = '" + subject + "'"
                );

                PreparedStatement updateGradeById = connection.prepareStatement(
                            "UPDATE grade \n" +
                                "Set grade = " + newGrade + "\n" +
                                "where id = ?"
                );

                try(ResultSet resultSet = getGradeId.executeQuery();){
                    if (resultSet.next()) {
                        updateGradeById.setInt(1, resultSet.getInt("id"));
                        updateGradeById.execute();
                        connection.commit();
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
