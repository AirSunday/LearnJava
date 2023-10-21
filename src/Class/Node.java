package Class;

public class Node <T> {
    private T data;
    private Node<T> next;
    private Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public void SetNext(Node<T> next){
        this.next = next;
    }
    public void SetPrev(Node<T> prev) {
        this.prev = prev;
    }
    public T GetData(){
        return data;
    }
    public Node<T> GetNext(){
        return next;
    }
    public Node<T> GetPrev(){
        return prev;
    }

    public boolean equals(Node<T> node){
        return data.equals(node.GetData());
    }

    public int hashCode(){
        return data.hashCode();
    }
}
