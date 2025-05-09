package service;
import core.Linkedlist;
import core.Node;
import model.Student;
import util.Action;
import model.Course;
import java.util.Stack;

public class ReegistrationSystem {
    private Linkedlist students;
    private Linkedlist courses;
    private Stack<Action> undoStack;
    private Stack<Action> redoStack;

    private Student lastStudent;
    private Course lastCourse;

    public ReegistrationSystem() {
        this.students = new Linkedlist();
        this.courses = new Linkedlist();
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void addStudent(int studentID, String studentName) {
        Student student = new Student(studentID, studentName);
        students.appendList(student); 
        lastStudent = student;
    }
    
    public void addCourse(int courseID,String courseName) {
        Course course = new Course(courseID,courseName);
        courses.appendList(course); 
        lastCourse = course;
    }

    public void removeStudent(int studentID) {
        students.removeList(studentID); 
        lastStudent = null;
    }

    public void removeCourse(int courseID) {
        courses.removeList(courseID); 
        lastCourse = null;
    }

    public int getLastStudentAdded() {
        return lastStudent != null ? lastStudent.id : 0;
    }

    public Integer getLastCourseAdded() {
        return lastCourse != null ? lastCourse.id : null;
    }

    public void enrollStudent(int studentID, int courseID,String studentName,String courseName) {
        Student student = findNode(students, studentID, Student.class);
        Course course = findNode(courses, courseID, Course.class);
        if (student != null && course != null) {
            student.enrolledCourse.appendList(new Node(courseID,courseName)); 
            course.enrolledStudents.appendList(new Node(studentID,studentName)); 
            undoStack.push(new Action(student.id, student.Name,course.id,course.Name, "enroll"));
            redoStack.clear(); 
        }
    }

    public void removeEnrollment(int studentID, int courseID) {
        Student student = findNode(students, studentID, Student.class);
        Course course = findNode(courses, courseID, Course.class);
        if (student != null && course != null) {
            student.enrolledCourse.removeList(courseID); 
            course.enrolledStudents.removeList(studentID); 
            undoStack.push(new Action(student.id, student.Name,course.id,course.Name, "Remove"));
            redoStack.clear(); 
        }
    }

    public void listCoursesByStudent(int studentID) {
        Student student = findNode(students, studentID, Student.class);
        if (student != null) {
            System.out.print("Courses enrolled by student : ");
            student.enrolledCourse.printList();
        } else {
            System.out.println("Student not found.");
        }
    }

    public void listStudentsByCourse(int courseID) {
        Course course = findNode(courses, courseID, Course.class);
        if (course != null) {
            System.out.print("Students enrolled in course : ");
            course.enrolledStudents.printList();
        } else {
            System.out.println("Course not found.");
        }
    }

    public void sortStudents() {
        students.sortList();
        students.printList();
    }

    public void sortCourses() {
        courses.sortList();
        courses.printList();
    }

    public boolean isFullCourse(int courseID) {
        Course course = findNode(courses, courseID, Course.class);
        if (course != null) {
            return course.enrolledStudents.list_length >= 30; 
        }
        return false;
    }

    public boolean isNormalStudent(int studentID) {
        Student student = findNode(students, studentID, Student.class);
        if (student != null) {
            int n = student.enrolledCourse.list_length;
            return n >= 2 && n <= 7; 
        }
        return false;
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            switch (action.ActionType) {
                case "enroll":
                    removeEnrollment(action.StudentID, action.CourseID);
                    redoStack.push(new Action(action.StudentID,action.StudentName, action.CourseID,action.CourseName, "remove"));
                    break;
                case "remove":
                    enrollStudent(action.StudentID, action.CourseID, action.StudentName, action.CourseName);
                    redoStack.push(new Action(action.StudentID,action.StudentName, action.CourseID,action.CourseName,  "enroll"));
                    break;
                default:
                    System.out.println("Unknown action type for undo.");
            }
        } else {
            System.out.println("No actions to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();
            switch (action.ActionType) {
                case "enroll":
                    enrollStudent(action.StudentID, action.CourseID, action.StudentName, action.CourseName);
                    undoStack.push(new Action(action.StudentID,action.StudentName, action.CourseID,action.CourseName,  "enroll"));
                    break;
                case "remove":
                    removeEnrollment(action.StudentID, action.CourseID);
                    undoStack.push(new Action(action.StudentID,action.StudentName, action.CourseID,action.CourseName,  "remove"));
                    break;
                default:
                    System.out.println("Unknown action type for redo.");
            }
        } else {
            System.out.println("No actions to redo.");
        }
    }

    private <T> T findNode(Linkedlist list, int id, Class<T> type) {
        Node temp = list.head;
        while (temp != null) {
            if (temp.id == id && type.isInstance(temp)) {
                return type.cast(temp);
            }
            temp = temp.next;
        }
        return null;
    }
}
