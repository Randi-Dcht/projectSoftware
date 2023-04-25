package cli;

import calculator.*;
import enums.TypeString;
import parser.StringRegrex;
import parser.Typos;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * Class to process the input of user
 */
public class InputUser
{

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
     * @param input : string input of user
     * @return notation : prefix, postfix or infix
     *                       default : infix
     */
    public static Notation getNotation(String input)
    {
        Notation notation;
        switch (input.toLowerCase())
        {
            case "prefix" -> notation = Notation.PREFIX;
            case "postfix" -> notation = Notation.POSTFIX;
            default -> notation = Notation.INFIX;
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
                case "+" -> e = new Plus(params, notation);
                case "-" -> e = new Minus(params, notation);
                case "*" -> e = new Times(params, notation);
                case "/" -> e = new Divides(params, notation);
                default -> System.out.println("Error"); //TODO : handle exception
            }
        } catch (IllegalConstruction ignored){}//TODO : handle exception
        return e;
    }


    //--------------------INSTANCE--------------------//
    /**List of expression*/
    private final List<Expression> list_of_expression = new ArrayList<>();
    /**Notation actual*/
    private Notation notation;
    /**List of string input of user without space*/
    private List<Typos> user_input_list;


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
    public void setUserInput(List<Typos> inputUser)
    {
        this.user_input_list = inputUser;
    }


    /**
     * Compute the input of user
     * @param isVerbose : boolean to display the expression
     */
    public MyNumber compute(boolean isVerbose)
    {
        //String operator = null;
        Expression e = null;

        for (Typos s : ConvertNotation.transformNotation(Notation.INFIX, this.user_input_list, isVerbose))
        {
            if (s.getType().equals(TypeString.INTEGER))
                list_of_expression.add(new MyNumber(new BigDecimal(s.getValue())));
            else if (s.getType().equals(TypeString.OPERATOR))
            {
                e = getOperator(s.getValue(), list_of_expression, Notation.POSTFIX);
                list_of_expression.clear();
                list_of_expression.add(e);
            }
            else if (s.getType().equals(TypeString.E_NOTATION))
            {
                String[] parts = s.getValue().split("E");
                list_of_expression.add(new MyNumber(new BigDecimal(parts[0]),Integer.parseInt(parts[1])));
            }
            else if (s.getType().equals(TypeString.SCIENTIFIC))
            {
                String[] parts = s.getValue().split("x10\\^");
                list_of_expression.add(new MyNumber(new BigDecimal(parts[0]),Integer.parseInt(parts[1])));
            }
        }

        if (e != null)
        {
            if (isVerbose)
                System.out.println("$> " + e.toString());
            return new Calculator().eval(e);
        }
        return new MyNumber(new BigDecimal(0),0);
    }
}