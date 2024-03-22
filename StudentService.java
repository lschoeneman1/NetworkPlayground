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

    public void doService() throws IOException
   { 
    try
    {     
      while (true)
      {  
         if (!in.hasNext()) 
         { 
            return; 
         }
         String command = in.next();
         if (command.equals("quit")) 
         { 
            out.println("Session Ending");
            out.flush();            
            return; 
         }
         else 
         {
             String result = executeCommand(command); 
             out.println(result);
             out.flush();
         }
      }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
   }

    public String executeCommand(String command) 
    {
        //String account = in.next();
        String result;
        try
        {
            if (command.equals("addstudent")) 
            {
                String name = in.nextLine();
                double gpa = in.nextDouble();
                result = addStudent(name, gpa);
                return result;
            } 
            else if (command.equals("student")) 
            {                
                int id = in.nextInt();
                Student student = _classroom.getStudent(id);
                return student.toString();
            } 
            else if (command.equals("studentlist")) 
            {
                result = getStudentList();
                return result;
            } 
            else if (command.equals("averagegpa")) 
            {
                result = _classroom.getAverageGpa();
                return result;
            }
            return "";
        }
        catch(Exception ex)
        {
            return "";
        }
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

    private String addStudent(String name, Double gpa) 
    {
        
        int id = _classroom.AddStudent(name, gpa);
        return Integer.toString(id);
    }
}
