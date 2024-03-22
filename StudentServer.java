import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
  
  public class StudentServer implements Runnable  
  {  
    Classroom _classroom;
    public StudentServer(Classroom classroom) 
    {
        _classroom = classroom;
    }
    @Override
    public void run() 
    {
        final int Port = 8898;
        ServerSocket server;
        try
        {
            server = new ServerSocket(Port);
            System.out.println("Waiting for clients to connect...");
            
            while (true)
            {
               try (Socket s = server.accept())
               {
                  System.out.println("Client connected.");
                  StudentService service = new StudentService(s, _classroom);
                  Thread t = new Thread(service);
                  t.start();
               }
            }        
        }
        catch (IOException e) 
        {   
            
            e.printStackTrace();
        }
     }
 }
 