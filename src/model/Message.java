package model;

public class Message {
    public int id;
    public String user;
    public String text;
    public String timestamp;
    public String status;

    public Message(int id,String user,String text,String timestamp){
        this.id = id;
        this.user = user;
        this.text = text;
        this.timestamp = timestamp;
        this.status = "Pending";
    }

    public String toString(){
        return "[" + id + "] " + user + ": " + text + " (" + status + ")";
    }
}
