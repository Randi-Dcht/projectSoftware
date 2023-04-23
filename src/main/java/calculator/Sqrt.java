package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class Sqrt extends Operation
{
    public /*constructor*/ Sqrt(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to square root,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to multiply
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Sqrt(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Sqrt(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
        symbol = "sqrt";
        neutral = 1;
    }

    public MyNumber op(MyNumber l, MyNumber r) {
        BigDecimal a = l.getValue();
        int exp = l.getexp();
        int imaginaryExp = l.getImaginaryExp();
        BigDecimal real;
        BigDecimal imaginary;
        if(!l.isComplex()){
            if(a.signum()<0){
                imaginary = a.abs().sqrt(MathContext.DECIMAL128);
                return new MyNumber(new BigDecimal(0),0,imaginary,exp);
            }

            else {
                real = a.sqrt(MathContext.DECIMAL128);
                return new MyNumber(real,exp);
            }
        }


        else{

            BigDecimal b = l.getImaginary();

            try{
                MyNumber z = new Modulus(args).op(l);
                MyNumber den = new MyNumber(new BigDecimal("2"));

                MyNumber numReal = new Plus(args).op(z,new MyNumber(a,exp));
                MyNumber tmp = new Divides(args).op(numReal,den);
                real = tmp.getValue().sqrt(MathContext.DECIMAL128);
                int expReal = tmp.getexp();

                MyNumber li = new Divides(args).op(new MyNumber(b,imaginaryExp),new MyNumber(b.abs(),imaginaryExp));

                MyNumber numImaginary = new Minus(args).op(z,new MyNumber(a,exp));
                MyNumber tmp2 = new Divides(args).op(numImaginary,den);
                BigDecimal i = tmp2.getValue().sqrt(MathContext.DECIMAL128);
                int e = tmp2.getexp();

                MyNumber n = new Times(args).op(li,new MyNumber(i,e));

                imaginary = n.getValue();
                int expImaginary = n.getexp();

                return new MyNumber(real,expReal,imaginary,expImaginary);
            }
            catch (IllegalConstruction e)
            {
                return l;
            }
        }


    }

    /**
     * The actual computation of the (binary) arithmetic square root of one number
     * @param n The number
     * @return The number that is the result of the square root
     */
    public MyNumber op(MyNumber n) {
        return op(n , null);
    }
}

