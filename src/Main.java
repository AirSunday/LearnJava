import Class.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ClassroomDataGroups classroomDataGroups = new ClassroomDataGroups();
        ClassroomDataAge classroomDataAge = new ClassroomDataAge();
        ClassroomDataFamily classroomDataFamily = new ClassroomDataFamily();

        String csvFile = "src/Data/students.csv"; // Путь к файлу CSV

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true; // Флаг для пропуска первой строки

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Пропускаем первую строку
                }

                String[] record = line.split(";"); // Разделяем строку по запятым

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
                    classroomDataGroups.AddPerson(person);
                    classroomDataAge.AddPerson(person);
                    classroomDataFamily.AddPerson(person);
                } else {
                    System.out.println("Ошибка в записи: Недостаточно столбцов.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}