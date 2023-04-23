package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/** This class represents the arithmetic division operation "/".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 */
public final class Divides extends Operation
{
    /**
     * Class constructor specifying a number of Expressions to divide.
     *
     * @param elist The list of Expressions to divide
     * @throws IllegalConstruction If an empty list of expressions if passed as parameter
     * @see #Divides(List<Expression>,Notation)
     */
    public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to divide,
     * as well as the notation used to represent the operation.
     *
     * @param elist The list of Expressions to divide
     * @param n     The Notation to be used to represent the operation
     * @throws IllegalConstruction If an empty list of expressions if passed as parameter
     * @see #Divides(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "/";
        neutral = 1;
    }

    private MyNumber additionOfMultiplication(MyNumber a, MyNumber b, MyNumber c, MyNumber d) throws IllegalConstruction{
        MyNumber den1 = new Times(args).op(a,b);
        MyNumber den2 = new Times(args).op(c,d);
        return new Plus(args).op(den1,den2);

    }

    /**
     * The actual computation of the (binary) arithmetic division of two BigDecimal number
     *
     * @param l The first BigDecimal number
     * @param r The second BigDecimal number that should divide the first
     * @return The BigDecimal number that is the result of the division
     */
    public MyNumber op(MyNumber l, MyNumber r) {
        BigDecimal new_val;
        int exp;
        BigDecimal new_val2;
        int exp2;

        int a_exp = l.getexp();
        int c_exp = r.getexp();
        int b_exp = l.getImaginaryExp();
        int d_exp = r.getImaginaryExp();

        MyNumber a = new MyNumber(l.getValue(),a_exp);
        MyNumber b = new MyNumber(l.getImaginary(),b_exp);
        MyNumber c = new MyNumber(r.getValue(),c_exp);
        MyNumber d = new MyNumber(r.getImaginary(),d_exp);

        try{
            MyNumber den = additionOfMultiplication(c,c,d,d);

            MyNumber numReal = additionOfMultiplication(a,c,b,d);

            new_val = numReal.getValue().divide(den.getValue(),MathContext.DECIMAL128);
            exp=numReal.getexp()-den.getexp();

            MyNumber numImaginary1 = new Times(args).op(b,c);
            MyNumber numImaginary2 = new Times(args).op(a,d);
            MyNumber numImaginary = new Minus(args).op(numImaginary1,numImaginary2);

            new_val2 = numImaginary.getValue().divide(den.getValue(),MathContext.DECIMAL128);
            exp2=numImaginary.getexp()-den.getexp();

            return new MyNumber(new_val, exp, new_val2,exp2);
        }
        catch (IllegalConstruction e)
        {
            return l;
        }
    }
}
