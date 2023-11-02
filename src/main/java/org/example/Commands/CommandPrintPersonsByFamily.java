package org.example.Commands;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.Group.DataGroup;
import org.example.Group.Person;


public class CommandPrintPersonsByFamily implements Command {
    public DataGroup dataGroup;

    public CommandPrintPersonsByFamily(DataGroup dataGroup){
        this.dataGroup = dataGroup;
    }

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 1) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        String family = parameters[0];       // получили имя ученика

        LinkedList<Person> persons = dataGroup.getListPersonsByKey(family.substring(0, 1).toUpperCase());
        Node<Person> person = persons != null ? persons.getHead() : null;
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
