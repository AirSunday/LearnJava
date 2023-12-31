package Class.Group;

import Class.Collection.HashMap;
import Class.Collection.LinkedList;             //Класс для группировок по лямбда выражению
import Class.Collection.Node;
import Interface.GroupCriterion;

public class DataGroup<K> {
    public HashMap<K, Person> dataGroup = new HashMap<>();          //Храним (Ключ, список учеников)
    private GroupCriterion<K> criterion;                            //Критерий группировки
    public DataGroup(GroupCriterion<K> criterion) {                 //Получаем критерий
        this.criterion = criterion;
    }

    public void addPerson(Person person){           //добавление ученика, основываясь на критерии
        K key = criterion.getCriteria(person);      //ключ берется согласно критерию
        dataGroup.put(key, person);
    }

    public LinkedList<Person> getListPersonsByKey(K key){   //Получение списка учеников по ключу
        return dataGroup.getLine(key);
    }
}
