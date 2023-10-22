package Class;

import Collection.Entry;
import Collection.HashMap;
import Collection.LinkedList;
import Collection.Node;

public class ClassroomDataAge {
    private HashMap<Integer, Person> classroomAge = new HashMap<Integer, Person>(32);

    public ClassroomDataAge(){}

    public void AddPerson(Person person){               //O(n)
        classroomAge.Put(person.GetAge(), person);
    }

    public LinkedList<Entry<Integer, Person>> GetPersonsByAge(int age){     //O(1)
        return classroomAge.GetLine(age);
    }

    public void PrintExcellentPersonsOlder14(){             //O(n^2) или O(17*n)
        for(int i = 15; i < 32; i++){
            LinkedList<Entry<Integer, Person>> persons = classroomAge.GetLine(i); //O(1)
            if(persons == null) continue;

            Node<Entry<Integer, Person>> person = persons.GetHead();
            while(person != null){                                          //O(n)
                if(person.GetData().GetValue().IsExcellentStudent()){
                    System.out.print("[");
                    person.GetData().GetValue().Print();
                    System.out.print("]");
                    System.out.println();
                }
                person = person.GetNext();
            }
        }
    }

}
