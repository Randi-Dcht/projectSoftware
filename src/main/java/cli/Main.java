package cli;

import calculator.Notation;
import enums.ListOperator;
import parser.StringRegrex;
import java.util.List;
import java.util.Scanner;


public class Main
{
    /**While loop to continue*/
    private static boolean isRunning = true;
    /**Notation actual*/
    private static Notation notation = Notation.INFIX;
    /**is Verbose mode (print list of expression)*/
    private static boolean verbose = false;


    /**
     * Print the help
     */
    public static void printHelp()
    {
        printing("=== HELP START===", true);
        printing("$> Quit program : .quit", true);
        printing("$> Verbose mode : .verbose <true|false> ", true);
        printMenu();
        printOperator();
        printing("=== HELP END===\n", true);
    }


    public static void printing(String message, boolean lineReturn)
    {
        if (lineReturn)
            System.out.println(message);
        else
            System.out.print(message);
    }


    public static void printError(String message)
    {
        printing("!!> " + message, true);
    }


    /**
     * Print the menu
     */
    public static void printMenu()
    {
        printing("$> Please enter an expression to evaluate or .quit to exit ", true);
        printing("$> To change the notation, use the command .mode <mode> where <mode> is normal, complex, XX ", true); //TODO : complete here
        printing("$> To change the notation, use the command .notation <notation> where <notation> is infix, prefix, postfix ", true);
    }

    public static void printOperator()
    {
        printing("$> List of operators : [", false);
        for (ListOperator operator : ListOperator.values())
            printing("  " + operator.getValue(), false);
        printing("]", true);
    }


    /**
     * Get the input of the user or wait the command
     */
    public static void get_input()
    {
        printing("$>>> ", false);
        Scanner scanner = new Scanner(System.in);
        String inputUser = scanner.nextLine();
        InputUser inputUser_instance = new InputUser(notation);
        /**List of input*/
        List<String> listInput = InputUser.cleanInput(inputUser);
        if (listInput.size() != 0)
        {
            if (listInput.get(0).equals(".quit"))
                isRunning = false;
            else if (listInput.get(0).equals(".mode") && listInput.size() == 2)
                printing("$> Mode changed to " + listInput.get(1), true);
            else if (listInput.get(0).equals(".notation") && listInput.size() == 2)
            {
                notation = InputUser.getNotation(listInput.get(1));
                printing("$> Notation : " + notation.toString(), true);
            }
            else if (listInput.get(0).equals(".verbose") && listInput.size() == 2)
                verbose = listInput.get(1).equals("true");
            else if (listInput.get(0).equals(".help"))
                printHelp();
            else if (listInput.get(0).equals(".log"))
                printing("$> Displaying the log of the last 10 operations: ", true);

            else
            {
                inputUser_instance.setUserInput(StringRegrex.analyse(inputUser));
                printing("$> " + inputUser_instance.compute(verbose), true);
            }
        }
        else
            printing("$> Please enter a valid expression !", true);
    }

    /**
     * Main
     * @param args is empty
     */
    public static void main(String[] args)
    {
        printing("$> Calculator Cucumber\n This is a calculator that can be used to perform basic arithmetic operations.\n", false);
        printMenu();
        printOperator();
        while(isRunning)
        {
            get_input();
        }
        printing("$> Bye bye !", true);
    }
}