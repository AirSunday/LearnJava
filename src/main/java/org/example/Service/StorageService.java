package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.model.Student;

public interface StorageService {
    void fillDB();
    Double getMidGradeStudentByGroup(int group);
    LinkedList<Student> printExcellentPersonsByOlderAge(int age);
    LinkedList<Student> printPersonByFamily(String family);
}
