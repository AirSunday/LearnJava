package Class;

import Collection.HashMap;
import Collection.LinkedList;
import Collection.Node;

public class ClassroomDataAge {
    private HashMap<Integer, Person> classroomAge = new HashMap<Integer, Person>(32);

    public void addPerson(Person person){               //O(n)
        classroomAge.put(person.getAge(), person);
    }

    public LinkedList<Person> getPersonsByAge(int age){     //O(1)
        return classroomAge.getLine(age);
    }

    public void printExcellentPersonsOlder14(){             //O(n^2) или O(17*n)
        for(int i = 15; i < 32; i++){
            LinkedList<Person> persons = getPersonsByAge(i); //O(1)
            if(persons == null) continue;

            Node<Person> person = persons.getHead();
            while(person != null){                                          //O(n)
                if(person.getData().isExcellentStudent()){
                    System.out.print("[");
                    person.getData().print();
                    System.out.print("]");
                    System.out.println();
                }
                person = person.getNext();
            }
        }
    }

}
