package e2e;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClientTest {
    
    //tests a Client-Server 1 on 1 Connection
    @Test public void testBasicConnection() {
        //ServerForClientTesting server = new ServerForClientTesting(1, 0);
        Client client = new Client();
        client.start();

        //listens to Server and recieves Email and makes sure the email recieved is correct
        Request inputFromServer;
        inputFromServer = client.getNextRequest();
        String errorMsg;
        errorMsg = "The email recieved should be:\n" + 
            ServerThreadForClientTesting.stdTest.toString() + 
            "\n But instead is:\n" + inputFromServer.getEmail().toString();

        boolean condition = inputFromServer.getEmail().toString().equals(ServerThreadForClientTesting.stdTest.toString());

        assertTrue(errorMsg,condition);
        
        /*
        //Sends email to server and makes sure the server gets it
        Email req = new Email("You", "Client", "Test", "This is a Client test");
        client.sendEmail(req); //email the server sent
        Email email = (Email) server.getOutput(); //email the server recieved
        errorMsg = "The email recieved should be:\n" + req.toString() + 
            "\n But instead is:\n" + email.toString();
        
        condition = req.toString().equals(email.toString());
        */

        //assertTrue(errorMsg, condition);
    }
    
}