package org.example.Service;

public class SqlScript {
    public static final String TRUNCATE_TABLE                   = "TRUNCATE FROM \"?\"";
    public static final String INSERT_GROUP                     = "INSERT INTO \"group\" (number) VALUES (?)";
    public static final String INSERT_SUBJECT                   = "INSERT INTO subject (name) VALUES (?)";
    public static final String SELECT_ID_FROM_GROUP_BY_NUMBER   = "SELECT id FROM \"group\" WHERE number = ?";
    public static final String INSERT_STUDENT                   = "INSERT INTO student (name, family, age, group_id) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ID_FROM_SUBJECT_BY_NAME   = "SELECT id FROM subject WHERE name = (?)";
    public static final String INSERT_GRADE                     = "insert into grade (grade, subject_id, student_id) values (?, ?, ?)";
    public static final String SELECT_MID_GRADE_FROM_STUDENT_BY_GROUP = """
                                                                        SELECT
                                                                            G.number,
                                                                            AVG(GR.grade) AS avg_grade
                                                                        FROM student S
                                                                            JOIN "group" G ON S.group_id = G.id
                                                                            JOIN grade GR ON S.id = GR.student_id
                                                                                WHERE G.number = ?
                                                                                GROUP BY G.number
                                                                        """;

    public static final String SELECT_STUDENT_INFO_FROM_STUDENT_BY_AGE = """
                                                                        SELECT
                                                                            S.id AS student_id,
                                                                            S.name,
                                                                            S.family,
                                                                            S.age,
                                                                            G.number,
                                                                            AVG(GR.grade) AS avg_grade
                                                                        FROM student S
                                                                            JOIN "group" G ON S.group_id = G.id
                                                                            JOIN grade GR ON S.id = GR.student_id
                                                                                WHERE S.age > ?
                                                                                GROUP BY S.id, G.number
                                                                                HAVING AVG(GR.grade) = 5
                                                                        """;
    public static final String SELECT_STUDENT_INFO_FROM_STUDENT_BY_FAMILY = """
                                                                        SELECT
                                                                            S.id AS student_id,
                                                                            S.name,
                                                                            S.family,
                                                                            S.age,
                                                                            G.number,
                                                                            AVG(GR.grade) AS avg_grade
                                                                        FROM student S
                                                                            JOIN "group" G ON S.group_id = G.id
                                                                            JOIN grade GR ON S.id = GR.student_id
                                                                                WHERE S.family LIKE  ? || '%'
                                                                                GROUP BY S.id, G.number
                                                                        """;

}
