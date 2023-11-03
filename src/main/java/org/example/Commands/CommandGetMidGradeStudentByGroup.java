package org.example.Commands;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.Group.DataGroup;                   //Команда для вывода средней оценки в 10 классах
import org.example.Group.Person;
import org.example.Service.JDBCStorageService;
import org.example.Service.StorageService;

import java.text.DecimalFormat;

public class CommandGetMidGradeStudentByGroup implements Command {
    private final StorageService storageService;
    public CommandGetMidGradeStudentByGroup(StorageService storageService){
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

        int group = 0;
        try{
            group = Integer.parseInt(parameters[0]);        //пытаемся преобразовать в число
        }
        catch (NumberFormatException  e){
            throw new NumberFormatException("Параметр должен быть числом");
        }



        System.out.println("Подсчет средней оценкии " + group + " классов...");
;
        double grade = fast ? storageService.fast_getMidGradeStudentByGroup(group) : storageService.getMidGradeStudentByGroup(group);

        System.out.println("Средняя оценка: " + grade);
    }
}
