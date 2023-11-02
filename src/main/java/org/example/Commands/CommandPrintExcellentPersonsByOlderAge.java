package org.example.Commands;

import org.example.Collection.LinkedList;
import org.example.Collection.Node;
import org.example.Group.DataGroup;
import org.example.Group.Person;


public class CommandPrintExcellentPersonsByOlderAge implements Command {
    public DataGroup dataGroup;

    public CommandPrintExcellentPersonsByOlderAge(DataGroup dataGroup){
        this.dataGroup = dataGroup;
    }

    @Override
    public void execute(String[] parameters) {

        if (parameters.length != 1) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        int age = 0;
        try{                //параметр должен быть числом, пытаемся преобразовать
            age = Integer.parseInt(parameters[0]);
        }
        catch (NumberFormatException  e){
            throw new NumberFormatException("Параметр должен быть числом");
        }


        System.out.println("Ученики отличники старше " + age + " лет:");
        for(int i = age; i < 32; i++){
            LinkedList<Person> persons = dataGroup.getListPersonsByKey(i); //O(1)
            if(persons == null) continue;

            Node<Person> person = persons.getHead();
            while(person != null){                                          //O(n)
                if(person.getData().isExcellentStudent()){
                    System.out.print("[");
                    person.getData().print();
                    System.out.print("]");
                    System.out.println();
                }
                person = person.getNext();
            }
        }
    }
}
