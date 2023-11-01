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

    public void build(String command) {
        switch (command){                       // перебор всех вариантов команд (не самое лаконичное решение)
            case "cmd1":
                buildCommandPrintExcellentPersonsOlder14().execute();
                break;
            case "cmd2":
                buildCommandGetMidGradeStudentByGroup10().execute();
                break;
            case "cmd3":
                buildCommandGetMidGradeStudentByGroup11().execute();
                break;
            case "cmd4":
                buildCommandPrintPersonsByFamily().execute();
                break;
            case "help":
                buildCommandHelp().execute();
                break;
            case "exit":
                break;
            default:
                System.out.println("Команды не существует");
        }
    }
}
