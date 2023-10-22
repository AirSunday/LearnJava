import Class.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassroomDataGroups classroomDataGroups = new ClassroomDataGroups();
        ClassroomDataFamily classroomDataFamily = new ClassroomDataFamily();
        ClassroomDataAge classroomDataAge = new ClassroomDataAge();

        System.out.println("Началось чтение файла...");

        LoadStudentFile(classroomDataGroups, classroomDataFamily, classroomDataAge);

        System.out.println("Закончилось чтение файла");

        System.out.println("===============================================");
        System.out.println("Подсчет средней оценки 10 и 11 классов...");
        System.out.println("Средняя оценка: " + classroomDataGroups.GetMidGradeStudentHighSchool());

        System.out.println("===============================================");
        System.out.println("Ученики отличники старше 14 лет:");
        classroomDataAge.PrintExcellentPersonsOlder14();

        System.out.println("===============================================");
        Scanner scanner = new Scanner(System.in);
        String family = "";
        while(true) {
            System.out.print("Введите фамилию ученика (или 0, чтобы завершить программу): ");
            family = scanner.nextLine();
            if(family.equals("0")) break;

            classroomDataFamily.PrintPersonsByFamily(family);
        }
    }

    static void LoadStudentFile(ClassroomDataGroups classroomDataGroups,
                                ClassroomDataFamily classroomDataFamily,
                                ClassroomDataAge classroomDataAge){

        String csvFile = "src/Data/students.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true; // Флаг для пропуска первой строки

            while ((line = br.readLine()) != null) {                // О(n)
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
                    classroomDataGroups.AddPerson(person);
                    classroomDataFamily.AddPerson(person);
                    classroomDataAge.AddPerson(person);
                } else {
                    System.out.println("Ошибка в записи: Недостаточно столбцов.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}