package models;

import java.util.Stack;

public class UniversityCourseRegistrationSystem {
    private Student studentHead;
    private Course courseHead;
    private int lastStudentAdded;
    private int lastCourseAdded;
    private Stack<Action> undoStack;
    private Stack<Action> redoStack;

    public UniversityCourseRegistrationSystem() {
        studentHead = null;
        courseHead = null;
        lastStudentAdded = -1;
        lastCourseAdded = -1;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    // Add a new student to the system
    public boolean addStudent(int studentID, String name) {
        if (findStudent(studentID) != null) {
            return false; // Student already exists
        }
        Student newStudent = new Student(studentID, name);
        newStudent.next = studentHead;
        studentHead = newStudent;
        lastStudentAdded = studentID;
        return true;
    }

    // Add a new course to the system
    public boolean addCourse(int courseID, String name) {
        if (findCourse(courseID) != null) {
            return false; // Course already exists
        }
        Course newCourse = new Course(courseID, name);
        newCourse.next = courseHead;
        courseHead = newCourse;
        lastCourseAdded = courseID;
        return true;
    }

    // Remove a student from the system
    public boolean removeStudent(int studentID) {
        Student current = studentHead;
        Student prev = null;
        while (current != null && current.studentID != studentID) {
            prev = current;
            current = current.next;
        }
        if (current == null) {
            return false; // Student not found
        }
        // Remove student's enrollments
        int[] courses = current.courses.toArray();
        for (int courseID : courses) {
            removeEnrollment(studentID, courseID, false);
        }
        // Remove student from the list
        if (prev == null) {
            studentHead = current.next;
        } else {
            prev.next = current.next;
        }
        return true;
    }

    // Remove a course from the system
    public boolean removeCourse(int courseID) {
        Course current = courseHead;
        Course prev = null;
        while (current != null && current.courseID != courseID) {
            prev = current;
            current = current.next;
        }
        if (current == null) {
            return false; // Course not found
        }
        // Remove course's enrollments
        int[] students = current.students.toArray();
        for (int studentID : students) {
            removeEnrollment(studentID, courseID, false);
        }
        // Remove course from the list
        if (prev == null) {
            courseHead = current.next;
        } else {
            prev.next = current.next;
        }
        return true;
    }

    // Get the ID of the last student added
    public int getLastStudentAdded() {
        return lastStudentAdded;
    }

    // Get the ID of the last course added
    public int getLastCourseAdded() {
        return lastCourseAdded;
    }

    // Enroll a student in a course
    public boolean enroll(int studentID, int courseID) {
        Student student = findStudent(studentID);
        Course course = findCourse(courseID);
        if (student == null || course == null) {
            return false; // Student or course not found
        }
        // Check constraints
        if (student.courses.size() >= 7 || course.students.size() >= 30) {
            return false; // Exceeds max courses or max students
        }
        if (student.courses.contains(courseID)) {
            return false; // Already enrolled
        }
        student.courses.add(courseID, course.name);
        course.students.add(studentID, student.name);
        // Add to undo stack
        undoStack.push(new Action("enroll", studentID, courseID));
        redoStack.clear(); // Clear redo stack on new action
        return true;
    }

    // Remove a student's enrollment from a course
    public boolean removeEnrollment(int studentID, int courseID ,Boolean recordAction) {
        Student student = findStudent(studentID);
        Course course = findCourse(courseID);
        if (student == null || course == null) {
            return false; // Student or course not found
        }
        if (!student.courses.contains(courseID)) {
            return false; // Not enrolled
        }
        student.courses.remove(courseID);
        course.students.remove(studentID);
        if(recordAction) {
            // Add to undo stack
            undoStack.push(new Action("remove", studentID, courseID));
            redoStack.clear(); // Clear redo stack on new action
        }
        
        return true;
    }

    // List all courses a student is enrolled in
    public void listCoursesByStudent(int studentID) {
        Student student = findStudent(studentID);
        if (student == null) {
            System.out.println("there is no list");
            return;
        }
        int[] courses = student.courses.toArray();
        for (int courseID : courses) {
            System.out.println("Course ID: " + courseID);
        }
    }

    // List all students enrolled in a course
    public void listStudentsByCourse(int courseID) {
        Course course = findCourse(courseID);
        if (course == null) {
            System.out.println("there is no list");
            return;
        }
        int[] students = course.students.toArray();
        for (int studentID : students) {
            System.out.println("Student ID: " + studentID);
        }
    }

    // Sort and return the list of all students by ID
    public int[] sortStudentsByID() {
        CustomLinkedList studentIDs = new CustomLinkedList();
        Student current = studentHead;
        while (current != null) {
            studentIDs.add(current.studentID, current.name);
            current = current.next;
        }
        int[] result = studentIDs.toArray();
        return bubbleSort(result);
    }

    // Sort and return the list of all courses by ID
    public int[] sortCoursesByID() {
        CustomLinkedList courseIDs = new CustomLinkedList();
        Course current = courseHead;
        while (current != null) {
            courseIDs.add(current.courseID, current.name);
            current = current.next;
        }
        int[] result = courseIDs.toArray();
        return bubbleSort(result);
    }

    // Check if a course is full (20–30 students)
    public boolean isFullCourse(int courseID) {
        Course course = findCourse(courseID);
        if (course == null) {
            return false;
        }
        return course.students.size() >= 20;
    }

    // Check if a student is "normal" (registered for 2–7 courses)
    public boolean isNormalStudent(int studentID) {
        Student student = findStudent(studentID);
        if (student == null) {
            return false;
        }
        int courseCount = student.courses.size();
        return courseCount >= 2 && courseCount <= 7;
    }

    // Undo the last enrollment action
    public boolean undo() {
        if (undoStack.isEmpty()) {
            return false;
        }
        Action action = undoStack.pop();
        if (action.type.equals("enroll")) {
            removeEnrollment(action.studentID, action.courseID, false);
            redoStack.push(new Action("enroll", action.studentID, action.courseID));
        } else if (action.type.equals("remove")) {
            enroll(action.studentID, action.courseID);
            redoStack.push(new Action("remove", action.studentID, action.courseID));
        }
        return true;
    }

    // Redo the last undone action
    public boolean redo() {
        if (redoStack.isEmpty()) {
            return false;
        }
        Action action = redoStack.pop();
        if (action.type.equals("enroll")) {
            enroll(action.studentID, action.courseID);
            undoStack.push(new Action("enroll", action.studentID, action.courseID));
        } else if (action.type.equals("remove")) {
            removeEnrollment(action.studentID, action.courseID, false);
            undoStack.push(new Action("remove", action.studentID, action.courseID));
        }
        return true;
    }

    // Helper method to find a student by ID
    private Student findStudent(int studentID) {
        Student current = studentHead ;
        while (current != null) {
            if (current.studentID == studentID) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Helper method to find a course by ID
    private Course findCourse(int courseID) {
        Course current = courseHead;
        while (current != null) {
            if (current.courseID == courseID) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Custom bubble sort implementation
    private int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}