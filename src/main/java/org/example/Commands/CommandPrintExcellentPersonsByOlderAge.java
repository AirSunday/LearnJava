package org.example.Commands;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.Group.DataGroup;
import org.example.Group.Person;
import org.example.Service.JDBCStorageService;
import org.example.Service.StorageService;
import org.example.model.Student;


public class CommandPrintExcellentPersonsByOlderAge implements Command {
    private final StorageService storageService;
    public CommandPrintExcellentPersonsByOlderAge(StorageService storageService){
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

        int age = 0;
        try{                //параметр должен быть числом, пытаемся преобразовать
            age = Integer.parseInt(parameters[0]);
        }
        catch (NumberFormatException  e){
            throw new NumberFormatException("Параметр должен быть числом");
        }


        System.out.println("Ученики отличники старше " + age + " лет:");

        LinkedList<Student> students = fast ?   storageService.fast_printExcellentPersonsByOlderAge(age) :
                                                storageService.printExcellentPersonsByOlderAge(age);

        Node<Student> student = students.getHead();
        while (student != null){
            student.getData().print();
            student = student.getNext();
            System.out.println();
        }
    }
}
