package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DTO.DtoGrade;
import org.example.DTO.DtoGroup;
import org.example.Model.ModelStudent;

public interface StorageService {
    void fillDB();
    Double getMidGradeByGroup(int group);
    LinkedList<ModelStudent> getExcellentPersonByOlderAge(int age);
    LinkedList<ModelStudent> getPersonByFamily(String family);
    DtoGroup getStudentsByGroup(int group, int page);
    boolean updateGradeByStudent(DtoGrade dtoGrade);
}
