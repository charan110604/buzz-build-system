package dsa;

public class Stack<T>{
    private class Node {
        T data;
        Node next;
        Node(T data){
            this.data = data;
        }
    }

    private Node top;

    public void push(T data){
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    public T pop(){
        if(top == null) return null;
        T data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public T peek(){
        return top != null ? top.data : null;
    }
}
