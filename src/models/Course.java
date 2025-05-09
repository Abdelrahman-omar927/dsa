package models;
// filepath: UniversityCourseRegistrationSystem/src/models/Course.java
class Course {
    int courseID;
    String name; // Name of the course
    CustomLinkedList students; // Students enrolled in the course
    Course next; // For linked list of courses

    public Course(int courseID, String name) {
        this.courseID = courseID;
        this.name = name;
        this.students = new CustomLinkedList();
        this.next = null;
    }
}