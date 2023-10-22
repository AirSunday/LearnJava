package Class;

import Collection.Node;                 //Данный класс используется для решения задачи
import Collection.HashMap;              //нахождения средней оценки 10 и 11 классов
import Collection.LinkedList;           //нахождения средней оценки 10 и 11 классов
                                        //так как идет группировка по классам из-за чего
import java.text.DecimalFormat;         //получить список учеников не составит труда

public class ClassroomDataGroups {
    private HashMap<Integer, Person> classroomGroups = new HashMap<Integer, Person>();

    public void addPerson(Person person){           //O(n)
        classroomGroups.put(person.getGroup(), person);
    }

    public LinkedList<Person> getPersonsByGroup(int group){     //O(1)
        return classroomGroups.getLine(group);
    }

    public double getMidGradeStudentByGroup(int group){                //O(n)
        LinkedList<Person> persons = getPersonsByGroup(group);          //O(1)
        double sum = 0;

        Node<Person> person = persons.getHead();
        while (person != null){
            sum += person.getData().getMidGrade();    //O(1)
            person = person.getNext();
        }

        DecimalFormat df = new DecimalFormat("#.###");
        String formattedValue = df.format(sum / persons.size());
        return Double.parseDouble(formattedValue.replace(',', '.'));
    }

}
