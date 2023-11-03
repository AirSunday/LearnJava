package org.example.Commands;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.Service.StorageService;
import org.example.model.Student;


public class CommandPrintPersonsByFamily implements Command {
    private final StorageService storageService;
    public CommandPrintPersonsByFamily(StorageService storageService){
        this.storageService = storageService;
    }
    @Override
    public void execute(String[] parameters) {
        if (parameters.length > 2) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        boolean fast = false;

        if (parameters.length == 2 && !parameters[1].equals("fast")) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }
        else {
            fast = true;
        }

        String family = parameters[0];       // получили имя ученика


        LinkedList<Student> students = fast ?   storageService.fast_printPersonByFamily(family) :
                                                storageService.printPersonByFamily(family);

        Node<Student> student = students.getHead();

        if(students.size() == 1){
            System.out.print("Средняя оценка ученика: ");
            System.out.print(student.getData().getMidGrade());
            System.out.println();
        }
        else {
            while (student != null) {
                student.getData().print();
                System.out.println();
                student = student.getNext();
            }
        }
    }
}
