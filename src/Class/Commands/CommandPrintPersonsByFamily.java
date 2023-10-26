package Class.Commands;

import Class.Collection.LinkedList;
import Class.Collection.Node;
import Class.Group.DataGroup;
import Class.Group.Person;                  //Команда для Нахождения учеников по фамилии
import Interface.Command;                   //(Выводим всех учеников, имя которых начинается с одной буквы)

import java.util.Scanner;

public class CommandPrintPersonsByFamily implements Command {
    public DataGroup dataGroup;

    public CommandPrintPersonsByFamily(DataGroup dataGroup){
        this.dataGroup = dataGroup;
    }

    @Override
    public void execute() {
        System.out.println("===============================================");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите фамилию ученика: ");
        String family = "";
        family = scanner.nextLine();

        LinkedList<Person> persons = dataGroup.getListPersonsByKey(family.substring(0, 1).toUpperCase());
        Node<Person> person = persons.getHead();
        while (person != null){
            if(person.getData().getFamily().equals(family)){
                System.out.print("[");
                person.getData().print();
                System.out.print("]");
                System.out.println();
            }
            person = person.getNext();
        }
    }
}
