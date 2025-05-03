package model;
import core.Linkedlist;
import core.Node;
public class Student extends Node {
    public Linkedlist enrolledCourse;

    public Student(int studentsID, String studentName) {
        super(studentsID, studentName);
        
        this.enrolledCourse = new Linkedlist();
    }
}
