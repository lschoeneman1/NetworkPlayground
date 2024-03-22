import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class StudentClient 
{
    
    private static Scanner _in;
    private static PrintWriter _out;
    public static void main(String args[]) throws InterruptedException, IOException
    {        
        Classroom classroom = new Classroom();
        classroom.AddStudent("Bob", 2.75);
        classroom.AddStudent("Steve", 2.75);
        classroom.AddStudent("Mike", 2.75);
        classroom.AddStudent("Dave", 2.75);
        StudentServer studentServer = new StudentServer(classroom);
        Thread studentServerThread = new Thread(studentServer);
        studentServerThread.start();
        

        String serviceAddress = "localhost";
        int port = 8898;
        try (Socket socket = new Socket("localhost", port))
        {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputSteam = socket.getOutputStream();
            _in = new Scanner(inputStream);
            _out = new PrintWriter(outputSteam);        
            
                _out.println("quit");
                _out.flush();
            
        }
    }

 
}