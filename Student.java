import java.util.concurrent.locks.ReentrantLock;

public class Student 
{
    private String _name;
    private double _gpa;
    private int _id;
    private ReentrantLock lock = new ReentrantLock();

    public Student(String name, int id, double gpa) 
    {
         _name = name;
         _id = id;
         _gpa = gpa;
    }

    public void setName(String value)
    {
        _name = value;
    }

    public void setId(int value)
    {
        _id = value;
    }

    public void setGPA(double value)
    {
        lock.lock();
        _gpa = value;
        lock.unlock();
    }

    public int getId() 
    {
        return _id;
    }

    public String getName()
    {
        return _name;
    }

    public double getGPA()
    {
        return _gpa;
    }

    @Override
    public String toString() 
    {
        return "Student [_name=" + _name + ", _gpa=" + _gpa + ", _id=" + _id + "]";
    } 
    
    

}
