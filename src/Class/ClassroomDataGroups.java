package Class;

import Collection.Node;
import Collection.HashMap;
import Collection.LinkedList;

import java.text.DecimalFormat;

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
