package cli;

import calculator.*;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to process the input of user
 */
public class InputUser
{

    //--------------------STATIC--------------------//
    /**List of operator*/
    protected static String listOperators = "+-*/";
    /**List of number*/
    protected static String listNumbers = "0123456789";
    //protected static String listRealNumbers = "0123456789.";


    /**
     * Method to clean the input of user (remove space)
     * @param input : string input of user
     * @return list of string without space
     */
    public static List<String> cleanInput(String input)
    {
        List<String> listInput = new ArrayList<>();
        for (String cut_list : input.split(" "))
        {
            if (!cut_list.equals("") && !cut_list.equals(" "))
            {
                listInput.add(cut_list);
            }
        }
        return listInput;
    }


    /**
     * @return boolean : is a number between 0 and 9
     */
    public static boolean isNumber(String input)
    {
        return listNumbers.contains(input);
    }

    public static boolean isDecimalNumber(String input)
    {
        String[] parts = input.split(".");
        if(isNumber(parts[0])&&isNumber(parts[1])){
            return true;
        }
        return false;
    }

    public static boolean isENotationNumber(String input)
    {
        String[] parts = input.split("E");
        if(isNumber(parts[0])&&isNumber(parts[1])){
            return true;
        }

        if(isDecimalNumber(parts[0])&&isNumber(parts[1])){
            return true;
        }
        return false;
    }

    public static boolean isScientificNotationNumber(String input)
    {
        String[] parts = input.split("x10\\^");
        if(isNumber(parts[0])&&isNumber(parts[1])){
            return true;
        }

        if(isDecimalNumber(parts[0])&&isNumber(parts[1])){
            return true;
        }
        return false;
    }


    /**
     * @return boolean : is an operator
     */
    public static boolean isOperator(String input)
    {
        return listOperators.contains(input);
    }


    /**
     * @param input : string input of user
     * @return notation : prefix, postfix or infix
     *                       default : infix
     */
    public static Notation getNotation(String input)
    {
        Notation notation;
        switch (input.toLowerCase())
        {
            case "prefix"		->	notation = Notation.PREFIX;
            case "postfix"		->	notation = Notation.POSTFIX;
            default				->	notation = Notation.INFIX;
        }
        return notation;
    }


    /**
     * @param inputUser : string input of user (operator)
     * @param params : list of expression (parameters)
     * @param notation : notation of the operation
     * @return expression of the operation
     */
    public static Expression getOperator(String inputUser, List<Expression> params, Notation notation)
    {
        Operation e = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (inputUser){
                case "+"	->	e = new Plus(params, notation);
                case "-"	->	e = new Minus(params, notation);
                case "*"	->	e = new Times(params, notation);
                case "/"	->	e = new Divides(params, notation);
                default		->	System.out.println("Error"); //TODO : handle exception
            }
        } catch (IllegalConstruction ignored){}//TODO : handle exception
        return e;
    }


    /**
     * @return boolean : is a boolean (true or false)
     */
    public static boolean isABoolean(String input)
    {
        return input.equalsIgnoreCase("true");
    }


    //--------------------INSTANCE--------------------//
    /**List of expression*/
    private final List<Expression> list_of_expression = new ArrayList<>();
    /**Notation actual*/
    private Notation notation;
    /**List of string input of user without space*/
    private List<String> user_input_list;


    /**
     * Constructor
     * @param notation : notation of the operation
     */
    public InputUser(Notation notation) //TODO : add mode
    {
        this.notation = notation;
    }


    /**
     * Set the notation
     * @param notation : notation of the operation
     */
    public void setNotation(Notation notation)
    {
        this.notation = notation;
    }


    /**
     * Set the input of user
     * @param inputUser : list of string input of user without space
     */
    public void setUserInput(List<String> inputUser)
    {
        this.user_input_list = inputUser;
    }


    /**
     * Compute the input of user
     * @param isVerbose : boolean to display the expression
     */
    public MyNumber compute(boolean isVerbose)
    {
        String operator = null;
        for (String s : user_input_list)
        {
            s.replaceAll(",",".");
            if (isNumber(s))
                list_of_expression.add(new MyNumber(new BigDecimal(s)));

            else if (isOperator(s))
                operator = s;

            else if (isENotationNumber(s)) {
                String[] parts = s.split("E");
                list_of_expression.add(new MyNumber(new BigDecimal(parts[0]),Integer.parseInt(parts[1])));
            }
            else if (isScientificNotationNumber(s)) {
                String[] parts = s.split("x10\\^");
                list_of_expression.add(new MyNumber(new BigDecimal(parts[0]),Integer.parseInt(parts[1])));
            }
        }
        if (operator != null)
        {
            Expression e = getOperator(operator, list_of_expression, this.notation);
            if (isVerbose)
                System.out.println("$> " + e.toString());
            return new Calculator().eval(e);
        }
        return new MyNumber(new BigDecimal(0),0);
    }
}