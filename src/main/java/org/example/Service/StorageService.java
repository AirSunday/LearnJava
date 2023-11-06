package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.model.ModelStudent;

public interface StorageService {
    void fillDB();
    Double getMidGradeByGroup(int group);
    LinkedList<ModelStudent> getExecellentPersonByOlderAge(int age);
    LinkedList<ModelStudent> getPersonByFamily(String family);
}
