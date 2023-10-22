package Class;

import Collection.Entry;
import Collection.HashMap;
import Collection.LinkedList;
import Collection.Node;

public class ClassroomDataFamily {
    private HashMap<String, Person> classroomFamily = new HashMap<String, Person>(32);

    public ClassroomDataFamily(){}

    public void AddPerson(Person person){           //O(n)
        classroomFamily.Put(person.GetFamily().substring(0, 1).toUpperCase(), person);
    }

    public LinkedList<Entry<String, Person>> GetPersonsByFamily(String family){     //O(1)
        return classroomFamily.GetLine(family.substring(0, 1).toUpperCase());
    }

    public void PrintPersonsByFamily(String family){            //O(n)
        LinkedList<Entry<String, Person>> persons = GetPersonsByFamily(family);
        Node<Entry<String, Person>> person = persons.GetHead();
        while (person != null){
            if(person.GetData().GetValue().GetFamily().equals(family)){
                System.out.print("[");
                person.GetData().GetValue().Print();
                System.out.print("]");
                System.out.println();
            }
            person = person.GetNext();
        }
    }
}
