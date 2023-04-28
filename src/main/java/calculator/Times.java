package calculator;

import java.math.BigDecimal;
import java.util.List;

/** This class represents the arithmetic multiplication operation "*".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Plus
 * @see Divides
 */
public final class Times extends Operation {
    /**
     * Class constructor specifying a number of Expressions to multiply.
     * @param elist The list of Expressions to multiply
     * @throws IllegalConstruction If an empty list of expressions if passed as parameter
     * @see #Times(List<Expression>,Notation)
     */
    public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to multiply,
     * as well as the Notation used to represent the operation.
     * @param elist The list of Expressions to multiply
     * @param n     The Notation to be used to represent the operation
     * @throws IllegalConstruction If an empty list of expressions if passed as parameter
     * @see #Times(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "*";
        neutral = 1;
    }


    /**
     * The actual computation of the (binary) arithmetic multiplication of two BigDecimal number
     * @param l The first BigDecimal number
     * @param r The second BigDecimal number that should be multiplied with the first
     * @return The BigDecimal number that is the result of the multiplication
     */
    public static MyNumber timesNumber(MyNumber l, MyNumber r){
        BigDecimal tmpVal;
        int exp;
        int exp2;
        int exp3;
        int exp4;

        BigDecimal a = l.getValue();
        BigDecimal c = r.getValue();
        int aExp = l.getexp();
        int cExp = r.getexp();

        BigDecimal b = l.getImaginary();
        BigDecimal d = r.getImaginary();
        int bExp = l.getImaginaryExp();
        int dExp = r.getImaginaryExp();

        tmpVal=a.multiply(c);
        exp=aExp+cExp;
        MyNumber tmp1 = new MyNumber(tmpVal,exp);

        tmpVal=b.multiply(d);
        exp2=bExp+dExp;
        MyNumber tmp2 = new MyNumber(tmpVal,exp2);

        tmpVal=a.multiply(d);
        exp3=aExp+dExp;
        MyNumber tmp3 = new MyNumber(tmpVal,exp3);

        tmpVal=b.multiply(c);
        exp4=bExp+cExp;
        MyNumber tmp4 = new MyNumber(tmpVal,exp4);

        MyNumber real = Minus.minNumber(tmp1,tmp2);
        MyNumber imaginary = Plus.addNumber(tmp3,tmp4);

        return new MyNumber(real.getValue(), real.getexp(), imaginary.getValue(),imaginary.getexp());
    }


    /**
     * The actual computation of the (binary) arithmetic multiplication of two BigDecimal number
     * @param l The first BigDecimal number
     * @param r The second BigDecimal number that should be multiplied with the first
     * @return The BigDecimal number that is the result of the multiplication
     */
    public MyNumber op(MyNumber l, MyNumber r) {return timesNumber(l,r);}
}
