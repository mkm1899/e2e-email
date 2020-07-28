package e2e;

import java.util.ArrayList;

public class ServerForClientTesting extends Server {
    private int option;
    private int msDelay;
    private ArrayList<ServerThreadForClientTesting> threads;

    public ServerForClientTesting(int option, int msDelay){
        super();
        this.option = option;
        this.msDelay = msDelay;
        threads = new ArrayList<ServerThreadForClientTesting>();
    }

    public ServerForClientTesting(int option, int msDelay, int port){
        super(port);
        this.option = option;
        this.msDelay = msDelay;
        threads = new ArrayList<ServerThreadForClientTesting>();
    }

    public void start(){
        super.start();
    }

    public Object getOutput(int i){
        return threads.get(i).getOutputFromTest();
    }
    
    @Override
    protected void setRunnable(){
        ServerThreadForClientTesting thread = new ServerThreadForClientTesting(super.socket, super.uniqueId, option, msDelay);
        threads.add(thread);
        super.r = thread;
    }
}