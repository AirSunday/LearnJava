package Class.Commands;

import Interface.Command;       //Команда Help

public class CommandHelp implements Command {
    @Override
    public void execute(String[] parameters){

        if (parameters.length != 0) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        System.out.println("===============================================");
        System.out.println("Помощь");
        System.out.println("- cmd1 <возраст>    - вывести всех отличников старше введенного возраста");
        System.out.println("- cmd2 <класс>      - вывести среднею оценку учеников по классу");
        System.out.println("- cmd3 <фамилия>    - найти учеников по фамилии (можно искать по началу фамилии)");
        System.out.println("- help              - вывести все команды");
        System.out.println("- exit              - закончить программу");
    }
}
