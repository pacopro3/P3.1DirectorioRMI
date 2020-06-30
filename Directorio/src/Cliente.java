import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFileChooser;

public class Cliente extends Conexion {
    public Cliente() throws IOException {
        super("cliente");
    }
    String host;
    int id;
    String directorio,a,b;

    public void MainCliente(String host) throws IOException{
        this.host=host;
        DataInputStream dis;
	//String host = (args.length < 1) ? null : args[0];
	try {
        dis = new DataInputStream(cs.getInputStream());
        id = dis.readInt();
        System.out.println("El id asignado por el servidor es: "+ id);
        String current = new java.io.File( "." ).getCanonicalPath();
        directorio = current + "\\src\\Clientes\\" + id + "\\";
	    Registry registry = LocateRegistry.getRegistry(host);	
            //también puedes usar getRegistry(String host, int port)
            Archivo stub = (Archivo) registry.lookup("Archivo" + id);
            boolean result=false;
            File file = new File(directorio);
            if (!file.exists()) {
                file.mkdir();
            }

            //Ejecutar selección de archivo de acuerdo al id 
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(directorio));
            fileChooser.setDialogTitle("Cliente numero: " + id);
            fileChooser.setMultiSelectionEnabled(false);
            int resultado = fileChooser.showOpenDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
                a = selectedFile.getName();
                b = MD5Checksum.getMD5Checksum(selectedFile.getAbsolutePath());
            }else{
                System.out.println("Error al seleccionar archivo");
                System.exit(0);
            }
            System.out.println(a);
            result = stub.check(a, b);
            System.out.println("El resultado devuelto por el servidor de la busqueda del archivo es: " + result);
        } catch (Exception e) {
            System.err.println("Excepción del cliente: " +
                             e.toString());
            e.printStackTrace();
        }
        }
    }
    