package e2e;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Request implements Serializable {
    private static final long serialVersionUID = 435849834234862L;
    //Request Type are commented from client perspective
    private int RequestType; //1: send email, 2: request emails
    private final static int LARGEST_REQUEST_TYPE = 2;
    private Email email;

    public Request(int RequestType, Email email){
        this.RequestType = RequestType;
        this.email = email;
    }

    public Request(){

    }

    public int getRequestType(){
        return RequestType;
    }

    public Email getEmail(){
        return email;
    }

    public boolean setRequestType(int RequestType){
        if(RequestType <= LARGEST_REQUEST_TYPE && RequestType > 0){
            this.RequestType = RequestType;
            return true;
        }
        return false;
    }

    public static Request getNextRequest(ObjectInputStream in){
        Request input = new Request(-1, null);
        try{
            input = (Request) in.readObject();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e.getClass());
        }

        return input;
    }
}