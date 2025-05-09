package models;

public class ListNode {
    public int data; // ID of student or course
    public String name; // Name of student or course
    public ListNode next;
    public ListNode prev;

    public ListNode(int data, String name) {
        this.data = data;
        this.name = name;
        this.next = null;
        this.prev = null;
    }
}