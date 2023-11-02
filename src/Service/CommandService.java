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

        commandBuilder.run("help", new String[0]);      // выводим команду Help
        String input = "";                        // считываем команды, пока не exit
        while (!input.equals("exit")) {
            System.out.println("===============================================");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите команду: ");
            input = scanner.nextLine().replaceAll("\\s{2,}", " "); // удаляем лишние пробелы

            String[] parts = input.split(" "); // Разбиваем строку по пробелам
            String command = parts[0];               // Название команды

            String[] parameters = new String[parts.length - 1]; // массив полученых параметров
            System.arraycopy(parts, 1, parameters, 0, parts.length - 1);

            commandBuilder.run(command, parameters);          // Поиск команды и передача параметров
        }
    }
}
