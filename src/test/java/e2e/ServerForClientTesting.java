package e2e;

public class ServerForClientTesting extends Server implements Runnable{
    private int option;
    private int msDelay;
    private ServerThreadForClientTesting thread;

    public ServerForClientTesting(int option, int msDelay){
        super();
        this.option = option;
        this.msDelay = msDelay;
    }

    public Object getOutput(){
        return thread.getOutputFromTest();
    }
    
    @Override
    protected void setRunnable(){
        thread = new ServerThreadForClientTesting(super.socket, super.uniqueId, option, msDelay);
        super.r =  thread;
    }

    @Override
    public void run(){

    }
}