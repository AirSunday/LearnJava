package org.example.Commands;

import org.example.Service.StudentService;

public class CommandExit implements Command {
    private final StudentService studentService;
    public CommandExit(StudentService studentService){
        this.studentService = studentService;
    }
    @Override
    public void execute(String[] parameters){
        studentService.closeConnection();
    }
}
