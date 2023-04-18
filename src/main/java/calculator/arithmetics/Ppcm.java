package calculator.arithmetics;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.Operation;
import java.util.List;


/**
 * Class to implement : Ppcm
 * @author Randi-Dcht
 * @param : elist The list of expressions passed as argument to the arithmetic operation
 * @param : notation The notation used to display the operation
 * @throws IllegalConstruction Exception thrown if a null list of expressions is passed as argument
 */
public class Ppcm extends Operation
{


    /**
     * It is not allowed to construct an operation with a null list of expressions.
     * Note that it is allowed to have an EMPTY list of arguments.
     * @param elist The list of expressions passed as argument to the arithmetic operation
     * @throws IllegalConstruction Exception thrown if a null list of expressions is passed as argument
     */
    public Ppcm(List<Expression> elist) throws IllegalConstruction
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
    public Ppcm(List<Expression> elist, Notation notation) throws IllegalConstruction
    {
        super(elist, notation);
        symbol = "ppcm";
        neutral = 0;
    }


    /**
     * Algorithm to compute the ppcm
     * @param a : integer
     * @param b : integer
     * @return ppcm (integer)
     */
    public int ppcm(int a, int b) throws IllegalConstruction
    {
        return (a * b) / new Pgcd(args).op(a,b);
    }


    /**
     * Abstract method representing the actual binary arithmetic operation to compute
     * @param l first argument of the binary operation
     * @param r second argument of the binary operation
     * @return result of computing the binary operation
     *       error : return -1
     */
    @Override
    public int op(int l, int r)
    {
        if (l == 0 || r == 0)
            return 0;
        try
        {
            return ppcm(l, r);
        }
        catch (IllegalConstruction e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
