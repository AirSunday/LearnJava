package org.example.Commands;

public class CommandHelp implements Command {
    @Override
    public void execute(String[] parameters){

        System.out.println("Помощь");
        System.out.println("- cmd1 <возраст>    - вывести всех отличников старше введенного возраста");
        System.out.println("- cmd2 <класс>      - вывести среднею оценку учеников по классу");
        System.out.println("- cmd3 <фамилия>    - найти учеников по фамилии (можно искать по началу фамилии)");
        System.out.println("- help              - вывести все команды");
        System.out.println("- exit              - закончить программу");
    }
}
