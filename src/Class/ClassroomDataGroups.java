package Class;

import Collection.Entry;
import Collection.Node;
import Collection.HashMap;
import Collection.LinkedList;

import java.text.DecimalFormat;

public class ClassroomDataGroups {
    private HashMap<Integer, Person> classroomGroups = new HashMap<Integer, Person>();

    public ClassroomDataGroups(){}

    public void AddPerson(Person person){           //O(n)
        classroomGroups.Put(person.GetGroup(), person);
    }

    public LinkedList<Entry<Integer, Person>> GetPersonsByGroup(int group){     //O(1)
        return classroomGroups.GetLine(group);
    }

    double GetMidGradeStudentByGroup(int group){                //O(n)
        LinkedList<Entry<Integer, Person>> persons = GetPersonsByGroup(group);          //O(1)
        double sum = 0;

        Node<Entry<Integer, Person>> person = persons.GetHead();
        while (person != null){
            sum += person.GetData().GetValue().GetMidGrade();    //O(1)
            person = person.GetNext();
        }

        return sum / persons.Size();
    }

    public double GetMidGradeStudentHighSchool(){       //O(2n)
        double midGradeStudentsGroup10 = GetMidGradeStudentByGroup(10);     //O(n)
        double midGradeStudentsGroup11 = GetMidGradeStudentByGroup(11);     //O(n)

        DecimalFormat df = new DecimalFormat("#.###");
        String formattedValue = df.format((midGradeStudentsGroup10 + midGradeStudentsGroup11) / 2);
        return Double.parseDouble(formattedValue.replace(',', '.'));
    }

}
