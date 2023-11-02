package org.example.DataLoader;

import org.example.Group.*;                       // интерфейс для реализации загрузки данных из файла
import org.example.Collection.LinkedList;         // передаем список группировок, по которым раскидываем

// Можно делать добавить несколько группировок, метод не изменится
public interface DataLoader {
    void loadStudentData(LinkedList<DataGroup> dataGroups);
}
