package entity;

import java.util.ArrayList;
import java.util.List;

interface StudentInterface {
    int getId();
    void setId(int id);
    String getName();
    void setName(String fullName);
    int getAge();
    void setAge(int age);
    void setEnrolledCourses(List<Course> enrolledCourses);
    List<Course> getEnrolledCourses();
    String toString();
}

public class Student implements StudentInterface {
    private int id;

    private String name;

    private int age;

    private List<Course> enrolledCourses = new ArrayList<>();



    public Student(int id,String name, int age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses)
    {
        this.enrolledCourses = enrolledCourses;
    }

    public List<Course> getEnrolledCourses()
    {
        return enrolledCourses;
    }

    @Override
    public String toString()
    {
        return "\nStudent Nº"+id+", fullname is : "+name+", "+age+" years old";
    }
}
