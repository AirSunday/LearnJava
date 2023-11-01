package Class.Commands;

import Interface.Command;
import Service.StudentService;                  //сборщик команд

public class CommandBuilder {
    private final StudentService studentService;                //поле, где хранится StudentService для получения доступа к группировкам

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
    }

    public Command buildCommandPrintExcellentPersonsByOlderAge(){
        return new CommandPrintExcellentPersonsByOlderAge(studentService.getAgeDataGroup());
    }

    public Command buildCommandGetMidGradeStudentByGroup(){
        return new CommandGetMidGradeStudentByGroup(studentService.getClassroomDataGroup());
    }

    public Command buildCommandPrintPersonsByFamily(){
        return new CommandPrintPersonsByFamily(studentService.getFamilyDataGroup());
    }

    public Command buildCommandHelp(){
        return new CommandHelp();
    }

    public void build(String command, String[] parameters) {
        try {
            switch (command) {                       // перебор всех вариантов команд (не самое лаконичное решение)
                case "cmd1":
                    buildCommandPrintExcellentPersonsByOlderAge().execute(parameters);
                    break;
                case "cmd2":
                    buildCommandGetMidGradeStudentByGroup().execute(parameters);
                    break;
                case "cmd3":
                    buildCommandPrintPersonsByFamily().execute(parameters);
                    break;
                case "help":
                    buildCommandHelp().execute(parameters);
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Команды не существует");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
