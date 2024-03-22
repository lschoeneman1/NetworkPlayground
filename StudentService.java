import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class StudentService implements Runnable 
{
    private Socket _socket;
    private Classroom _classroom;
    private Scanner in;
    private PrintWriter out;

    public StudentService(Socket socket, Classroom classroom) 
    {
        _socket = socket;
        _classroom = classroom;
    }

    public void run()
   {
      try
      {
         try
         {
            in = new Scanner(_socket.getInputStream());
            out = new PrintWriter(_socket.getOutputStream());
            doService();
         }
         finally
         {
            _socket.close();
         }
      }
      catch (IOException exception)
      {
         exception.printStackTrace();
      }
   }


    public void doService() throws IOException {
        while (true) {
            if (!in.hasNext()) {
                return;
            }
            String command = in.nextLine();
            if (command.equals("quit")) {
                return;
            } else {
                String result = executeCommand(command);
                if (!result.equals("")) {
                    out.println(result);
                    out.flush();
                }
            }
        }
    }

    public String executeCommand(String command) {
        String account = in.next();
        String result;
        String[] parameters = account.splitWithDelimiters("\\w", 0);
        if (parameters[0].equals("addstudent")) {

            result = addStudent(parameters);
            return result;
        } else if (command.equals("student")) {
            result = addStudent(parameters);
            return result;
        } else if (command.equals("studentlist")) {
            result = getStudentList();
            return result;
        } else if (command.equals("averagegpa")) {
            result = getAverageGpa();
            return result;
        }
        return "";

    }

    private String getAverageGpa() 
    {
        double averageGpa = 0.0;
        for (Student student : _classroom.getStudents()) 
        {
            averageGpa += student.getGPA();
        }
        averageGpa = averageGpa / _classroom.getStudents().size();
        return Double.toString(averageGpa);
    }

    private String getStudentList() 
    {
        String buffer = "";
        for (Student student : _classroom.getStudents())
        {
            buffer += student + "\n";
        }
        return buffer;
    }

    private String addStudent(String[] parameters) 
    {
        double gpa = Double.parseDouble(parameters[2]);
        int id = _classroom.AddStudent(parameters[1], gpa);
        return Integer.toString(id);
    }
}
