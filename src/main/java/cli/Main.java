package cli;

import calculator.*;
import enums.ListOperator;
import parser.StringRegex;
import java.util.List;
import java.util.Scanner;


public class Main
{
    /**While loop to continue*/
    private static boolean isRunning = true;
    /**Notation actual*/
    private static Notation notation = Notation.INFIX;
    private static NumberNotation mode = NumberNotation.CARTESIAN;

    private static int decimal_number = 15;
    /**is Verbose mode (print list of expression)*/
    private static boolean verbose = false;
    private static MyNumber result = null;

    private static Memory memory = new Memory();
    private static Memory log = new Memory();


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
            else if (listInput.get(0).equals(".mode") && listInput.size() == 2){
                setMode(listInput.get(1));
                printing("$> Mode : " + mode.toString(), true);
            }
            else if (listInput.get(0).equals(".notation") && listInput.size() == 2)
            {
                notation = InputUser.getNotation(listInput.get(1));
                printing("$> Notation : " + notation.toString(), true);
            }
            else if (listInput.get(0).equals(".decim") && listInput.size() == 2)
            {
                decimal_number = InputUser.getNumber(listInput.get(1));
                printing("$> Number of decimals : " + decimal_number, true);
            }
            else if (listInput.get(0).equals(".verbose") && listInput.size() == 2)
                verbose = listInput.get(1).equals("true");
            else if (listInput.get(0).equals(".help"))
                printHelp();
            else if (listInput.get(0).equals(".log")) {
                if (listInput.size() == 1) {
                    printing("$> Displaying all log", true);
                    log.display();
                } else {
                    printing("$> Displaying last " + listInput.get(1) + " data", true);
                    log.displayLastData(Integer.parseInt(listInput.get(1)));
                }
            }
            else if (listInput.get(0).equals(".memory")) {
                if (listInput.size() == 1) {
                    printing("$> Displaying all memory", true);
                    memory.display();
                } else {
                    printing("$> Displaying last " + listInput.get(1) + " data", true);
                    memory.displayLastData(Integer.parseInt(listInput.get(1)));
                }
            }
            else if (listInput.get(0).equals(".remove")) {
                if (listInput.size() != 2) {
                    printing("$> please enter valid syntax", true);
                }
                try {
                    memory.remove(listInput.get(1));
                    printing("$> Removing variable: "+ listInput.get(1), true);
                } catch (IllegalArgumentException e) {
                    printing("$> " + e.getMessage(), true);
                }
            }
            else if (listInput.get(0).equals(".clear"))
            {
                if (listInput.size() != 2) {
                    printing("$> please enter valid syntax", true);
                }
                if (listInput.get(1).equals("memory")) {
                    printing("$> Clearing all variables in memory", true);
                    memory.clear();
                } else if (listInput.get(1).equals("log")) {
                    printing("$> Clearing all variables in log", true);
                    log.clear();
                } else {
                    printing("$> please enter valid syntax", true);
                }
            } else if (listInput.get(0).equals(".rename"))
                try {
                    if (listInput.size() != 4) {
                        printing("$> please enter valid syntax", true);
                    }
                    if (listInput.get(1).equals("memory")) {
                        Variable variable = memory.get(listInput.get(2));
                        variable.setName(listInput.get(3));
                        printing("$> Renaming variable: " + listInput.get(2) + " to " + listInput.get(3), true);
                    } else if (listInput.get(1).equals("log")) {
                        Variable variable = log.get(listInput.get(2));
                        variable.setName(listInput.get(3));
                        printing("$> Renaming variable: " + listInput.get(2) + " to " + listInput.get(3), true);
                    } else {
                        printing("$> please enter valid syntax", true);
                    }
                } catch (IllegalArgumentException e) {
                    printing("$> " + e.getMessage(), true);
                }
            else if (listInput.get(0).equals(".set_size")) {
                if (listInput.size() != 2) {
                    printing("$> please enter valid syntax", true);
                }
                try {
                    memory.setMaxSize(Integer.parseInt(listInput.get(1)));
                    printing("$> Setting new size of memory: " + listInput.get(1), true);
                } catch (IllegalArgumentException e) {
                    printing("$> " + e.getMessage(), true);
                }
            }
            else if (listInput.get(0).equals(".size")) {
                if (memory.getMaxSize() == -1)
                    printing("$> displaying maximum size of memory: unlimited", true);
                else
                    printing("$> displaying maximum size of memory: " + memory.getMaxSize(), true);
            }
            else if (listInput.get(0).equals(".store")) {
                inputUser_instance.setLog(log);
                inputUser_instance.setMemory(memory);
                inputUser_instance.setName(listInput.get(1));
                inputUser_instance.setUserInput(StringRegex.analyse(inputUser));
                result = inputUser_instance.compute(verbose);
                if (result != null) {
                    printing("$> Adding new variable: " + listInput.get(1), true);
                    printing("$> " + result, true);
                } else {
                    printError("Error in the expression");
                }
                inputUser_instance.setMemory(null);
                inputUser_instance.setName(null);
            }
            else
            {
                inputUser_instance.setLog(log);
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
        memory.loadMemory();
        log.loadLog();
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
        memory.saveMemory();
        log.saveLog();
        printing("$> Bye bye !", true);
    }
}