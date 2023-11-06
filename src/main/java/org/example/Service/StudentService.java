package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DTO.DtoGroup;
import org.example.DTO.DtoStudent;
import org.example.Model.ModelStudent;

public interface StudentService {
    Double getMidGradeStudentsByGroup(int group);
    LinkedList<ModelStudent> getExcellentPersonsByOlderAge(int age);
    LinkedList<ModelStudent> getPersonByFamily(String family);
    DtoGroup getStudentsByGroup(int group, int page);
    void fillDB();
}
