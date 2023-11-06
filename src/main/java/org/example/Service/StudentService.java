package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.model.ModelStudent;

public interface StudentService {
    Double getMidGradeStudentsByGroup(int group);
    LinkedList<ModelStudent> getExcellentPersonsByOlderAge(int age);
    LinkedList<ModelStudent> getPersonByFamily(String family);
    void fillDB();
}
