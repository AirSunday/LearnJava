package Class;

import Collection.Entry;
import Collection.Node;
import Collection.HashMap;
import Collection.LinkedList;

public class ClassroomDataGroups {
    private HashMap<Integer, Person> classroomGroups = new HashMap<Integer, Person>();

    public ClassroomDataGroups(){}

    public void AddPerson(Person person){
        classroomGroups.Put(person.GetGroup(), person);
    }

    public LinkedList<Entry<Integer, Person>> GetPersonsByGroup(int group){
        return classroomGroups.GetLine(group);
    }

    public void PrintPersonsByGroup(int group){
        LinkedList<Entry<Integer, Person>> persons = GetPersonsByGroup(group);
        Node<Entry<Integer, Person>> entry = persons.GetHead();
        while (entry != null){
            System.out.print("[");
            entry.GetData().GetValue().Print();
            System.out.print("], ");
            System.out.println();
            entry = entry.GetNext();
        }
    }

    public void Print(){
        classroomGroups.Print();
    }
}
