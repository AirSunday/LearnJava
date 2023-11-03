package org.example.Commands;

import org.example.Service.StorageService;
import org.example.Service.StudentService;                  //сборщик команд

public class CommandBuilder {
    private final StorageService storageService;                //поле, где хранится StorageService для получения доступа к БД

    public CommandBuilder(StorageService storageService) {
        this.storageService = storageService;
    }

    public Command buildCommandPrintExcellentPersonsByOlderAge(){
        return new CommandPrintExcellentPersonsByOlderAge(storageService);
    }

    public Command buildCommandGetMidGradeStudentByGroup(){
        return new CommandGetMidGradeStudentByGroup(storageService);
    }

    public Command buildCommandPrintPersonsByFamily(){
        return new CommandPrintPersonsByFamily(storageService);
    }
    public Command buildCommandFillDB(){
        return new CommandFillDB(storageService);
    }


    public Command buildCommandHelp(){
        return new CommandHelp();
    }
    Command build (String command) {

        enum CommandType {
            CMD1("cmd1"),
            CMD2("cmd2"),
            CMD3("cmd3"),
            HELP("help"),
            EXIT("exit"),
            FILLDB("filldb");

            private final String commandString;

            CommandType(String commandString) {
                this.commandString = commandString;
            }

            public String getCommandString() {
                return commandString;
            }

            public static CommandType fromString(String text) {
                for (CommandType type : CommandType.values()) {
                    if (type.commandString.equalsIgnoreCase(text)) {
                        return type;
                    }
                }
                return null;
            }
        }

        CommandType type = CommandType.fromString(command);

        if (type == null) {
            System.out.println("Команды не существует");
            return null;
        }

        switch (type) {
            case CMD1:
                return buildCommandPrintExcellentPersonsByOlderAge();
            case CMD2:
                return buildCommandGetMidGradeStudentByGroup();
            case CMD3:
                return buildCommandPrintPersonsByFamily();
            case HELP:
                return buildCommandHelp();
            case FILLDB:
                return buildCommandFillDB();
            case EXIT:
                break;
        }
        return null;
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
