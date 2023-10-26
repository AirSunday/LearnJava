package Class.Commands;

import Class.Collection.LinkedList;
import Class.Collection.Node;
import Class.Group.DataGroup;
import Class.Group.Person;                  //Команда для вывода средней оценки в 11 классах
import Interface.Command;

import java.text.DecimalFormat;

public class CommandGetMidGradeStudentByGroup11 implements Command {
    public DataGroup dataGroup;

    public CommandGetMidGradeStudentByGroup11(DataGroup dataGroup){
        this.dataGroup = dataGroup;
    }

    @Override
    public void execute() {
        System.out.println("===============================================");
        System.out.println("Подсчет средней оценкии 11 классов...");

        LinkedList<Person> persons = dataGroup.getListPersonsByKey(11);          //O(1)
        double sum = 0;

        Node<Person> person = persons.getHead();
        while (person != null){
            sum += person.getData().getMidGrade();    //O(1)
            person = person.getNext();
        }

        DecimalFormat df = new DecimalFormat("#.###");
        String formattedValue = df.format(sum / persons.size());
        System.out.println("Средняя оценка: " + formattedValue);
    }
}
