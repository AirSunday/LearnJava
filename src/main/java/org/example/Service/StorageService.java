package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.model.ModelStudent;
import org.example.model.ModelStudentFast;

public interface StorageService {
    void fillDB();
    LinkedList<ModelStudent> getStudentsByGroup(int group);
    LinkedList<ModelStudent> getPersonsByOlderAge(int age);
    LinkedList<ModelStudent> getPersonByFamily(String family);

    LinkedList<ModelStudentFast> fast_getStudentsByGroup(int group);
    LinkedList<ModelStudentFast> fast_getPersonsByOlderAge(int age);
    LinkedList<ModelStudentFast> fast_getPersonByFamily(String family);
}
