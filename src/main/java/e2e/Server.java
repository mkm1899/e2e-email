package e2e;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
*/

//import java.util.ArrayList;
//import java.util.List;

public class Server{
    public static int stdPort = 3589;
    protected static int uniqueId = 0;
    //protected Socket socket = null;
    //private ServerSocket server = null;
    protected Socket socket = null;
    private SSLServerSocket server = null;
    private int port = stdPort;
    protected Runnable r;
    private static final String[] protocols = new String[] {"TLSv1.3"};
    private static final String[] cipher_suites = new String[] {"TLS_AES_128_GCM_SHA256"};
    //private DataInputStream in = null;
    //private ObjectInputStream in = null;
    
    //private final List<ClientThread> clients = new ArrayList<>();

    public Server(int port){
        this.port = port;
    }

    public void start(){
        try {
            server = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(port);
            server.setEnabledProtocols(protocols);
            server.setEnabledCipherSuites(cipher_suites);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while(true){
            try{
                socket = server.accept();
                setRunnable();
                Thread t = new Thread(r);
                //clients.add((ClientThread) r);
                t.start();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    protected void setRunnable(){
        r = new ServerThread(socket, uniqueId++);
    }

    public Server(){}

    public static void main(String[] args){
        Server server = new Server(); 
        server.start();
    }

}

