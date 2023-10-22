package Collection;

public class HashMap <K, V> {
    private int capacity = 16;
    private LinkedList<Entry<K, V>>[] table;
    private int size = 0;

    public HashMap() {
        table = new LinkedList[capacity];
    }
    public HashMap(int capacity) {
        this.capacity = capacity;
        table = new LinkedList[capacity];
    }

    public void Put(K key, V value) {                   // O(n)
        if (key == null)
            return;

        int index = key.hashCode() % table.length;

        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }

        Node<Entry<K, V>> node = table[index].GetHead();
        while(node != null){
            node = node.GetNext();
        }

        table[index].Add(new Entry<K, V>(key, value));  //O(1)
        size++;
    }

    public V Get(K key) {                               // O(n)
        int index = key.hashCode() % table.length;

        if (table[index] == null) {
            return null;
        }

        Node<Entry<K, V>> node = table[index].GetHead();
        while(node != null){
            if(node.GetData().GetKey().equals(key)){
                return node.GetData().GetValue();
            }
            node = node.GetNext();
        }

        return null;
    }

    public int size() {
        return size;
    }

    public void Print(){                                    //O(n)
        for(int i = 0; i < table.length; i++){
            Node<Entry<K, V>> node = table[i] == null ? null : table[i].GetHead();
            System.out.print("{ ");
            while(node != null){
                System.out.print("[" + node.GetData().GetKey() + ":" + node.GetData().GetValue() + "] ");
                node = node.GetNext();
            }
            System.out.print(" }");
            System.out.println();
        }
    }

    public LinkedList<Entry<K, V>> GetLine(K key){          //O(1)
        int index = key.hashCode() % table.length;
        return table[index];
    }
}
