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

        commandBuilder.build("help");      // выводим команду Help
        String command = "";                        // считываем команды, пока не exit
        while (!command.equals("exit")) {
            System.out.println("===============================================");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите команду: ");
            command = scanner.nextLine();

            commandBuilder.build(command);
        }
    }
}
