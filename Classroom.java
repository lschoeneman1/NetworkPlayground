import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Classroom 
{
    private int _lastStudentId = 0;
    private ArrayList<Student> _students = new ArrayList<Student>();
    ReentrantLock lock = new ReentrantLock();

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
}
