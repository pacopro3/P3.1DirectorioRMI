import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	
public class Servidor extends Conexion implements Archivo {
	
    public Servidor() throws IOException {
          super("servidor");
    }

    public void MainServidor(Servidor s) throws IOException {
          int idcliente = 0;
          Archivo stub = null;
            try {
                        //puerto default del rmiregistry
                        java.rmi.registry.LocateRegistry.createRegistry(1099); 
                        System.out.println("RMI registro listo.");
                        stub = (Archivo) UnicastRemoteObject.exportObject(s, 0);
            } catch (Exception e) {
                        System.out.println("Excepcion RMI del registry:");
                        e.printStackTrace();
                        System.exit(0);
                  }//catch
                  while(true) {
                        DataOutputStream dos;            
                        System.out.println("Esperando..."); // Esperando conexión
                        Socket cs = ss.accept();
                        idcliente++;
                        dos = new DataOutputStream(cs.getOutputStream());
                        dos.writeInt(idcliente);
                        System.out.println("Cliente " + idcliente + " en línea");
                        DespachaClientes hilo = new DespachaClientes(cs,idcliente,stub);
                        hilo.start();
            }   
            
      }

      @Override
      public boolean check(String a, String b) throws RemoteException, Exception {
            String current = new java.io.File( "." ).getCanonicalPath();
            String dirserv = current + "\\src\\Servidor\\" + a;
            File file = new File(dirserv);
            if (!file.exists()) {
                  return false;
            }else{
                  if(b.equals(MD5Checksum.getMD5Checksum(dirserv))){
                        return true;
                  }else{
                        return false;
                  }
            }
      }

      
}
