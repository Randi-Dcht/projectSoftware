package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;


/**
 * Complex operation
 */
public class Modulus extends Operation
{
    public /*constructor*/ Modulus(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to modulus,
     * as well as the Notation used to represent the operation.
     * @param elist The list of Expressions to multiply
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Modulus(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Modulus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
        symbol = "modulus";
        neutral = 0;
    }


    /***/
    public static MyNumber modNumber(MyNumber l){
        MyNumber a = new MyNumber(l.getValue(),l.getexp());
        MyNumber b = new MyNumber(l.getImaginary(),l.getImaginaryExp());


        MyNumber tmp1 = Times.timesNumber(a,a);
        MyNumber tmp2 = Times.timesNumber(b,b);
        MyNumber tmp = Plus.addNumber(tmp1,tmp2);

        BigDecimal sqrt = tmp.getValue().sqrt(MathContext.DECIMAL128);
        return  new MyNumber(sqrt, tmp.getexp() /2);
    }

    /**
     * The actual computation of the arithmetic modulus of a number
     * @param l The number
     * @return The number that is the result of the modulus
     */
    public MyNumber op(MyNumber l, MyNumber r) {return modNumber(l);}

    /**
     * The actual computation of the arithmetic modulus of a number
     * @param n The number
     * @return The number that is the result of the modulus
     */
    public MyNumber op(MyNumber n) {
        return op(n , null);
    }
}
