package Class;

import Collection.Entry;
import Collection.HashMap;
import Collection.LinkedList;
import Collection.Node;

public class ClassroomDataFamily {
    private HashMap<String, Person> classroomFamily = new HashMap<String, Person>(32);

    public ClassroomDataFamily(){}

    public void AddPerson(Person person){
        classroomFamily.Put(person.GetFamily().substring(0, 1).toUpperCase(), person);
    }

    public LinkedList<Entry<String, Person>> GetPersonsByFamily(String family){
        return classroomFamily.GetLine(family.substring(0, 1).toUpperCase());
    }

    public void PrintPersonsByFamily(String family){
        LinkedList<Entry<String, Person>> persons = GetPersonsByFamily(family);
        Node<Entry<String, Person>> entry = persons.GetHead();
        while (entry != null){
            System.out.print("[");
            entry.GetData().GetValue().Print();
            System.out.print("], ");
            System.out.println();
            entry = entry.GetNext();
        }
    }
}
