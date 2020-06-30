import java.io.IOException;


//Clase principal que hará uso del servidor
public class RunServidor
{
    public static void main(String[] args) throws IOException
    {
        Servidor serv = new Servidor();
        System.out.println("Iniciando servidor\n");
        serv.MainServidor(serv);
    }
}