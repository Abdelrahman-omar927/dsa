package models;

public class Action {
    public String type; // "enroll" or "remove"
    public int studentID;
    public int courseID;

    public Action(String type, int studentID, int courseID) {
        this.type = type;
        this.studentID = studentID;
        this.courseID = courseID;
    }
}