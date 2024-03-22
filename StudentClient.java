import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class StudentClient 
{  
    public static void main(String args[]) throws InterruptedException, IOException
    {       
      
        StudentServer studentServer = new StudentServer();
        Thread studentServerThread = new Thread(studentServer);
        studentServerThread.start();           
        int port = 8898;            
        try (Socket socket = new Socket("localhost", port))
        {                    
            InputStream inputStream = socket.getInputStream();
            OutputStream outputSteam = socket.getOutputStream();
            Scanner in = new Scanner(inputStream);
            PrintWriter out = new PrintWriter(outputSteam);  
            
            
            ProcessCommand(in, out, "averagegpa");
            ProcessCommand(in, out, "averagegpa");
            ProcessCommand(in, out, "student 3");
            String id = ProcessCommand(in, out, "addstudent doogie howser\n3.9");
            ProcessCommand(in, out, "student " + id);

            id = ProcessCommand(in, out, "addstudent Batman\n3.1");
            ProcessCommand(in, out, "student " + id);

            ProcessCommand(in, out, "quit");
        }
    }

    private static String ProcessCommand(Scanner in, PrintWriter out, String command)
    {
            command = command+"\n";
            System.out.print("Sending:"+command);
            out.println(command);
            out.flush();

            String response = in.nextLine();          
            
            System.out.println("Receiving:"+ response);
            return response;

    }
}