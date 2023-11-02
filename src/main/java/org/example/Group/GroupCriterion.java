package org.example.Group;

// группировки через лямбда выражения
@FunctionalInterface
public interface GroupCriterion<K> {
    K getCriteria(Person person);
}
