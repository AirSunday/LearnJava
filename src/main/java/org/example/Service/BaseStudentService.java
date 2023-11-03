package org.example.Service;


import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.model.ModelStudent;
import org.example.model.ModelStudentFast;

import java.text.DecimalFormat;

public class BaseStudentService implements StudentService {
    @Override
    public Double getMidGradeStudentsByGroup(int group){
        JDBCStorageService storageService = new JDBCStorageService();
        LinkedList<ModelStudent> students = storageService.getStudentsByGroup(group);

        Node<ModelStudent> student = students.getHead();
        double sum = 0;
        while (student != null){
            sum += student.getData().getMidGrade();
            student = student.getNext();
        }

        DecimalFormat df = new DecimalFormat("#.###");
        String formattedValue = df.format(sum / students.size());
        return Double.parseDouble(formattedValue.replace(',', '.'));
    }
    @Override
    public LinkedList<ModelStudent> getExcellentPersonsByOlderAge(int age){
        JDBCStorageService storageService = new JDBCStorageService();
        LinkedList<ModelStudent> students = storageService.getPersonsByOlderAge(age);

        LinkedList<ModelStudent> excellentStudents = new LinkedList<>();
        Node<ModelStudent> student = students.getHead();

        while (student != null){
            if( student.getData().getMidGrade() == 0.5)
                excellentStudents.add(student.getData());
            student = student.getNext();
        }

        return excellentStudents;
    }
    @Override
    public LinkedList<ModelStudent> getPersonByFamily(String family){
        JDBCStorageService storageService = new JDBCStorageService();

        return storageService.getPersonByFamily(family);
    }
    @Override
    public Double fast_getMidGradeStudentsByGroup(int group){
        JDBCStorageService storageService = new JDBCStorageService();
        LinkedList<ModelStudentFast> students = storageService.fast_getStudentsByGroup(group);

        Node<ModelStudentFast> student = students.getHead();
        double sum = 0;
        while (student != null){
            sum += student.getData().getMidGrade();
            student = student.getNext();
        }

        DecimalFormat df = new DecimalFormat("#.###");
        String formattedValue = df.format(sum / students.size());
        return Double.parseDouble(formattedValue.replace(',', '.'));
    }
    @Override
    public LinkedList<ModelStudentFast> fast_getExcellentPersonsByOlderAge(int age){
        JDBCStorageService storageService = new JDBCStorageService();
        LinkedList<ModelStudentFast> students = storageService.fast_getPersonsByOlderAge(age);

        LinkedList<ModelStudentFast> excellentStudents = new LinkedList<>();
        Node<ModelStudentFast> student = students.getHead();

        while (student != null){
            if(student.getData().getMidGrade() == 5.0)
                excellentStudents.add(student.getData());
            student = student.getNext();
        }

        return excellentStudents;
    }
    @Override
    public LinkedList<ModelStudentFast> fast_getPersonByFamily(String family){
        JDBCStorageService storageService = new JDBCStorageService();

        return storageService.fast_getPersonByFamily(family);
    }
    @Override
    public void fillDB() {
        JDBCStorageService storageService = new JDBCStorageService();
        storageService.fillDB();
    }
}
