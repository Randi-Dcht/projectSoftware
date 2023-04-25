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
        BigDecimal newVal;
        int exp;
        BigDecimal newVal2;
        int exp2;

        int aExp = l.getexp();
        int cExp = r.getexp();
        int bExp = l.getImaginaryExp();
        int dExp = r.getImaginaryExp();

        MyNumber a = new MyNumber(l.getValue(),aExp);
        MyNumber b = new MyNumber(l.getImaginary(),bExp);
        MyNumber c = new MyNumber(r.getValue(),cExp);
        MyNumber d = new MyNumber(r.getImaginary(),dExp);

        try{
            MyNumber den = additionOfMultiplication(c,c,d,d);

            MyNumber numReal = additionOfMultiplication(a,c,b,d);

            newVal = numReal.getValue().divide(den.getValue(),MathContext.DECIMAL128);
            exp=numReal.getexp()-den.getexp();

            MyNumber numImaginary1 = new Times(args).op(b,c);
            MyNumber numImaginary2 = new Times(args).op(a,d);
            MyNumber numImaginary = new Minus(args).op(numImaginary1,numImaginary2);

            newVal2 = numImaginary.getValue().divide(den.getValue(),MathContext.DECIMAL128);
            exp2=numImaginary.getexp()-den.getexp();

            return new MyNumber(newVal, exp, newVal2,exp2);
        }
        catch (IllegalConstruction e)
        {
            System.err.println("IllegalConstruction in Divides.op");
            return null;
        }
    }
}
