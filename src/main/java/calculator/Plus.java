package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static java.lang.Math.pow;

/** This class represents the arithmetic sum operation "+".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Divides
 */
public final class Plus extends Operation
 {

  /**
   * Class constructor specifying a number of Expressions to add.
   *
   * @param elist The list of Expressions to add
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Plus(List<Expression>,Notation)
   */
  public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to add,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to add
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Plus(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Plus(List<Expression> elist, Notation n) throws IllegalConstruction {
  	super(elist,n);
  	symbol = "+";
  	neutral = 0;
  }

public static MyNumber addition(BigDecimal l, int lExp, BigDecimal r, int rExp){
    BigDecimal newVal;
    int exp;

    if (lExp>rExp){
        exp=lExp;
        int gap=lExp-rExp;
        newVal = l.add(r.divide(BigDecimal.valueOf(pow(10,gap)), MathContext.DECIMAL128));

    }
    else if (lExp<rExp){
        exp=rExp;
        int gap=rExp-lExp;
        newVal = r.add(l.divide(BigDecimal.valueOf(pow(10,gap)), MathContext.DECIMAL128));
    }
    else {
        exp=lExp;
        newVal = r.add(l);
    }
    return new MyNumber(newVal,exp);
}

public static MyNumber addNumber(MyNumber l, MyNumber r){

    BigDecimal lValue =  l.getValue();
    BigDecimal rValue =  r.getValue();
    int lExp = l.getexp();
    int rExp = r.getexp();

    MyNumber real = addition(lValue,lExp,rValue,rExp);

    BigDecimal lImaginary =  l.getImaginary();
    BigDecimal rImaginary =  r.getImaginary();
    int lImaginaryExp = l.getImaginaryExp();
    int rImaginaryExp = r.getImaginaryExp();

    MyNumber imaginary = addition(lImaginary,lImaginaryExp,rImaginary,rImaginaryExp);


    return new MyNumber(real.getValue(),real.getexp(),imaginary.getValue(),imaginary.getexp());

}

  /**
   * The actual computation of the (binary) arithmetic addition of two integers
   *
   * @param l The first BigDecimal number
   * @param r The second BigDecimal number that should be added to the first
   * @return The BigDecimal number that is the result of the addition
   */
  public MyNumber op(MyNumber l, MyNumber r) {return addNumber(l,r);}

}
