package Class.Commands;

import Class.Collection.LinkedList;
import Class.Collection.Node;
import Class.Group.DataGroup;
import Class.Group.Person;                  //Команда для Нахождения учеников по фамилии
import Interface.Command;                   //(Выводим всех учеников, имя которых начинается с одной буквы)

import java.util.Locale;
import java.util.Scanner;

public class CommandPrintPersonsByFamily implements Command {
    public DataGroup dataGroup;

    public CommandPrintPersonsByFamily(DataGroup dataGroup){
        this.dataGroup = dataGroup;
    }

    @Override
    public void execute(String[] parameters) {
        System.out.println("===============================================");
        if (parameters.length != 1) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        String family = parameters[0];       // получили имя ученика

        LinkedList<Person> persons = dataGroup.getListPersonsByKey(family.substring(0, 1).toUpperCase());
        Node<Person> person = persons.getHead();
        while (person != null){
            if(person.getData().getFamily().toLowerCase().startsWith(family.toLowerCase())){
                System.out.print("[");
                person.getData().print();
                System.out.print("]");
                System.out.println();
            }
            person = person.getNext();
        }
    }
}
