package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static java.lang.Math.pow;

/** This class represents the arithmetic operation "-".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Times
 * @see Divides
 */
public final class Minus extends Operation {

    /**
     * Class constructor specifying a number of Expressions to subtract.
     *
     * @param elist The list of Expressions to subtract
     * @throws IllegalConstruction If an empty list of expressions if passed as parameter
     * @see #Minus(List<Expression>,Notation)
     */
    public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to subtract,
     * as well as the Notation used to represent the operation.
     * @param elist The list of Expressions to subtract
     * @param n     The Notation to be used to represent the operation
     * @throws IllegalConstruction If an empty list of expressions if passed as parameter
     * @see #Minus(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "-";
        neutral = 0;
    }


    /**
     * Return the minimum number between two number
     * @param l : number
     * @param r : number
     * @return number
     */
    public static MyNumber subtraction(BigDecimal l, int lExp, BigDecimal r, int rExp){
        BigDecimal newVal;
        int exp;

        if (lExp > rExp) {
            exp = lExp;
            int gap = lExp - rExp;
            newVal = l.subtract(r.divide(BigDecimal.valueOf(pow(10,gap)), MathContext.DECIMAL128));

        } else if (lExp < rExp) {
            exp = lExp;
            int gap = rExp - lExp;
            newVal = l.subtract(r.multiply(BigDecimal.valueOf(pow(10,gap))), MathContext.DECIMAL128);
        } else {
            exp = lExp;
            newVal = l.subtract(r);
        }

        return new MyNumber(newVal,exp);

    }


    /**
     * Return the minimum number between two number
     * @param l : number
     * @param r : number
     * @return number
     */
    public static MyNumber minNumber(MyNumber l, MyNumber r){
        BigDecimal lValue = l.getValue();
        BigDecimal rValue = r.getValue();
        int lExp = l.getexp();
        int rExp = r.getexp();

        MyNumber real = subtraction(lValue,lExp,rValue,rExp);

        BigDecimal lImaginary =  l.getImaginary();
        BigDecimal rImaginary =  r.getImaginary();
        int lImaginaryExp = l.getImaginaryExp();
        int rImaginaryExp = r.getImaginaryExp();

        MyNumber imaginary = subtraction(lImaginary,lImaginaryExp,rImaginary,rImaginaryExp);

        return new MyNumber(real.getValue(),real.getexp(),imaginary.getValue(),imaginary.getexp());
    }

    /**
     * The actual computation of the (binary) arithmetic subtraction of two integers
     * @param l The first BigDecimal number
     * @param r The second BigDecimal number that should be subtracted from the first
     * @return The BigDecimal number that is the result of the subtraction
     */
    public MyNumber op(MyNumber l, MyNumber r) {return minNumber(l,r);}

}
