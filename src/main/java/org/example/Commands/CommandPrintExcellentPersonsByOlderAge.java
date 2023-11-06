package org.example.Commands;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.Service.StudentService;
import org.example.model.ModelStudent;


public class CommandPrintExcellentPersonsByOlderAge implements Command {
    private final StudentService studentService;
    public CommandPrintExcellentPersonsByOlderAge(StudentService studentService){
        this.studentService = studentService;
    }
    @Override
    public void execute(String[] parameters) {

        if (parameters.length != 1) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        int age = 0;
        try{                //параметр должен быть числом, пытаемся преобразовать
            age = Integer.parseInt(parameters[0]);
        }
        catch (NumberFormatException  e){
            throw new NumberFormatException("Параметр должен быть числом");
        }

        System.out.println("Ученики отличники старше " + age + " лет:");

        LinkedList<ModelStudent> students = studentService.getExcellentPersonsByOlderAge(age);
        Node<ModelStudent> student = students.getHead();
        while (student != null){
            student.getData().print();
            student = student.getNext();
            System.out.println();
        }
    }
}
