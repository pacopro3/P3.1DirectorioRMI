import java.io.IOException;


//Clase principal que hará uso del cliente
public class RunCliente
{
    public static void main(String[] args) throws IOException
    {
        Cliente cli = new Cliente(); //Se crea el cliente
        String host = (args.length < 1) ? null : args[0];
        System.out.println("Iniciando cliente\n");
        cli.MainCliente(host);
    }
}