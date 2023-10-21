package Class;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public void Add(T item){
        Node<T> newNode = new Node<T>(item);
        if(head == null) {
            head = newNode;
        }
        else {
            newNode.SetPrev(tail);
            tail.SetNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public void Remove(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }

        if(index == 0)
            head = head.GetNext();

        if(index == size-1)
            tail = tail.GetPrev();


        Node<T> temp = head;
        for(int i = 0; i < index; i++)
            temp = temp.GetNext();


        Node<T> tempNext = temp.GetNext();
        Node<T> tempPrev = temp.GetPrev();
        if(tempNext != null) tempNext.SetPrev(temp.GetPrev());
        if(tempPrev != null) tempPrev.SetNext(temp.GetNext());
    }

    public T Get(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        Node<T> temp = head;
        for(int i = 0; i < index; i++)
            temp = temp.GetNext();

        return temp.GetData();
    }

    public int Size(){
        return size;
    }

    public boolean IsEmpty() {
        return size == 0;
    }

    public void Print(){
        Node<T> temp = head;
        while (temp != null) {
            System.out.print("[" + temp.GetData() + "] ");
            temp = temp.GetNext();
        }
        System.out.println();
    }

    public Node<T> GetHead(){
        return head;
    }

    public Node<T> GetTail(){
        return tail;
    }
}
