package org.example.Commands;

import org.example.Service.StorageService;
import org.example.Service.StudentService;

public class CommandFillDB implements Command {
    private final StudentService studentService;
    public CommandFillDB(StudentService studentService){
        this.studentService = studentService;
    }
    @Override
    public void execute(String[] parameters){
        studentService.fillDB();
    }
}
