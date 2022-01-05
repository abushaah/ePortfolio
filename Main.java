//package ePortfolio;
import java.util.Scanner;

/**
@summary This is the Main function. It is in charge of the user input.
@author Haifaa Abushaaban [ID: 1146372]
@version 1.0
*/

public class Main{

    private static Scanner scannerObj = new Scanner(System.in);
    public static final int OPTIONS = 6;
    protected static Layout mainPage;
    protected static String fileName;

    /**
        The main function is used for the user to input an option. The options are reasonable values that the user may enter.
    */
    public static void main(String[] args){

        mainPage = new Layout();
        mainPage.setVisible(true);

        if (args.length == 1){
            fileName = args[0];
            Portfolio.loadFile(fileName); // load contents from a file
        }
        else if (args.length > 1){
            System.out.println("Invalid, please enter one command line argument of a file name");
            System.exit(0);
        }
        else if (args.length < 1){
            System.out.println("Invalid, please enter a command line argument of a file name");
            System.exit(0);
        }

        scannerObj.close();

    }

}
