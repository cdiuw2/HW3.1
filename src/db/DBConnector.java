package db;

import entity.Course;
import entity.Student;
import entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface DBConnectorInterface {
    static void addStudent(String name, int age) {};
    static int findStudent(int student_id) {return student_id;};
    static void enrollStudent(int student_id, int course_id) {};
    static void dropCourse(int student_id, int course_id) {};
    static List<Course> enrolledCourses(int student_id) {return null;};
    static List<Course> allCourses() {return null;};
    static Course findCourse(int course_id) {return null;};
    static Teacher findTeacher(int id) {return null;};
}
public class DBConnector implements DBConnectorInterface {
    private static Connection connection;

    static
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hw3",
                    "postgres",
                    "Tentik2005");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void addStudent(String name, int age)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into students(name, age) values(?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
            ResultSet key = preparedStatement.getGeneratedKeys();
            if( key.next() )
            {
                int id = key.getInt(1);
                System.out.println("Student registered successfully , your id is : "+id+", please remember it!");
            }
            preparedStatement.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static Student findStudent(int student_id)
    {
        Student student = null;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where id=?");
            preparedStatement.setInt(1, student_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                student = new Student(id,name,age);
                student.setEnrolledCourses(enrolledCourses(id));
            }
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return student;
    }

    public static void enrollStudent(int student_id, int course_id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into student_courses(student_id,course_id) values (?,?)");
            preparedStatement.setInt(1,student_id);
            preparedStatement.setInt(2,course_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Student was successfully enrolled");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void dropCourse(int student_id, int course_id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from student_courses where student_id=? and course_id=?");
            preparedStatement.setInt(1,student_id);
            preparedStatement.setInt(2,course_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("\nStudent was removed from the course");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Course> enrolledCourses(int student_id)
    {
        List<Course> courses = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student_courses where student_id=?");
            preparedStatement.setInt(1, student_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Course course = DBConnector.findCourse(resultSet.getInt("course_id"));
                courses.add(course);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return courses;
    }

    public static List<Course> allCourses()
    {
        List<Course> courses = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from courses");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int credits = resultSet.getInt("credits");
                int teacher_id = resultSet.getInt("teacher_id");
                Teacher teacher = findTeacher(teacher_id);
                Course course = new Course(id,name,credits,teacher);
                courses.add(course);
            }
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return courses;
    }

    public static Course findCourse(int course_id)
    {
        Course course = null;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from courses where id=?");
            preparedStatement.setInt(1,course_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if( resultSet.next() )
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int credits = resultSet.getInt("credits");
                int teacher_id = resultSet.getInt("teacher_id");
                Teacher teacher = findTeacher(teacher_id);
                course = new Course(id, name, credits, teacher);
            }
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return course;
    }

    public static Teacher findTeacher(int id)
    {
        Teacher teacher = null;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from teachers where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                teacher = new Teacher(id,resultSet.getString("fullname"));
            }
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return teacher;
    }
}
