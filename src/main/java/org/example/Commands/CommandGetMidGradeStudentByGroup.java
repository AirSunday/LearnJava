package org.example.Commands;

import org.example.Service.StudentService;

public class CommandGetMidGradeStudentByGroup implements Command {
    private final StudentService studentService;
    public CommandGetMidGradeStudentByGroup(StudentService studentService){
        this.studentService = studentService;
    }

    @Override
    public void execute(String[] parameters) {

        if (parameters.length != 1) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        int group = 0;
        try{
            group = Integer.parseInt(parameters[0]);        //пытаемся преобразовать в число
        }
        catch (NumberFormatException  e){
            throw new NumberFormatException("Параметр должен быть числом");
        }

        System.out.println("Подсчет средней оценкии " + group + " классов...");

        double grade = studentService.getMidGradeStudentsByGroup(group);

        System.out.println("Средняя оценка: " + grade);
    }
}
