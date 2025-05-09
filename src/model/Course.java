package model;
import core.Linkedlist;
import core.Node;
public class Course extends Node {
    public Linkedlist enrolledStudents;
    public Course(int courseID, String courseName) {
        super(courseID, courseName);
       
        this.enrolledStudents = new Linkedlist();
    }
    
}
