package org.example.DataLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;                     //Класс для чтения файла и загрузки данных
import org.example.Group.*;                     //в переданные DataGroup-ы
import org.example.Collection.LinkedList;
import org.example.Collection.Node;

public class StudentsDataLoader implements DataLoader {
    @Override
    public void loadStudentData(LinkedList<DataGroup> dataGroups) {     // Получаем список DataGroup, куда надо записать учениеов

        System.out.println("Началось чтение файла...");
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

                    Node<DataGroup> dataGroup = dataGroups.getHead(); // Добавляем каждого человека в каждую группировку
                    while (dataGroup != null){
                        dataGroup.getData().addPerson(person);
                        dataGroup = dataGroup.getNext();
                    }

                } else {
                    System.out.println("Ошибка в записи: Недостаточно столбцов.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Закончилось чтение файла");
    }
}
