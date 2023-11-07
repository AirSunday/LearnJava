package org.example.DTO;

import org.example.Collection.LinkedList;

import java.util.ArrayList;

public class DtoGroup {
    private int number;
    private ArrayList<DtoStudent> students;
    public DtoGroup() {
    }
    public DtoGroup(int number){
        this.number = number;
        this.students = new ArrayList<>();
    }
    public void add(DtoStudent student){
        this.students.add(student);
    }
    public int getNumber(){
        return number;
    }
    public ArrayList<DtoStudent> getStudents(){
        return students;
    }
}
