package Class.Commands;

import Class.Collection.LinkedList;
import Class.Collection.Node;
import Class.Group.DataGroup;
import Class.Group.Person;                      //Команда для вывода всех отличников старше 14 лет
import Interface.Command;

public class CommandPrintExcellentPersonsOlder14 implements Command {
    public DataGroup dataGroup;

    public CommandPrintExcellentPersonsOlder14(DataGroup dataGroup){
        this.dataGroup = dataGroup;
    }

    @Override
    public void execute() {
        System.out.println("===============================================");
        System.out.println("Ученики отличники старше 14 лет:");
        for(int i = 15; i < 32; i++){
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
