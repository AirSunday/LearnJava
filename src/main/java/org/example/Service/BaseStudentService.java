package org.example.Service;


import org.example.Collection.LinkedList;
import org.example.model.ModelStudent;

public class BaseStudentService implements StudentService {
    @Override
    public Double getMidGradeStudentsByGroup(int group){
        JDBCStorageService storageService = new JDBCStorageService();

        return storageService.getMidGradeByGroup(group);
    }
    @Override
    public LinkedList<ModelStudent> getExcellentPersonsByOlderAge(int age){
        JDBCStorageService storageService = new JDBCStorageService();

        return storageService.getExecellentPersonByOlderAge(age);
    }
    @Override
    public LinkedList<ModelStudent> getPersonByFamily(String family){
        JDBCStorageService storageService = new JDBCStorageService();

        return storageService.getPersonByFamily(family);
    }
    @Override
    public void fillDB() {
        JDBCStorageService storageService = new JDBCStorageService();
        storageService.fillDB();
    }
}
