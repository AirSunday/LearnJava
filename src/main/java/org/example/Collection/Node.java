package org.example.Collection;

public class Node <T> {
    private T data;
    private Node<T> next;
    private Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
    public T getData(){
        return data;
    }
    public Node<T> getNext(){
        return next;
    }
    public Node<T> getPrev(){
        return prev;
    }

    public boolean equals(Node<T> node){
        return data.equals(node.getData());
    }

    public int hashCode(){
        return data.hashCode();
    }
}
