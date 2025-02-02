package cli;

import calculator.MyNumber;
import calculator.Notation;
import calculator.NumberNotation;
import enums.ListOperator;
import regex.StringRegex;
import java.util.List;
import java.util.Scanner;


public class Main
{
    /**Constructor*/
    private Main() {}

    /**While loop to continue*/
    private static boolean isRunning = true;
    /**Notation actual*/
    private static Notation notation = Notation.INFIX;
    private static NumberNotation mode = NumberNotation.CARTESIAN;

    private static int decimal_number = 15;
    /**is Verbose mode (print list of expression)*/
    private static boolean verbose = false;
    private static MyNumber result = null;


    /**
     * Print the help
     */
    public static void printHelp()
    {
        printing("=== HELP START===", true);
        printing("$> Quit program : .quit", true);
        printing("$> Verbose mode : .verbose <true|false>", true);
        printMenu();
        printOperator();
        printing("=== HELP END===", true);
    }


    /**
     * Print on console
     * @param message : string to print in console
     * @param lineReturn : boolean
     */
    public static void printing(String message, boolean lineReturn)
    {
        if (lineReturn)
            System.out.println(message);
        else
            System.out.print(message);
    }

    /**
     * @return is close while loop
     */
    public static boolean isRunning()
    {
        return isRunning;
    }

    /**
     * Print the error in console
     * @param message : error to print
     */
    public static void printError(String message)
    {
        printing("!!> " + message, true);
    }


    /**
     * Print the menu
     */
    public static void printMenu()
    {
        printing("$> Please enter an expression to evaluate or .quit to exit", true);
        printing("$> To change the notation, use the command .mode <mode> where <mode> is cartesian, polar, exponential, scientific or e_notation", true);
        printing("$> To change the notation, use the command .notation <notation> where <notation> is infix, prefix, postfix", true);
        printing("$> To change the number of decimal, use the command .decim <number> where <number> is the number of decimal you want (15 by default)", true);
    }

    /**
     * Print the list of operator
     * @see ListOperator
     */
    public static void printOperator()
    {
        printing("$> List of operators : [", false);
        for (ListOperator operator : ListOperator.values())
            printing("  " + operator.getValue(), false);
        printing("]", true);
    }

    /**
     * @return the mode
     */
    public static NumberNotation getMode() {
        return mode;
    }

    /**
     * Update the mode
     * @param mode is the new mode
     */
    public static void setMode(NumberNotation mode) {
        Main.mode = mode;

        if (mode.equals(NumberNotation.BINARY))
            printing("$> Warning : [base(integer)]x[notation]", true);
    }

    /**
     * @param mode : string of mode
     */
    public static void setMode(String mode)
    {
        setMode(InputUser.getMode(mode));
    }


    /**
     * @return the decimal of number
     */
    public static int getDecimalNumber() {
        return decimal_number;
    }


    /**
     * Get the input of the user or wait the command
     */
    public static void get_input()
    {
        printing("$>>> ", false);
        Scanner scanner = new Scanner(System.in);
        String inputUser = scanner.nextLine();
        InputUser inputUser_instance = new InputUser(notation,mode,decimal_number);
        /**List of input*/
        List<String> listInput = InputUser.cleanInput(inputUser);
        if (listInput.size() != 0)
        {
            if (listInput.get(0).equals(".quit"))
                isRunning = false;
            else if (listInput.get(0).equals(".help"))
                printHelp();
            else if (listInput.size() == 2)
            {
                switch (listInput.get(0)) {
                    case ".mode" -> {
                        setMode(listInput.get(1));
                        printing("$> Mode : " + mode.toString(), true);
                    }
                    case ".notation" -> {
                        notation = InputUser.getNotation(listInput.get(1));
                        printing("$> Notation : " + notation.toString(), true);
                    }
                    case ".decim" -> {
                        decimal_number = InputUser.getNumber(listInput.get(1));
                        printing("$> Number of decimals : " + decimal_number, true);
                    }
                    case ".verbose" -> verbose = listInput.get(1).equals("true");
                }
            }
            else
            {
                inputUser_instance.setUserInput(StringRegex.analyse(inputUser));
                result = inputUser_instance.compute(verbose);
                if (result != null)
                    printing("$> " + result, true);
                else
                    printError("Error in the expression");
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
            try
            {
                get_input();
            }
            catch (Exception e)
            {
                printError(e.getMessage());
            }
        }
        printing("$> Bye bye !", true);
    }

    /**
     * @return print all result
     */
    public static boolean isVerbose()
    {
        return verbose;
    }

    /**
     * @return the notation
     */
    public static Notation getNotation()
    {
        return notation;
    }
}