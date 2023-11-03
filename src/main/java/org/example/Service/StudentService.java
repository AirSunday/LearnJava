package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.model.ModelStudent;
import org.example.model.ModelStudentFast;

public interface StudentService {
    Double getMidGradeStudentsByGroup(int group);
    LinkedList<ModelStudent> getExcellentPersonsByOlderAge(int age);
    LinkedList<ModelStudent> getPersonByFamily(String family);
    Double fast_getMidGradeStudentsByGroup(int group);
    LinkedList<ModelStudentFast> fast_getExcellentPersonsByOlderAge(int age);
    LinkedList<ModelStudentFast> fast_getPersonByFamily(String family);
    void fillDB();
}
