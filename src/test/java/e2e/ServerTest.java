package e2e;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServerTest implements Runnable{
    int option;
    int msDelay;

    public ServerTest(int option, int msDelay){
        this.option = option;
        this.msDelay = msDelay;
    }
    
    @Test public void testBasicConnection() {
        ServerForClientTesting server = new ServerForClientTesting(1, 0);
        Client client = new Client();
        client.start();
                
        //Sends email to server and makes sure the server gets it
        Email req = new Email("You", "Client", "Test", "This is a Client test");
        client.sendEmail(req); //email the server sent
        Email email = (Email) server.getOutput(); //email the server recieved
        String errorMsg = "The email recieved should be:\n" + req.toString() + 
            "\n But instead is:\n" + email.toString();
        
        boolean condition = req.toString().equals(email.toString());
        
        assertTrue(errorMsg, condition);
    }

    @Override
    public void run(){
        ServerForClientTesting server = new ServerForClientTesting(option, msDelay);
    }
}