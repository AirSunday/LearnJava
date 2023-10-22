package Class;
                                    //Данный класс используется для решения задачи
import Collection.HashMap;          //Нахождения учеников по фамилии
import Collection.LinkedList;       //так как данные сгруппированы по первой букве имени
import Collection.Node;             //мы точно знаем, в каком списке искать ученика

public class ClassroomDataFamily {
    private HashMap<String, Person> classroomFamily = new HashMap<String, Person>(32);

    public void addPerson(Person person){           //O(n)
        classroomFamily.put(person.getFamily().substring(0, 1).toUpperCase(), person);
    }

    public LinkedList<Person> getPersonsByFamily(String family){     //O(1)
        return classroomFamily.getLine(family.substring(0, 1).toUpperCase());
    }

    public void printPersonsByFamily(String family){            //O(n)
        LinkedList<Person> persons = getPersonsByFamily(family);
        Node<Person> person = persons.getHead();
        while (person != null){
            if(person.getData().getFamily().equals(family)){
                System.out.print("[");
                person.getData().print();
                System.out.print("]");
                System.out.println();
            }
            person = person.getNext();
        }
    }
}
