package Interface;

import Class.Group.Person;      // Функциональный интерфейс для определения критерия
                                // группировки через лямбда выражения
@FunctionalInterface
public interface GroupCriterion<K> {
    K getCriteria(Person person);
}
