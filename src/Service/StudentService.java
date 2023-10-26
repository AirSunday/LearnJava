package Service;

import Class.Group.DataGroup;
import Class.Collection.LinkedList;             // Класс для работы с группировками учеников
import Class.Group.Person;                      // Здесь можно добавить свои новые группировки
import Interface.DataLoader;

public class StudentService {
    // Группировка по группам
    private final DataGroup<Integer> classroomDataGroup = new DataGroup<>(Person::getGroup);

    //Группировка по возрасту
    private final DataGroup<Integer> ageDataGroup = new DataGroup<>(Person::getAge);

    //Группировка по первой букве фамилии
    private final DataGroup<String> familyDataGroup = new DataGroup<>(person -> person.getFamily().substring(0, 1).toUpperCase());

    public StudentService(DataLoader dataLoader){                   // Передаем способ чтения файла
        LinkedList<DataGroup> dataGroupLinkedList                   // конвертация в мою реализацию списка (можно добавить больше комманд)
                = new LinkedList<>(classroomDataGroup, ageDataGroup, familyDataGroup);  // и не менять loadStudentData
        dataLoader.loadStudentData(dataGroupLinkedList);            // запуск чтения файла и записи данных в объекты группировки
    }

    public DataGroup getClassroomDataGroup(){
        return classroomDataGroup;
    }

    public DataGroup getAgeDataGroup(){
        return ageDataGroup;
    }

    public DataGroup getFamilyDataGroup(){
        return familyDataGroup;
    }
}
