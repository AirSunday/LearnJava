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
    Command build (String command) {
        Command executor = null;
        switch (command) {                       // перебор всех вариантов команд (не самое лаконичное решение)
            case "cmd1":
                executor = buildCommandPrintExcellentPersonsByOlderAge();
                break;
            case "cmd2":
                executor = buildCommandGetMidGradeStudentByGroup();
                break;
            case "cmd3":
                executor = buildCommandPrintPersonsByFamily();
                break;
            case "help":
                executor = buildCommandHelp();
                break;
            case "exit":
                break;
            default:
                System.out.println("Команды не существует");
        }
        return executor;
    }

    public void run(String command, String[] parameters) {
        System.out.println("===============================================");
        Command executor = build(command);
        if(executor != null)
            try {
                executor.execute(parameters);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
    }
}
