package e2e;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
//import java.util.ArrayList;
//import java.util.List;

public class Server{
    private static int uniqueId = 0;
    private Socket socket = null;
    private ServerSocket server = null;
    private final static int port = 2589;
   // private DataInputStream in = null;
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

    public static void main(String[] args){
        Server server = new Server(port); 
    }

    private final class ClientThread implements Runnable {
        private Socket socket;
        private ObjectInputStream in = null;
        private ObjectOutputStream out = null;
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
            Request req = new Request();
            req.setTo("You");
            req.setFrom("Server");
            req.setSubject("Test");
            req.setMessage("This is a Server test");

            try {
                out.writeObject(req);
            } catch(IOException e){
                e.printStackTrace();
            }
            
            Request inputFromClient = null;
            try{
                inputFromClient = (Request) in.readObject();
            } catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
    
            System.out.println(inputFromClient);
        }

        @Override
        public void run(){
            test();
        }
    }
}

