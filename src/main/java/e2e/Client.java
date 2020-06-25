package e2e;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class Client{
    private Socket socket = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    private final String server;
    private final int port;

    public Client(String server, int port){
        this.server = server;
        this.port = port;
    }

    public Client(int port){
        this("localhost", port);
    }

    public Client(){
        this("localhost", 2589);
    }

    public Request getNextRequest(){
        return Request.getNextRequest(in);
    }

    public void sendEmail(Email email){
        Email.sendEmail(out, email);
    }

    private void TestBasicEmailFunctionality(){
        application();
        Request inputFromServer = Request.getNextRequest(in);
        if(inputFromServer.getRequestType() == 1){
            System.out.println(inputFromServer.getEmail());
        }
    }

    private void application(){
        ClientUI ui = new ClientUI(this,"temp"); //@TODO
        ui.MainMenuFunctionality(); // it also does more @TODO need to rename
    }

    public static void main(String[] args){
        Client client;
        if(args.length == 2){
            client = new Client(args[0], Integer.parseInt(args[1]));
        }
        else if(args.length == 1){
            client = new Client(Integer.parseInt(args[0]));
        }
        else{
            client = new Client();
        }
        client.start();
        client.TestBasicEmailFunctionality();

    }

    public boolean start(){
        try {
            socket = new Socket(server, port);
        } catch (IOException e) {
            System.out.println("Error 404: Server not found");
            return false;
        }

        // Create your input and output streams
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}