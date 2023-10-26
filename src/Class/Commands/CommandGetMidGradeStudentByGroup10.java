package Class.Commands;

import Class.Collection.LinkedList;
import Class.Collection.Node;
import Class.Group.DataGroup;                   //Команда для вывода средней оценки в 10 классах
import Class.Group.Person;
import Interface.Command;

import java.text.DecimalFormat;

public class CommandGetMidGradeStudentByGroup10 implements Command {
    public DataGroup dataGroup;

    public CommandGetMidGradeStudentByGroup10(DataGroup dataGroup){
        this.dataGroup = dataGroup;
    }

    @Override
    public void execute() {
        System.out.println("===============================================");
        System.out.println("Подсчет средней оценкии 10 классов...");

        LinkedList<Person> persons = dataGroup.getListPersonsByKey(10);          //O(1)
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
