package org.example.Collection;

public class HashMap <K, V> {
    private int capacity = 32;
    private LinkedList<V>[] table;
    private int size = 0;

    public HashMap() {
        table = new LinkedList[capacity];
    }
    public HashMap(int capacity) {
        this.capacity = capacity;
        table = new LinkedList[capacity];
    }

    public void put(K key, V value) {                   // O(n)
        if (key == null)
            return;

        int index = key.hashCode() % table.length;

        if (table[index] == null) {
            table[index] = new LinkedList<V>();
        }

        Node<V> node = table[index].getHead();
        while(node != null){
            node = node.getNext();
        }

        table[index].add(value);  //O(1)
        size++;
    }

    public int size() {
        return size;
    }

    public LinkedList<V> getLine(K key){          //O(1)
        int index = key.hashCode() % table.length;
        return table[index];
    }
}
