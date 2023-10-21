package Class;

public class HashMap <K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] table;
    private int size = 0;

    public HashMap() {
        table = new LinkedList[DEFAULT_CAPACITY];
    }

    public void Put(K key, V value) {
        if (key == null)
            return;

        int index = key.hashCode() % table.length;

        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }

        Node<Entry<K, V>> node = table[index].GetHead();
        while(node != null){
            if(node.GetData().GetKey().equals(key)){
                node.GetData().SetVolue(value);
                return;
            }
            node = node.GetNext();
        }

        table[index].Add(new Entry<K, V>(key, value));
        size++;
    }

    public V Get(K key) {
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

    public void Print(){
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
}
