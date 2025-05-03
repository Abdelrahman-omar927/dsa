package core;


public class Node {
    public int id;
    public String Name; 
    public Node next;
    public Node prev;

    public Node(int id, String Name) { 
        this.id = id;
        this.Name = Name; 
        this.next = null;
        this.prev = null;
    }
}
