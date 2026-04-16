package dsa;

public class LinkedList<T>{
    private class Node{
        T data;
        Node next;
        Node(T data){
            this.data = data;
        }
    }

    private Node head;

    public void add(T data){
        Node node = new Node(data);
        if(head == null){
            head = node;
        }
        else{
            Node temp = head;
            while(temp.next != null) temp = temp.next;
            temp.next = node;
        }
    }

    public boolean remove(T data){
        if(head == null) return false;
        if(head.data.equals(data)){
            head = head.next;
            return true;
        }

        Node temp = head;
        while(temp.next != null){
            if(temp.next.data.equals(data)){
                temp.next = temp.next.next;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public boolean isEmpty(){
        return head == null;
    }
}
