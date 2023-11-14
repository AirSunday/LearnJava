package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DTO.DtoStudent;
import org.example.DataLoader.JDBCStudentsDataLoader;

import java.sql.*;
import java.text.DecimalFormat;

public class JDBCStorageService implements StorageService {

    @Override
    public void fillDB() {
        JDBCStorageService.TransactionScript.getInstance().fillDB();
    }
    @Override
    public Double getMidGradeByGroup(int group){
        return JDBCStorageService.TransactionScript.getInstance().getMidGradeByGroup(group);
    }
    @Override
    public LinkedList<DtoStudent> getExcellentPersonByOlderAge(int age){
        return JDBCStorageService.TransactionScript.getInstance().getExcellentPersonByOlderAge(age);
    }
    @Override
    public LinkedList<DtoStudent> getPersonByFamily(String family){
        return JDBCStorageService.TransactionScript.getInstance().getPersonByFamily(family);
    }
    @Override
    public void closeConnection(){
        JDBCStorageService.TransactionScript.getInstance().closeConnection();
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
        }

        public void fillDB() {
            JDBCStudentsDataLoader JDBCStudentsDataLoader = new JDBCStudentsDataLoader(connection);
            JDBCStudentsDataLoader.fillDB();
        }

        public Double getMidGradeByGroup(int group) {
            try(
                    PreparedStatement midGradeByGroup = connection.prepareStatement(
                        SqlScript.SELECT_MID_GRADE_FROM_STUDENT_BY_GROUP
                    )
            ) {
                midGradeByGroup.setInt(1, group);

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

        public LinkedList<DtoStudent> getExcellentPersonByOlderAge(int age) {
            try (
                    PreparedStatement studentStatement = connection.prepareStatement(
                            SqlScript.SELECT_STUDENT_INFO_FROM_STUDENT_BY_AGE
                    );
            ){
                studentStatement.setInt(1, age);
                return getPerson(studentStatement);
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public LinkedList<DtoStudent> getPersonByFamily(String family){
            try (
                    PreparedStatement studentStatement = connection.prepareStatement(
                            SqlScript.SELECT_STUDENT_INFO_FROM_STUDENT_BY_FAMILY
                    );
            ) {

                studentStatement.setString(1, family);
                return getPerson(studentStatement);
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        private LinkedList<DtoStudent> getPerson(PreparedStatement studentStatement){
            try {
                LinkedList<DtoStudent> students = new LinkedList<>();

                try(ResultSet resultSet = studentStatement.executeQuery();){
                    while (resultSet.next()) {
                        students.add(new DtoStudent(
                                resultSet.getString("name"),
                                resultSet.getString("family"),
                                resultSet.getInt("age"),
                                resultSet.getInt("number"),
                                resultSet.getDouble("avg_grade")));
                    }
                }
                return students;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        }

        void closeConnection(){
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
