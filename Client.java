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

    private void test(){
        Email req = new Email();
        req.setTo("You");
        req.setFrom("Client");
        req.setSubject("Test");
        req.setMessage("This is a Client test");
        //out.writeObject(req);
        Email inputFromServer = null;
        try{
            inputFromServer = (Email) in.readObject();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        System.out.println(inputFromServer);

        try{
            out.writeObject(req);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void application(){
        Scanner sc = new Scanner(System.in);
        ClientUI.printMainMenu();
        int optionPicked = Integer.MIN_VALUE;
        
        // makes sure the user only inputs a valid number
        do{
            if(optionPicked != Integer.MIN_VALUE){
                System.out.println("Please enter a number between 1 - " + ClientUI.NUMBER_OF_MAIN_MENU_OPTIONS);
            }

            //makes sure the user only inputed an integer value;
            while(!sc.hasNextInt()){
                System.out.println("Please only enter a number between 1 - " + ClientUI.NUMBER_OF_MAIN_MENU_OPTIONS);
                sc.next();
            }

            optionPicked = sc.nextInt();

        } while(!(optionPicked <= ClientUI.NUMBER_OF_MAIN_MENU_OPTIONS && optionPicked > 0));
        sc.close();
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
        client.test();

    }

    private boolean start(){
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

        // This thread will listen from the server for incoming messages
        //Runnable r = new ListenFromServer();
        //Thread t = new Thread(r);
        //t.start();

    }

    /*
    private final class ListenFromServer implements Runnable {
        public void run() {
            do {
                try {
                    Communication email = (Communication) in.readObject();
                    System.out.print(msg);
                    if(msg.toLowerCase().indexOf("/logout") !=-1){
                        break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    break;
                    //e.printStackTrace();
                }
            }while (true);
        }
    }
    */
}