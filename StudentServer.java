import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
  
  public class StudentServer implements Runnable  
  {  
    @Override
    public void run() 
    {
        Classroom classroom = new Classroom();        
        final int SBAP_PORT = 8898;
        ServerSocket server = null;
        try
        {
           server = new ServerSocket(SBAP_PORT);
  
           System.out.println("Waiting for clients to connect...");
  
           while (true)
           {
              Socket s = server.accept();
              System.out.println("Client connected.");
              StudentService service = new StudentService(s, classroom);
              Thread t = new Thread(service);
              t.start();
           }
        }
        catch (IOException e)
        {
           throw new RuntimeException(e);
        }     
     }
 }
 