package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DTO.DtoStudent;

public interface StorageService {
    void fillDB();
    Double getMidGradeByGroup(int group);
    LinkedList<DtoStudent> getExcellentPersonByOlderAge(int age);
    LinkedList<DtoStudent> getPersonByFamily(String family);
    void closeConnection();
}
