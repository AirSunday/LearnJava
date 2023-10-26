package Interface;

import Class.Group.*;                       // Интерфейс для реализации загрузки данных из файла
import Class.Collection.LinkedList;         // передаем список группировок, по которым раскидываем
                                            // Можно делать добавить несколько группировок, метод не изменится
public interface DataLoader {
    void loadStudentData(LinkedList<DataGroup> dataGroups);
}
