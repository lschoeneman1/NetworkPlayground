import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Classroom 
{
    private int _lastStudentId = 0;
    private ArrayList<Student> _students = new ArrayList<Student>();
    ReentrantLock lock = new ReentrantLock();

    public Classroom() 
    {
        AddStudent("Bob", 2.75);
        AddStudent("Steve", 2.75);
        AddStudent("Mike", 2.75);
        AddStudent("Dave", 2.75);
    }
    public int AddStudent(String name, double gpa)
    {
        int id = IncrementId();
        Student student = new Student(name, id, gpa);
        _students.add(student);
        return id;
    }

    public Student FindStudentById(int id)
    {
        for (Student student : _students)
        {
            if (student.getId()==id)
            {
                return student;
            }
        }
        return null;
    }

    public int AddStudent(Student student)
    {
        return AddStudent(student.getName(), student.getGPA());
    }

    private int IncrementId()
    {
        lock.lock();
        _lastStudentId++;
        lock.unlock();
        return _lastStudentId;
    }

    public ArrayList<Student> getStudents()
    {
        return _students;
    }

    public Student getStudent(int id)
    {
        for (Student student : _students)
        {
            if (student.getId() == id)
            {
                return student;
            }
        }
        return null;
    }

    public String getAverageGpa() 
    {
        double averageGpa = 0.0;
        for (Student student : _students)
        {
            averageGpa += student.getGPA();
        }
        averageGpa = averageGpa / _students.size();
        return Double.toString(averageGpa);
    }
}
