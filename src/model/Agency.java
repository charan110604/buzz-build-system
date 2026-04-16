package model;

import dsa.Queue;

public class Agency {
    public String name;
    public Queue<Message> queue;

    public Agency(String name){
        this.name = name;
        this.queue = new Queue<>();
    }

    public void assignMessage(Message msg) {
        queue.enqueue(msg);
    }

    public Message processMessage() {
        return queue.dequeue();
    }

    public boolean hasMessages() {
        return !queue.isEmpty();
    }

    public String toString() {
        return "Agency: " + name;
    }

}
