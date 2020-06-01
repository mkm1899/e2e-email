import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 435849834234862L;
    private int RequestType; //1: send email, 2: request emails
    private final static int LARGEST_REQUEST_TYPE = 2;
    private Email email;

    public Request(int RequestType, Email email){
        this.RequestType = RequestType;
        this.email = email;
    }

    public Request(){

    }

    public boolean setRequestType(int RequestType){
        if(RequestType <= LARGEST_REQUEST_TYPE && RequestType > 0){
            this.RequestType = RequestType;
            return true;
        }
        return false;
    }
}