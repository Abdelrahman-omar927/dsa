package models;

public class Student {
    public int studentID;
    public String name; // Name of the student
    public CustomLinkedList courses; // Courses the student is enrolled in
    public Student next; // For linked list of students

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.courses = new CustomLinkedList();
        this.next = null;
    }
}