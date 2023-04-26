package cli;

import calculator.*;
import calculator.arithmetics.*;
import enums.TypeString;
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
     * @param operator : string input of user (string -> operator)
     * @param params : list of expression (parameters)
     * @param notation : notation of the operation
     * @return expression of the operation
     */
    public static Expression getOperator(Typos operator, List<Expression> params, Notation notation)
    {
        Operation e = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (operator.getOperator()){
                case ADD -> e = new Plus(params, notation);
                case SUB -> e = new Minus(params, notation);
                case MUL -> e = new Times(params, notation);
                case DIV -> e = new Divides(params, notation);
                case COMB -> e = new Combinatorial(params, notation);
                case GCD -> e = new Eucledian(params, notation);
                case EUCLIDEAN -> e = new EuclidianDivides(params, notation);
                case FACTO -> e = new Facto(params, notation);
                case MODULO -> e = new Modulo(params, notation);
                case PGCD -> e = new Pgcd(params, notation);
                case PPCM -> e = new Ppcm(params, notation);
                case POW -> e = new Pow(params, notation);
                case PRIME -> e = new PrimeNumber(params, notation);
                default -> System.out.println("Error"); //TODO : handle exception
            }
        } catch (IllegalConstruction ignored){}//TODO : handle exception
        return e;
    }


    //--------------------INSTANCE--------------------//
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

    public List<Typos> getUserInput()
    {
        return this.user_input_list;
    }

    public Notation getNotation()
    {
        return this.notation;
    }


    /**
     * Compute the input of user
     * @param isVerbose : boolean to display the expression
     */
    public MyNumber compute(boolean isVerbose)
    {
        Expression e = null;
        int args = 0;
        List<Expression> list_of_expression_data = new ArrayList<>();
        Stack<Expression> stack = new Stack<>();

        for (Typos s : ConvertNotation.transformNotation(notation, this.user_input_list, isVerbose))
        {

            if (s.getType().equals(TypeString.INTEGER) || s.getType().equals(TypeString.REAL))
                stack.push(new MyNumber(new BigDecimal(s.getValue())));

            else if (s.getType().equals(TypeString.COMPLEX) || s.getType().equals(TypeString.REAL_COMPLEX)){
                if(s.getValue().contains("-"))
                    stack.push(new MyNumber("0"+s.getValue()));
                else
                    stack.push(new MyNumber("0+"+s.getValue()));
            }

            else if (s.getType().equals(TypeString.OPERATOR))
            {
               while(!stack.isEmpty() && s.getOperator().getNumberArgs() > args)
               {
                   list_of_expression_data.add(0, stack.pop());
                   args++;
               }
                args = 0;
                e = getOperator(s, list_of_expression_data, Notation.POSTFIX);
                list_of_expression_data.clear();


                stack.push(e);
            }
            else if (s.getType().equals(TypeString.E_NOTATION))
            {
                String[] parts = s.getValue().split("E");
                stack.push(new MyNumber(new BigDecimal(parts[0]),Integer.parseInt(parts[1])));
            }
            else if (s.getType().equals(TypeString.SCIENTIFIC))
            {
                String[] parts = s.getValue().split("x10\\^");
                stack.push(new MyNumber(new BigDecimal(parts[0]),Integer.parseInt(parts[1])));
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