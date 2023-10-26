package Service;

import Class.Commands.CommandBuilder;
import Class.DataLoader.StudentsDataLoader;
import Interface.Command;

import java.util.Scanner;

public class CommandService {
    public void start(){
        CommandBuilder commandBuilder = new CommandBuilder(         // Данный класс принимает сервис для работы с группировками
                new StudentService(new StudentsDataLoader())        // И метод чтения файла
        );

        // Создаем экземляры команд после их билда
        Command commandPrintExcellentPersonsOlder14 = commandBuilder.buildCommandPrintExcellentPersonsOlder14();
        Command commandGetMidGradeStudentByGroup10 = commandBuilder.buildCommandGetMidGradeStudentByGroup10();
        Command commandGetMidGradeStudentByGroup11 = commandBuilder.buildCommandGetMidGradeStudentByGroup11();
        Command commandPrintPersonsByFamily = commandBuilder.buildCommandPrintPersonsByFamily();
        Command commandHelp = commandBuilder.buildCommandHelp();

        commandHelp.execute();                      // выводим команду Help
        String command = "";                        // считываем команды, пока не exit
        while (!command.equals("exit")) {
            System.out.println("===============================================");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите команду: ");
            command = scanner.nextLine();

            switch (command){                       // перебор всех вариантов команд (не самое лаконичное решение)
                case "cmd1":
                    commandPrintExcellentPersonsOlder14.execute();
                    break;
                case "cmd2":
                    commandGetMidGradeStudentByGroup10.execute();
                    break;
                case "cmd3":
                    commandGetMidGradeStudentByGroup11.execute();
                    break;
                case "cmd4":
                    commandPrintPersonsByFamily.execute();
                    break;
                case "help":
                    commandHelp.execute();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Команды не существует");
            }
        }
    }
}
