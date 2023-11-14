package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DTO.DtoStudent;

public interface StudentService {
    Double getMidGradeStudentsByGroup(int group);
    LinkedList<DtoStudent> getExcellentPersonsByOlderAge(int age);
    LinkedList<DtoStudent> getPersonByFamily(String family);
    void fillDB();
    void closeConnection();
}
