package org.example.Commands;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.Service.StorageService;
import org.example.Service.StudentService;
import org.example.model.ModelStudent;
import org.example.model.ModelStudentFast;


public class CommandPrintExcellentPersonsByOlderAge implements Command {
    private final StudentService studentService;
    public CommandPrintExcellentPersonsByOlderAge(StudentService studentService){
        this.studentService = studentService;
    }
    @Override
    public void execute(String[] parameters) {

        if (parameters.length > 2 || parameters.length < 1) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        boolean fast = false;

        if(parameters.length == 2) {
            if (!parameters[1].equals("fast")) {
                throw new IllegalArgumentException("Не верно заданы параметры команды");
            } else {
                fast = true;
            }
        }

        int age = 0;
        try{                //параметр должен быть числом, пытаемся преобразовать
            age = Integer.parseInt(parameters[0]);
        }
        catch (NumberFormatException  e){
            throw new NumberFormatException("Параметр должен быть числом");
        }


        System.out.println("Ученики отличники старше " + age + " лет:");

        if(fast){
            LinkedList<ModelStudentFast> students = studentService.fast_getExcellentPersonsByOlderAge(age);
            Node<ModelStudentFast> student = students.getHead();
            while (student != null){
                student.getData().print();
                student = student.getNext();
                System.out.println();
            }
        }
        else {
            LinkedList<ModelStudent> students = studentService.getExcellentPersonsByOlderAge(age);
            Node<ModelStudent> student = students.getHead();
            while (student != null){
                student.getData().print();
                student = student.getNext();
                System.out.println();
            }
        }
    }
}
