package Class.Commands;

import Interface.Command;       //Команда Help

public class CommandHelp implements Command {
    @Override
    public void execute(){
        System.out.println("===============================================");
        System.out.println("Помощь");
        System.out.println("- cmd1  - вывести всех отличников старше 14 лет");
        System.out.println("- cmd2  - вывести среднею оценку учеников 10 классов");
        System.out.println("- cmd3  - вывести среднею оценку учеников 11 классов");
        System.out.println("- cmd4  - найти учеников по фамилии (можно искать по началу фамилии)");
        System.out.println("- help  - вывести все команды");
        System.out.println("- exit  - закончить программу");
    }
}
