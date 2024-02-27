package entity;

interface CourseInterface {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    int getCredits();
    void setCredits(int credits);
    Teacher getTeacher();
    void setTeacher(Teacher teacher);
    String toString();
}

public class Course implements CourseInterface {
    private int id;

    private String name;

    private int credits;

    private Teacher teacher;

    public Course()
    {

    }

    public Course(int id, String name, int credits, Teacher teacher)
    {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
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

    public int getCredits()
    {
        return credits;
    }

    public void setCredits(int credits)
    {
        this.credits = credits;
    }

    public Teacher getTeacher()
    {
        return teacher;
    }

    public void setTeacher(Teacher teacher)
    {
        this.teacher = teacher;
    }

    @Override
    public String toString()
    {
        return id+" - "+name + " course, " + credits + " credits, taught by " + teacher;
    }
}
