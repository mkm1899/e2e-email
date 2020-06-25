package e2e;
import java.net.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerThread implements Runnable{
        Socket socket;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        //private Connection databaseConnection;
        int id;
        String emailAddress;

        public ServerThread(Socket socket, int id){
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