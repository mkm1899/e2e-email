package e2e;

import java.net.Socket;

public class ServerThreadForClientTesting extends ServerThread {
    private int option;
    private int msDelay;
    private Object outputFromTest = null;
    public static Email stdTest = 
        new Email("Client", "Server", "Test", "This is a Server Test");

    public ServerThreadForClientTesting(Socket socket, int id, int option, int msDelay) {
        super(socket, id);
        this.option = option;
        if(msDelay < 0){
            this.msDelay = 0;
        }
        else{
            this.msDelay = msDelay;
        }
    }

    // The server recieves email and will eventually return it. 
    // Then it sends somthing to the client to see if it recieved it.
    private Object basicConnectionTest(){
        //Email req = new Email();
        Object rtn = null;

        Email.sendEmail(out, stdTest);
    
        Request inputFromClient = Request.getNextRequest(super.in);

        if(inputFromClient.getRequestType() == 1){
            rtn = inputFromClient.getEmail();
            //System.out.println(rtn);
        }
        else{
            rtn = new Exception("email recieved was of the wrong type");
        }

        //Email.sendEmail(out, req);
        return rtn;
    }

    public Object test(int options, int msDelay) {
        try {
            Thread.sleep(msDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        if(options == 1){
            return basicConnectionTest();
        }
        return null;
    }

    public Object getOutputFromTest(){
        return outputFromTest;
    }

    @Override 
    public void run(){
        outputFromTest = test(option, msDelay);
    }
}