import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server{
    private static int uniqueId = 0;
    private Socket socket = null;
    private ServerSocket server = null;
   // private DataInputStream in = null;
    //private ObjectInputStream in = null;
    
    //private final List<ClientThread> clients = new ArrayList<>();

    public Server(int port){
        try{
            server = new ServerSocket(port);
            socket = server.accept();
            Runnble r = new ClientThread(socket, uniqueId++);
            Thread t = new Thread(r);
            //clients.add((ClientThread) r);
            t.start();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server server = new Server(2589); 
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
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput = new ObjectInputStream(socket.getInputStream());
                username = (String) sInput.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run(){
            
        }
    }
}

