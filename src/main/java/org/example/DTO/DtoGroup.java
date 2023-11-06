package org.example.DTO;

import org.example.Collection.LinkedList;

public class DtoGroup {
    private int number;
    private LinkedList<DtoStudent> students;
    public DtoGroup(int number){
        this.number = number;
        this.students = new LinkedList<>();
    }
    public void add(DtoStudent student){
        this.students.add(student);
    }
    public int getNumber(){
        return number;
    }
    public LinkedList<DtoStudent> getStudents(){
        return students;
    }
}
