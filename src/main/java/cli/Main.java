package cli;

import calculator.Memory;
import calculator.Notation;
import parser.StringRegrex;

import java.util.List;
import java.util.Scanner;


public class Main
{
    /**While loop to continue*/
    private static boolean isRunning = true;
    /**List of input*/
    private static List<String> listInput;
    /**Notation actual*/
    private static Notation notation = Notation.INFIX;
    /**is Verbose mode (print list of expression)*/
    private static boolean verbose = false;


    /**
     * Print the help
     */
    public static void printHelp()
    {
        System.out.println("=== HELP START===");
        System.out.println("$> Quit program : .quit");
        System.out.println("$> Verbose mode : .verbose <true|false> ");
        printMenu();
        System.out.println("=== HELP END===\n");
    }


    /**
     * Print the menu
     */
    public static void printMenu()
    {
        System.out.println("$> Please enter an expression to evaluate or .quit to exit ");
        System.out.println("$> To change the notation, use the command .mode <mode> where <mode> is normal, complex, XX "); //TODO : complete here
        System.out.println("$> To change the notation, use the command .notation <notation> where <notation> is infix, prefix, postfix ");
    }


    /**
     * Get the input of the user or wait the command
     */
    public static void get_input()
    {
        System.out.print("$>>> ");
        Scanner scanner = new Scanner(System.in);
        String inputUser = scanner.nextLine();
        InputUser inputUser_instance = new InputUser(notation);
        listInput = InputUser.cleanInput(inputUser);
        if (listInput.size() != 0)
        {
            if (listInput.get(0).equals(".quit"))
                isRunning = false;
            else if (listInput.get(0).equals(".mode") && listInput.size() == 2)
                System.out.println("$> Mode changed to " + listInput.get(1));
            else if (listInput.get(0).equals(".notation") && listInput.size() == 2)
            {
                notation = InputUser.getNotation(listInput.get(1));
                System.out.println("$> Notation : " + notation.toString());
            }
            else if (listInput.get(0).equals(".verbose") && listInput.size() == 2)
                verbose = InputUser.isABoolean(listInput.get(1));
            else if (listInput.get(0).equals(".help"))
                printHelp();
            else if (listInput.get(0).equals(".log"))
                System.out.println("$> Displaying the log of the last 10 operations: ");

            else
            {
                inputUser_instance.setUserInput(StringRegrex.analyse(inputUser));
                System.out.println("$> " + inputUser_instance.compute(verbose));
            }
        }
        else
            System.out.println("$> Please enter a valid expression !");
    }

    /**
     * Main
     * @param args is empty
     */
    public static void main(String[] args)
    {
        System.out.print("$> Calculator Cucumber\n This is a calculator that can be used to perform basic arithmetic operations.\n");
        printMenu();
        while(isRunning)
        {
            get_input();
        }
        System.out.println("$> Bye bye !");
    }
}