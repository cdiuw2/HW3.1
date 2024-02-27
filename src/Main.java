import db.DBConnector;
import entity.Course;
import entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final DBConnector connector;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("\nWelcome to AITU enrollment system\n");
        while(true)
        {
            System.out.println("\nWhat do you want to do?\n");
            System.out.println("1 - Register new student");
            System.out.println("2 - Enroll subjects");
            System.out.println("3 - Display info");
            System.out.println("0 - Exit\n");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1" -> registerStudent();
                case "2" -> enrollCourses();
                case "3" -> displayInfo();
                case "0" -> System.exit(0);
                default -> System.out.println("\nIncorrect key\n");
            }
        }
    }
    public Main(DBConnector dbConnector) {
        this.connector = dbConnector;
    }

    public static void registerStudent()
    {
        System.out.print("Enter your first name : ");
        String name = scanner.nextLine();
        System.out.print("Enter your last name : ");
        String surname = scanner.nextLine();
        System.out.print("Enter your age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        DBConnector.addStudent(name+" "+surname, age);
    }

    public static void enrollCourses()
    {

        System.out.print("Please enter your id : ");
        int id = scanner.nextInt();
        while(true)
        {
            System.out.println("What do you want to do?");
            System.out.println("1 - Add course");
            System.out.println("2 - Drop course");
            System.out.println("0 - Exit");
            int choice = scanner.nextInt();
            if(choice == 1) enroll(id);
            else if(choice == 2) drop(id);
            else if(choice == 0) break;
            else System.out.println("\nIncorrect key!!!\n");
        }
        scanner.nextLine();
    }

    public static void displayInfo()
    {
        System.out.print("Please enter your id : ");
        int id = scanner.nextInt();
        Student student = DBConnector.findStudent(id);
        System.out.println(student);
        ArrayList<Course> enrolledCourses = (ArrayList<Course>) student.getEnrolledCourses();
        if(enrolledCourses.size() != 0)
        {
            System.out.print("Enrolled courses : ");
            for (Course course : student.getEnrolledCourses())
            {
                System.out.print(course.getName() + " ");
            }
        }
        else
        {
            System.out.print("No enrolled courses");
        }
        System.out.println();
        scanner.nextLine();
    }

    public static void enroll(int id)
    {
        ArrayList<Course> courses = (ArrayList<Course>) DBConnector.allCourses();
        Student student = DBConnector.findStudent(id);
        System.out.println("Choose courses to enroll");
        for (Course course : courses)
        {
            System.out.println(course);
        }
        int course_id = scanner.nextInt();
        boolean contains = false;
        for(Course course : student.getEnrolledCourses())
        {
            if(course.getId()==course_id)
            {
                contains = true;
                break;
            }
        }
        if(contains) System.out.println("This subject already in your basket");
        else DBConnector.enrollStudent(id,course_id);
    }
    public static void drop(int id)
    {
        Student student = DBConnector.findStudent(id);
        if(student.getEnrolledCourses().size()!=0)
        {
            System.out.println("Choose course to drop : ");
            for (Course course : student.getEnrolledCourses())
            {
                System.out.println(course);
            }
            int course_id = scanner.nextInt();
            DBConnector.dropCourse(id, course_id);
        }
        else
        {
            System.out.println("\nYou have no courses\n");
        }
    }
}