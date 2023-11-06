package org.example.Commands;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.Service.StudentService;
import org.example.Model.ModelStudent;


public class CommandPrintPersonsByFamily implements Command {
    private final StudentService studentService;
    public CommandPrintPersonsByFamily(StudentService studentService){
        this.studentService = studentService;
    }
    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 1) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        String family = parameters[0];       // получили имя ученика

        LinkedList<ModelStudent> students = studentService.getPersonByFamily(family);
        Node<ModelStudent> student = students.getHead();

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
