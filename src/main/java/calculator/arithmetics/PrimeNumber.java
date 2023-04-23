package calculator.arithmetics;

import calculator.*;

import java.util.List;


/**
 * Class to implement : prime number between two numbers
 * @author Randi-Dcht
 */
public class PrimeNumber extends Operation
{


    /**
     * It is not allowed to construct an operation with a null list of expressions.
     * Note that it is allowed to have an EMPTY list of arguments.
     * @param elist The list of expressions passed as argument to the arithmetic operation
     * @throws IllegalConstruction Exception thrown if a null list of expressions is passed as argument
     */
    public PrimeNumber(List<Expression> elist) throws IllegalConstruction
    {
        this(elist, null);
    }


    /**
     * It is not allowed to construct an operation with a null list of expressions.
     * Note that it is allowed to have an EMPTY list of arguments.
     * @param elist The list of expressions passed as argument to the arithmetic operation
     * @param notation The notation used to display the operation
     * @throws IllegalConstruction Exception thrown if a null list of expressions is passed as argument
     */
    public PrimeNumber(List<Expression> elist, Notation notation) throws IllegalConstruction
    {
        super(elist, notation);
        symbol = "prime";
        neutral = 1;
    }

    /**
     * Abstract method representing the actual binary arithmetic operation to compute
     *
     * @param l first argument of the binary operation
     * @param r second argument of the binary operation
     * @return result of computing the binary operation
     */
    @Override
    public MyNumber op(MyNumber l, MyNumber r)
    {
        return calculate(l.getInteger(), r.getInteger()) == 1 ? new MyNumber(1) : new MyNumber(0);
    }


    /**
     * Allows to compute the prime of two numbers
     * @param a : integer
     * @param b : integer
     * @return a pgcd (integer)
     */
    public int calculate(int a, int b)
    {
        return Pgcd.pgcd(a, b);
    }
}
