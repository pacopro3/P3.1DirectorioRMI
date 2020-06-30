import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Archivo extends Remote {
    boolean check(String a,String b) throws RemoteException, Exception;  
}
