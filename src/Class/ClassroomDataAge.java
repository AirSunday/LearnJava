package Class;

import Collection.Entry;
import Collection.HashMap;
import Collection.LinkedList;
import Collection.Node;

public class ClassroomDataAge {
    private HashMap<Integer, Person> classroomAge = new HashMap<Integer, Person>(32);

    public ClassroomDataAge(){}

    public void AddPerson(Person person){
        classroomAge.Put(person.GetAge(), person);
    }

    public LinkedList<Entry<Integer, Person>> GetPersonsByAge(int age){
        return classroomAge.GetLine(age);
    }

    public void PrintPersonsByAge(int age){
        LinkedList<Entry<Integer, Person>> persons = GetPersonsByAge(age);
        Node<Entry<Integer, Person>> entry = persons.GetHead();
        while (entry != null){
            System.out.print("[");
            entry.GetData().GetValue().Print();
            System.out.print("], ");
            System.out.println();
            entry = entry.GetNext();
        }
    }

}
