package org.example.Collection;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public LinkedList(T... values){
        this();
        for (T value : values) {
            this.add(value);
        }
    }

    public void add(T item){                    //O(1) Был добавлен хвост списку для быстрого добавления
        Node<T> newNode = new Node<T>(item);    // Однако, есть потери в памяти
        if(head == null) {
            head = newNode;
        }
        else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public void remove(int index){                  //O(n)
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }

        if(index == 0)
            head = head.getNext();

        if(index == size-1)
            tail = tail.getPrev();


        Node<T> temp = head;
        for(int i = 0; i < index; i++)      // O(n) Нет индексов для обращения к элементу на прямую
            temp = temp.getNext();          // необходимо пройти весь путь для удаления нужного


        Node<T> tempNext = temp.getNext();
        Node<T> tempPrev = temp.getPrev();
        if(tempNext != null) tempNext.setPrev(temp.getPrev());
        if(tempPrev != null) tempPrev.setNext(temp.getNext());
    }

    public T get(int index){                            //O(n)
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        Node<T> temp = head;
        for(int i = 0; i < index; i++)
            temp = temp.getNext();

        return temp.getData();
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> getHead(){
        return head;
    }

    public Node<T> getTail(){
        return tail;
    }
}
