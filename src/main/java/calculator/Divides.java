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

    public static MyNumber additionOfMultiplication(MyNumber a, MyNumber b, MyNumber c, MyNumber d){
        MyNumber den1 = Times.timesNumber(a,b);
        MyNumber den2 = Times.timesNumber(c,d);
        return Plus.addNumber(den1,den2);

    }

    /**
     * The actual computation of the (binary) arithmetic division of two BigDecimal number
     *
     * @param l The first BigDecimal number
     * @param r The second BigDecimal number that should divide the first
     * @return The BigDecimal number that is the result of the division
     */

    public static  MyNumber divNumber(MyNumber l, MyNumber r){

        BigDecimal newVal;
        int exp;
        BigDecimal newVal2;
        int exp2;

        int aExp = l.getexp();
        int cExp = r.getexp();
        int bExp = l.getImaginaryExp();
        int dExp = r.getImaginaryExp();

        //if(((l.getValue().round(new MathContext(15))).compareTo(BigDecimal.ZERO)) == 0){
        //    return new MyNumber(-1);
        //}

        MyNumber a = new MyNumber(l.getValue(),aExp);
        MyNumber b = new MyNumber(l.getImaginary(),bExp);
        MyNumber c = new MyNumber(r.getValue(),cExp);
        MyNumber d = new MyNumber(r.getImaginary(),dExp);

        MyNumber den = additionOfMultiplication(c,c,d,d);

        MyNumber numReal = additionOfMultiplication(a,c,b,d);

        newVal = numReal.getValue().divide(den.getValue(),MathContext.DECIMAL128);
        exp=numReal.getexp()-den.getexp();

        MyNumber numImaginary1 = Times.timesNumber(b,c);
        MyNumber numImaginary2 = Times.timesNumber(a,d);
        MyNumber numImaginary = Minus.minNumber(numImaginary1,numImaginary2);

        newVal2 = numImaginary.getValue().divide(den.getValue(),MathContext.DECIMAL128);
        exp2=numImaginary.getexp()-den.getexp();

        return new MyNumber(newVal, exp, newVal2,exp2);
    }

    /**
     * The actual computation of the (binary) arithmetic division of two BigDecimal number
     *
     * @param l The first BigDecimal number
     * @param r The second BigDecimal number that should divide the first
     * @return The BigDecimal number that is the result of the division
     */
    public MyNumber op(MyNumber l, MyNumber r) {return divNumber(l,r);}
}
