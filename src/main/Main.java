package main;
import service.*;
import core.*;
import model.*;
import util.*;
import java.util.Scanner;

public class Main {
    public static void printMenu() {
            System.out.println("==========================================");
            System.out.println("[ 1. Add Student                         ]");
            System.out.println("[ 2. Add Course                          ]");
            System.out.println("[ 3. remove Student                      ]");
            System.out.println("[ 4. remove Course                       ]");
            System.out.println("[ 5. Enroll Student in Course            ]");
            System.out.println("[ 6. Remove Student from Course          ]");
            System.out.println("[ 7. enroll course to student            ]");
            System.out.println("[ 8. remove course from student          ]");
            System.out.println("[ 9. List Students by Course             ]");
            System.out.println("[ 10. List Courses by Student            ]");
            System.out.println("[ 11. Undo Last Action                   ]");
            System.out.println("[ 12. Redo Last Action                   ]");
            System.out.println("[ 13. Sort Students                      ]");
            System.out.println("[ 14. Sort Courses                       ]");
            System.out.println("[ 15. Check if Course is Full            ]");    
            System.out.println("[ 16. check if student is normal student ]");    
            System.out.println("[ 17. Exit                               ]");
            System.out.println("=========================================="); 
    }
    public static void main(String[] args){
        ReegistrationSystem registrationSystem = new ReegistrationSystem();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the registration Management System!");
        while (true){ 
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

           switch(choice){
            case 1:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Student ID: ");
                int studentID = input.nextInt();
                input.nextLine(); 
                System.out.print("Enter Student Name: ");
                String studentName = input.nextLine(); 
                registrationSystem.addStudent(studentID, studentName);
                System.out.println("Student added successfully!");
                break;
            case 2:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Course ID: ");
                int courseID = input.nextInt();
                input.nextLine(); 
                System.out.print("Enter Course Name: ");
                String courseName = input.nextLine(); 
                registrationSystem.addCourse(courseID, courseName);
                System.out.println("Course added successfully!");
                break;
            case 3:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Student ID: ");
                int remove_Student = input.nextInt();
                registrationSystem.removeStudent(remove_Student);
                System.out.println("Student removed successfully!");
                break;
            case 4:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Course ID: ");
                int remove_course = input.nextInt();
                registrationSystem.removeCourse(remove_course);
                System.out.println("Course removed successfully!");
                break;
            case 5:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Student ID: ");
                int studentId = input.nextInt();
                input.nextLine(); 
                System.out.print("Enter Student Name: ");
                String studentNameToEnroll = input.nextLine(); 
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Course ID: ");

                int courseId = input.nextInt();
                input.nextLine(); 
                System.out.print("Enter Course Name: ");
                String courseNameToEnroll = input.nextLine(); 
                registrationSystem.enrollStudent(studentId, courseId, studentNameToEnroll, courseNameToEnroll);
                System.out.println("Student enrolled in course successfully!");
                break;
            case 6:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Student ID: ");
                int studentIdToRemove = input.nextInt();
                System.out.print("Enter Course ID: ");
                int courseIdToRemove = input.nextInt();
                registrationSystem.removeEnrollment(studentIdToRemove, courseIdToRemove);
                System.out.println("Student removed from course successfully!");
                break;
            case 7:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Student ID: ");
                
                int studentIdToEnroll = input.nextInt();
                input.nextLine(); 
                System.out.print("Enter Student Name: ");
                
                String studentNameToEnroll2 = input.nextLine(); 
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Course ID: ");
                 
                int courseIdToEnroll = input.nextInt();
                input.nextLine(); 
                System.out.print("Enter Course Name: ");
                String courseNameToEnroll2 = input.nextLine(); 
                registrationSystem.enrollStudent(studentIdToEnroll, courseIdToEnroll, studentNameToEnroll2, courseNameToEnroll2);
                System.out.println("Course enrolled to student successfully!");
                break;
            case 8:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Student ID: ");
                int studentIdToRemoveCourse = input.nextInt();
                System.out.print("Enter Course ID: ");
                int courseIdToRemoveCourse = input.nextInt();
                registrationSystem.removeEnrollment(studentIdToRemoveCourse, courseIdToRemoveCourse);
                System.out.println("Course removed from student successfully!");
                break; 
            case 9:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Course ID: ");
                int courseIdToList = input.nextInt();
                registrationSystem.listStudentsByCourse(courseIdToList);
                break;
            case 10:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Student ID: ");
                int studentIdToList = input.nextInt();
                registrationSystem.listCoursesByStudent(studentIdToList);
                break;
            case 11:
                System.out.print("\033[H\033[2J");
                registrationSystem.undo();
                System.out.println("Last action undone successfully!");
                break;
            case 12:
                System.out.print("\033[H\033[2J");
                registrationSystem.redo();
                System.out.println("Last action redone successfully!");
                break;
            case 13:
                System.out.print("\033[H\033[2J");
                registrationSystem.sortStudents();
                
                System.out.println("Students sorted successfully!");
                break;
            case 14:
                System.out.print("\033[H\033[2J");
                registrationSystem.sortCourses();
                System.out.println("Courses sorted successfully!");
                break;
            case 15:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Course ID: ");
                int courseIdToCheck = input.nextInt();
                boolean isFull = registrationSystem.isFullCourse(courseIdToCheck);
                if (isFull) {
                    System.out.println("The course is full.");
                } else {
                    System.out.println("The course is not full.");
                }
                break;
            case 16:
                System.out.print("\033[H\033[2J");
                System.out.print("Enter Student ID: ");
                int studentIdToCheck = input.nextInt();
                boolean isNormal = registrationSystem.isNormalStudent(studentIdToCheck);
                if (isNormal) {
                    System.out.println("The student is a normal student.");
                } else {
                    System.out.println("The student is not a normal student.");
                }
                break;
            case 17:
                System.out.print("\033[H\033[2J");    
                System.out.println("Exiting the system. Goodbye!");
                input.close();
                return; 
            default:
                System.out.print("\033[H\033[2J");
                System.out.println("Invalid choice. Please try again.");
                break;
            }
            System.out.println("Press Enter to continue...");
            input.nextLine(); 

                
           }
            
        }
}
          
