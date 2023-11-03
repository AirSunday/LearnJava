package org.example.Commands;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.Service.StudentService;
import org.example.model.ModelStudent;
import org.example.model.ModelStudentFast;


public class CommandPrintPersonsByFamily implements Command {
    private final StudentService studentService;
    public CommandPrintPersonsByFamily(StudentService studentService){
        this.studentService = studentService;
    }
    @Override
    public void execute(String[] parameters) {
        if (parameters.length > 2) {
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

        String family = parameters[0];       // получили имя ученика

        if(fast){
            LinkedList<ModelStudentFast> students = studentService.fast_getPersonByFamily(family);
            Node<ModelStudentFast> student = students.getHead();

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
        else {
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
}
