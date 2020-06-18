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
    private static int uniqueId = 0;
    private Socket socket = null;
    private ServerSocket server = null;
    private final static int port = 2589;
    //private DataInputStream in = null;
    //private ObjectInputStream in = null;
    
    //private final List<ClientThread> clients = new ArrayList<>();

    public Server(int port){
        try{
            server = new ServerSocket(port);
            socket = server.accept();
            Runnable r = new ClientThread(socket, uniqueId++);
            Thread t = new Thread(r);
            //clients.add((ClientThread) r);
            t.start();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public Server(){
        this(port);
    }

    public static void main(String[] args){
        Server server = new Server(); 
    }

    private final class ClientThread implements Runnable {
        private Socket socket;
        private ObjectInputStream in = null;
        private ObjectOutputStream out = null;
        //private Connection databaseConnection;
        int id;
        String emailAddress;

        private ClientThread(Socket socket, int id){
            this.id = id;
            this.socket = socket;
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void test(){
            Email req = new Email();
            req.setTo("You");
            req.setFrom("Server");
            req.setSubject("Test");
            req.setMessage("This is a Server test");

            Email.sendEmail(out, req);
            
            Request inputFromClient = Request.getNextRequest(in);
    
            if(inputFromClient.getRequestType() == 1){
                System.out.println(inputFromClient.getEmail());
            }
        }

        //stores email in database so the user can check on it
        private void StoreEmail(Request input){

        }

        @Override
        public void run(){
            test();
            //Request inputFromClient = Request.getNextRequest(in);
    
            //if(inputFromClient.getRequestType() == 1){
            //    StoreEmail(inputFromClient);
            //}
        }
    }
}

