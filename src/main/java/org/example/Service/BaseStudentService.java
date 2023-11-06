package org.example.Service;


import org.example.Collection.LinkedList;
import org.example.DTO.DtoGroup;
import org.example.Model.ModelStudent;

public class BaseStudentService implements StudentService {
    JDBCStorageService storageService = new JDBCStorageService();
    @Override
    public Double getMidGradeStudentsByGroup(int group){
        return storageService.getMidGradeByGroup(group);
    }
    @Override
    public LinkedList<ModelStudent> getExcellentPersonsByOlderAge(int age){
        return storageService.getExcellentPersonByOlderAge(age);
    }
    @Override
    public LinkedList<ModelStudent> getPersonByFamily(String family){
        return storageService.getPersonByFamily(family);
    }
    @Override
    public DtoGroup getStudentsByGroup(int group, int page){
        return storageService.getStudentsByGroup(group, page);
    }
    @Override
    public void fillDB() {
        storageService.fillDB();
    }
}
