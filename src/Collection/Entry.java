package Collection;

public class Entry<K, V> {
    private K key;
    private V value;

    public Entry(K key, V value){
        this.key = key;
        this.value = value;
    }

    public V GetValue(){
        return value;
    }
    public K GetKey(){
        return key;
    }

    public void SetVolue(V value){
        this.value = value;
    }

    public boolean equals(Entry<K, V> entry){
        return value.equals(entry.GetValue());
    }

    public int hashCode(){
        return value.hashCode();
    }
}
