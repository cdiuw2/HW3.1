package entity;

interface TeacherInterface {
    int getId();
    void setId(int id);
    String getFullName();
    void setFullName(String fullName);
    String toString();
}

public class Teacher implements TeacherInterface
{
    private int id;

    private String fullname;

    public Teacher()
    {

    }

    public Teacher(int id, String fullname)
    {
        this.id = id;
        this.fullname = fullname;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return fullname;
    }

    public void setFullName(String fullname)
    {
        this.fullname = fullname;
    }

    @Override
    public String toString()
    {
        return fullname;
    }
}
