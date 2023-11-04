package org.example.Commands;

import org.example.Service.StudentService;

public class CommandGetMidGradeStudentByGroup implements Command {
    private final StudentService studentService;
    public CommandGetMidGradeStudentByGroup(StudentService studentService){
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

        int group = 0;
        try{
            group = Integer.parseInt(parameters[0]);        //пытаемся преобразовать в число
        }
        catch (NumberFormatException  e){
            throw new NumberFormatException("Параметр должен быть числом");
        }

        System.out.println("Подсчет средней оценкии " + group + " классов...");

        double grade = fast ?   studentService.fast_getMidGradeStudentsByGroup(group) :
                                studentService.getMidGradeStudentsByGroup(group);

        System.out.println("Средняя оценка: " + grade);
    }
}
