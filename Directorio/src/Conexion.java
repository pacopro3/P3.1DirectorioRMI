import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion
{
    private final int PORT = 5000;   
    private final String HOST = "localhost";   
    protected ServerSocket ss;   
    protected Socket cs; 

    // Constructor
    public Conexion(String tipo) throws IOException     
    { 
        if(tipo.equalsIgnoreCase("servidor"))
        {
            ss = new ServerSocket(PORT); 
            cs = new Socket(); 
        }
        else
        {
            cs = new Socket(HOST, PORT); 
        }
    }
}
