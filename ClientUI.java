import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ClientUI {
    public static int NUMBER_OF_MAIN_MENU_OPTIONS = 2;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    public ClientUI(ObjectInputStream in, ObjectOutputStream out){
        this.in = in;
        this.out = out;
    }

    public static void printMainMenu(){
        System.out.println("Enter the number for the action you want to do");
        System.out.println("\t1: send email");
        System.out.println("\t2: check emails");
    }

    public void MainMenuFunctionality(){
        Scanner sc = new Scanner(System.in);
        printMainMenu();
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

    public static void printMessageCreationMenu(){
        System.out.println("Enter the number for the action you want to do");
        System.out.println("\t1: To:");
        System.out.println("\t2: Subject:");
        System.out.println("\t3: Message:");
        System.out.println("\t4: Send");
        System.out.println("\t5: Discard");
    }

    private static void sendMessageFunctionality(Scanner sc){
        System.out.println
    }
}