package e2e;
//import org.junit.Test;
//import static org.junit.Assert.*;

public class ServerTest implements Runnable{
    int option;
    int msDelay;
    ServerForClientTesting server;
    private int port = -1;

    public ServerTest(int option, int msDelay){
        this.option = option;
        this.msDelay = msDelay;
    }
    
    public ServerTest(int option, int msDelay,int port){
        this(option, msDelay);
        this.port = port;
    }
   
    public boolean isServerReady(){
        if(server == null){
            return false;
        }
        return server.isServerReady();
    }

    public Object getOutput(int i){
        return server.getOutput(i);
    }

    @Override
    public void run(){
        if(port <= 0){
            server = new ServerForClientTesting(option, msDelay);
        }
        else{
            server = new ServerForClientTesting(option, msDelay, port);
        }
        server.start();
    }
}