package dsa;

public class Queue<T> {
    private class Node {
        T data;
        Node next;
        Node(T data) {
            this.data = data;
        }
    }

    private Node front,rear;

    public void enqueue(T data){
        Node node = new Node(data);
        if(rear == null){
            front = rear = node;
        }
        else{
            rear.next = node;
            rear = node;
        }
    }

    public T dequeue(){
        if (front == null) return null;
        T data = front.data;
        front = front.next;
        if(front == null) rear = null;
        return data;
    }

    public int size() {
        int count = 0;
        Node temp = front;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public boolean isEmpty(){
        return front == null;
    }

    public T peek() {
        return front != null ? front.data : null;
    }

}
