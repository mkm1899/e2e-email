package e2e;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
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
    protected static int uniqueId = 0;
    protected Socket socket = null;
    private ServerSocket server = null;
    private final static int port = 2589;
    protected Runnable r;
    //private DataInputStream in = null;
    //private ObjectInputStream in = null;
    
    //private final List<ClientThread> clients = new ArrayList<>();

    public Server(int port){
        try{
            server = new ServerSocket(port);
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

    protected void setRunnable(){
        r = new ServerThread(socket, uniqueId++);
    }

    public Server(){
        this(port);
    }

    public static void main(String[] args){
        Server server = new Server(); 
    }

}

