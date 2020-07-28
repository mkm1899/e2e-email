package e2e;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ClientUI {
    public final static int NUMBER_OF_MAIN_MENU_OPTIONS = 2;
    private final static int NUM_MSG_CREAT_OPTS = 5; //the Number of message creation options in the menu below
    private Client client;
    private String emailAddress = null;

    public static void clearSystemInput(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            sc.next();
        }
        sc.close();
    }

    public ClientUI(Client client, String emailAddress){
        this.client = client;
        this.emailAddress = emailAddress;
        //clearSystemInput();
    }

    public static void printMainMenu(){
        System.out.println("Enter the number for the action you want to do");
        System.out.println("\t1: send email");
        System.out.println("\t2: check emails");
    }

    public static int menuSelection(Scanner sc, int maxValue){
        int optionPicked = Integer.MIN_VALUE;
        
        // makes sure the user only inputs a valid number
        do{
            if(optionPicked != Integer.MIN_VALUE){
                System.out.println("Please enter a number between 1 - " + maxValue);
            }

            //makes sure the user only inputed an integer value;
            while(sc.hasNext() && !sc.hasNextInt()){
                System.out.println("Please only enter a number between 1 - " + maxValue);
                if(optionPicked != Integer.MIN_VALUE){
                    sc.next();
                }
            }

            optionPicked = sc.nextInt();

        } while(!(optionPicked <= maxValue && optionPicked > 0));

        return optionPicked;
    }

    public void MainMenuFunctionality(){
        Scanner sc = new Scanner(System.in);
        printMainMenu();
        int optionPicked = menuSelection(sc, NUMBER_OF_MAIN_MENU_OPTIONS);
        
        switch(optionPicked){
            case 1:
                sendMessageFunctionality(sc);
                break;
            case 2:
                //@TODO
                break;
            default :
                sc.close();
                System.err.append("\nGot number "+ optionPicked +" that was not yet accounted for\n");
                System.exit(1);
        }
        sc.close();
    }

    public static void printMessageCreationMenu(Email email){
        System.out.println("Enter the number for the action you want to do");
        System.out.println("\t1: To: " + email.getTo());
        System.out.println("\t2: Subject: " + email.getSubject());
        System.out.println("\t3: Message: " + email.getMessage());
        System.out.println("\t4: Send");
        System.out.println("\t5: Discard");
    }

    private void sendMessageFunctionality(Scanner sc){
        Email email = new Email("", emailAddress, "", "");
        boolean bool = true; //is false when the user sends or discard

        do{
            printMessageCreationMenu(email);
            int option_selected = menuSelection(sc, NUM_MSG_CREAT_OPTS);

            switch(option_selected) {
                case 1:
                    System.out.println("Please write the email address of the person you want to email");
                    sc.nextLine();
                    email.setTo(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Please write the subject of the email");
                    sc.nextLine();
                    email.setSubject(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Please write the message of the email");
                    sc.nextLine();
                    email.setMessage(GetMultipleLinesFromUser(sc));
                    break;
                case 4:
                    if(email.getTo() == null || email.getTo().equals("")){
                        System.out.println("You did not specify who you want to send to");
                        break;
                    }
                    client.sendEmail(email);
                    bool = false;
                    break;
                case 5:
                    System.out.println("Email discarded");
                    bool = false;
                    break;
                default:
                    System.out.println("Client Input Incorrect");
            }
        }while(bool);
    }
    
    private String GetMultipleLinesFromUser(Scanner sc, String delimeter){
        String message = "";
	    while(true){
	        message += sc.nextLine() + "\n";
	        if(message.length() >= delimeter.length() && message.substring(message.length() - delimeter.length()).equals(delimeter)){
	            break;
	        }
	    }
	    
	    message = message.substring(0,message.length() - delimeter.length());
	    return message;
    }

    private String GetMultipleLinesFromUser(Scanner sc){
        return GetMultipleLinesFromUser(sc, "\n\n\n\n");
    }
}