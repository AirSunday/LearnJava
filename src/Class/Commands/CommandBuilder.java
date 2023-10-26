package Class.Commands;

import Interface.Command;
import Service.StudentService;                  //сборщик команд

public class CommandBuilder {
    private final StudentService studentService;                //поле, где хранится StudentService для получения доступа к группировкам

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
    }

    public Command buildCommandPrintExcellentPersonsOlder14(){
        return new CommandPrintExcellentPersonsOlder14(studentService.getAgeDataGroup());
    }

    public Command buildCommandGetMidGradeStudentByGroup10(){
        return new CommandGetMidGradeStudentByGroup10(studentService.getClassroomDataGroup());
    }

    public Command buildCommandGetMidGradeStudentByGroup11(){
        return new CommandGetMidGradeStudentByGroup11(studentService.getClassroomDataGroup());
    }

    public Command buildCommandPrintPersonsByFamily(){
        return new CommandPrintPersonsByFamily(studentService.getFamilyDataGroup());
    }

    public Command buildCommandHelp(){
        return new CommandHelp();
    }
}
