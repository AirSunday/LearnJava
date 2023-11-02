package Class.Commands;

import Class.Collection.LinkedList;
import Class.Collection.Node;
import Class.Group.DataGroup;                   //Команда для вывода средней оценки в 10 классах
import Class.Group.Person;
import Interface.Command;

import java.text.DecimalFormat;

public class CommandGetMidGradeStudentByGroup implements Command {
    public DataGroup dataGroup;

    public CommandGetMidGradeStudentByGroup(DataGroup dataGroup){
        this.dataGroup = dataGroup;
    }

    @Override
    public void execute(String[] parameters) {

        if (parameters.length != 1) {
            throw new IllegalArgumentException("Не верно заданы параметры команды");
        }

        int group = 0;
        try{
            group = Integer.parseInt(parameters[0]);        //пытаемся преобразовать в число
        }
        catch (NumberFormatException  e){
            throw new NumberFormatException("Параметр должен быть числом");
        }

        System.out.println("Подсчет средней оценкии " + group + " классов...");

        LinkedList<Person> persons = dataGroup.getListPersonsByKey(group);          //O(1)
        double sum = 0;

        if(persons == null) {
            System.out.println("Средняя оценка: " + 0);
            return;
        }

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
