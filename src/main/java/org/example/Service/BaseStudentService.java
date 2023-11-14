package org.example.Service;

import org.example.Collection.LinkedList;
import org.example.DTO.DtoStudent;

public class BaseStudentService implements StudentService {

    JDBCStorageService storageService = new JDBCStorageService();
    @Override
    public Double getMidGradeStudentsByGroup(int group){
        return storageService.getMidGradeByGroup(group);
    }
    @Override
    public LinkedList<DtoStudent> getExcellentPersonsByOlderAge(int age){
        return storageService.getExcellentPersonByOlderAge(age);
    }
    @Override
    public LinkedList<DtoStudent> getPersonByFamily(String family){
        return storageService.getPersonByFamily(family);
    }
    @Override
    public void fillDB() {
        storageService.fillDB();
    }
    @Override
    public void closeConnection(){
        storageService.closeConnection();
    }
}
