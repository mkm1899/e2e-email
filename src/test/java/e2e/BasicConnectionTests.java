package e2e;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.Class;

public class BasicConnectionTests {

    //returns whether an object is an exception or not
    private boolean isObjException(Object obj){
        Exception e = new Exception();
        return (e.getClass().isInstance(obj));
    }

    
    // tests a Client-Server 1 on 1 Connection
    @Test
    public void testBasicConnection() throws Exception {
        int port = Server.stdPort + 1;
        // ServerForClientTesting server = new ServerForClientTesting(1, 0);
        ServerTest server = new ServerTest(1, 0, port);
        Thread t = new Thread(server);
        t.start();

        Thread.sleep(500);
       
        Client client = new Client(port);
        client.start();

        // listens to Server and recieves Email and makes sure the email recieved is
        // correct
        Request inputFromServer;
        inputFromServer = client.getNextRequest();

        // Sends email to server and makes sure the server gets it
        Email req = new Email("You", "Client", "Test", "This is a Client test");
        client.sendEmail(req); // email the server sent

        //tests that email was recieved
        String errorMsg;
        errorMsg = "The email recieved should be:\n" + ServerThreadForClientTesting.stdTest.toString()
                + "\n\nBut instead is:\n" + inputFromServer.getEmail().toString();

        boolean condition = inputFromServer.getEmail().toString()
                .equals(ServerThreadForClientTesting.stdTest.toString());

        assertTrue(errorMsg, condition);

       
        
        //give time for the server to recieve the email
        Object obj = null;
        do{
            Thread.yield();
            obj = server.getOutput(0);
        } while (obj == null);

        if(isObjException(obj)){
            throw (Exception) obj;
        }

        Email email = (Email) obj; //email the server recieved from client
        errorMsg = "The email recieved should be:\n" + req.toString() + 
            "\n But instead is:\n" + email.toString();
        
        condition = req.toString().equals(email.toString());
        

        assertTrue(errorMsg, condition);
    }
    
    
    // tests a Client-Server 2 on 1 Connection
    @Test
    public void testMultBasicConnection() throws Exception {
        // ServerForClientTesting server = new ServerForClientTesting(1, 0);
        int port = Server.stdPort + 2;
        ServerTest server = new ServerTest(1, 0, port);
        Thread t = new Thread(server);
        t.start();

        Thread.sleep(500);
       
        Client client1 = new Client(port);
        client1.start();

        Client client2 = new Client(port);
        client2.start();

        // listens to Server and recieves Email and makes sure the email recieved is
        // correct
        Request inputFromServer1;
        inputFromServer1 = client1.getNextRequest();
        Request inputFromServer2;
        inputFromServer2 = client2.getNextRequest();

        // Sends email to server and makes sure the server gets it
        Email req = new Email("You", "Client", "Test", "This is a Client test");
        client1.sendEmail(req); // email the server sent
        client2.sendEmail(req);

        //tests that email was recieved by the server
        String errorMsg;
        errorMsg = "The email recieved should be:\n" + ServerThreadForClientTesting.stdTest.toString()
                + "\n\nBut instead is:\n" + inputFromServer1.getEmail().toString();

        boolean condition = inputFromServer1.getEmail().toString()
                .equals(ServerThreadForClientTesting.stdTest.toString());

        assertTrue(errorMsg, condition);

        //tests that email was recieved by the server
        errorMsg = "The email recieved should be:\n" + ServerThreadForClientTesting.stdTest.toString()
                + "\n\nBut instead is:\n" + inputFromServer2.getEmail().toString();

        condition = inputFromServer2.getEmail().toString()
                .equals(ServerThreadForClientTesting.stdTest.toString());
        
        assertTrue(errorMsg, condition);
       
        
        //give time for the server to recieve the email
        Object obj = null;
        Object obj2 = null;
        do{
            Thread.yield();
            obj = server.getOutput(0);
        } while (obj == null);

        if(isObjException(obj)){
            throw (Exception) obj;
        }


        do{
            Thread.yield();
            obj2 = server.getOutput(1);
        } while (obj2 == null);

        if(isObjException(obj2)){
            throw (Exception) obj2;
        }

        Email email = (Email) obj; //email the server recieved from client
        errorMsg = "The email recieved should be:\n" + req.toString() + 
            "\n But instead is:\n" + email.toString();
        
        condition = req.toString().equals(email.toString());

        Email email2 = (Email) obj2; //email the server recieved from client
        errorMsg = "The email recieved should be:\n" + req.toString() + 
            "\n But instead is:\n" + email2.toString();
        
        condition = req.toString().equals(email2.toString());
        

        assertTrue(errorMsg, condition);
    }
}