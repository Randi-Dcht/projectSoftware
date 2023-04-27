package calculator;

import visitor.Visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;


import static cli.Main.getDecimalNumber;
import static cli.Main.getMode;
import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;


/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public class MyNumber implements Expression
{
  private final BigDecimal value;
  private final int exp;





  private final BigDecimal imaginary;

  private final int imaginaryExp;

  private String[] list_val = new String[2];
  private String[] list_i = new String[2];

  public NumberNotation notation = getMode();
  private final int printdecimalNumber = getDecimalNumber();
  private final int decimalNumber = 15;



    /** getter method to obtain the value contained in the object
     *
     * @return BigDecimal number of the real part contained in the object
     */
  public BigDecimal getValue() { return value; }

    /** getter method to obtain the exp contained in the object
     *
     * @return int number contained in the object
     */
    public int getexp() { return exp; }

    /** getter method to obtain the value contained in the object
     *
     * @return The integer number of the imaginary part contained in the object
     */
  public BigDecimal getImaginary() { return imaginary; }

    public int getImaginaryExp() { return imaginaryExp; }

    /**
     * Constructor method
     *
     * @param value  TBigDecimal value to be contained in the object
     */
    public MyNumber(int value)
    {
        this(new BigDecimal(value));
    }




    public /*constructor*/ MyNumber(BigDecimal v) {

        list_val = decimalRefactor(v,0);
        value=new BigDecimal(list_val[0]).round(new MathContext(decimalNumber));
        exp= (parseInt( list_val[1]));


        BigDecimal tmp = new BigDecimal(0);
        imaginary = tmp.round(new MathContext(decimalNumber));
        imaginaryExp = 0;
    }
    public /*constructor*/ MyNumber(BigDecimal v, int e) {

        list_val = decimalRefactor(v,e);
        value=new BigDecimal(list_val[0]).round(new MathContext(decimalNumber));
        exp= (parseInt( list_val[1]));


        BigDecimal tmp = new BigDecimal(0);
        imaginary = tmp.round(new MathContext(decimalNumber));
        imaginaryExp = 0;
    }

    public /*constructor*/ MyNumber(BigDecimal v, BigDecimal i) {

        list_val = decimalRefactor(v,0);
        value=new BigDecimal(list_val[0]).round(new MathContext(decimalNumber));
        exp= (parseInt( list_val[1]));

        list_i = decimalRefactor(i,0);
        imaginary=new BigDecimal(list_i[0]).round(new MathContext(decimalNumber));
        imaginaryExp= (parseInt( list_i[1]));
    }

    public /*constructor*/ MyNumber(BigDecimal v, int e, BigDecimal i, int ie) {

        list_val = decimalRefactor(v,e);
        value=new BigDecimal(list_val[0]).round(new MathContext(decimalNumber));
        exp= (parseInt( list_val[1]));

        list_i = decimalRefactor(i,ie);
        imaginary=new BigDecimal(list_i[0]).round(new MathContext(decimalNumber));
        imaginaryExp= (parseInt( list_i[1]));

    }

    public /*constructor*/ MyNumber(String number) {

        String[] parts = number.split("(?=[-+i])|(?<=[-+i])");
        BigDecimal tmp_imaginary;
        BigDecimal tmp_value = BigDecimal.valueOf(Double.parseDouble(parts[0]));
        if(parts[1].equals("-"))
            tmp_imaginary = BigDecimal.valueOf(Double.parseDouble(parts[2]) * -1);
        else
            tmp_imaginary = BigDecimal.valueOf(Double.parseDouble(parts[2]));
        int tmp_exp = 0;
        int tmp_imaginaryExp = 0;

        list_val = decimalRefactor(tmp_value,tmp_exp);
        value=new BigDecimal(list_val[0]).round(new MathContext(decimalNumber));
        exp= (parseInt( list_val[1]));

        list_i = decimalRefactor(tmp_imaginary,tmp_imaginaryExp);
        imaginary=new BigDecimal(list_i[0]).round(new MathContext(decimalNumber));
        imaginaryExp= (parseInt( list_i[1]));
    }



    /**
     * accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v	The visitor object
     */
  public void accept(Visitor v) {
      v.visit(this);
  }


    /**
     * The depth of a number expression is always 0
     *
     * @return The depth of a number expression
     */
  public int countDepth() {
	  return 0;
  }

    /**
     * The number of operations contained in a number expression is always 0
     *
     * @return The number of operations contained in a number expression
     */
  public int countOps() {
	  return 0;
  }

    /**
     * The number of numbers contained in a number expression is always 1
     *
     * @return The number of numbers contained in  a number expression
     */
  public int countNbs() {
	  return 1;
  }

    /**
     * Convert a number into a String to allow it to be printed.
     *
     * @return	The String that is the result of the conversion.
     */

    public boolean isComplex(){
        return !(imaginary.signum() == 0);
    }

    public BigDecimal applyExp(BigDecimal v, int e){
        return v.multiply(BigDecimal.valueOf(pow(10, e)));
    }


    public String[] decimalRefactor(BigDecimal v, int e){
        while ( v.compareTo(BigDecimal.valueOf(0.1)) < 0 && v.compareTo(BigDecimal.valueOf(-0.1)) > 0 && !((v.round(new MathContext(decimalNumber))).compareTo(BigDecimal.ZERO) == 0))  {
            v = (v.multiply(BigDecimal.valueOf(10)));
            e = e-1;
        }
        String[] list = new String[2];
        list[0]=(v.round(new MathContext(decimalNumber))).toString();
        list[1]=Integer.toString(e);
        return list;
    }
    

    @Override
    public String toString() {
        return toString(notation);
    }



    public final String toString(NumberNotation n)
    {
        Double real = applyExp(this.value,this.exp).round(new MathContext(decimalNumber)).doubleValue();
        Double imag = applyExp(this.imaginary,this.imaginaryExp).round(new MathContext(decimalNumber)).doubleValue();

        StringBuilder printNumberDecimal = new StringBuilder();
        for (int i = 0; i < printdecimalNumber; i++) {
            printNumberDecimal.append("#");
        }

        DecimalFormat format;

        if(printdecimalNumber==0){
            format = new DecimalFormat("0");}
        else{
            format = new DecimalFormat("0."+printNumberDecimal);
        }
        DecimalFormat imFormat = new DecimalFormat("+#,##0.#;-#");
        double O = Math.PI/2;
        if(value.signum()!=0) {
            MyNumber tmp1 = Divides.divNumber(new MyNumber(imaginary, imaginaryExp), new MyNumber(value, exp));
            O = Math.atan(tmp1.getValue().multiply(BigDecimal.valueOf(pow(10, tmp1.getexp())).round(new MathContext(decimalNumber))).doubleValue());

        }
        MyNumber tmp2 = Modulus.modNumber(this);
        double r = tmp2.getValue().multiply(BigDecimal.valueOf(pow(10, tmp2.getexp())).round(new MathContext(decimalNumber))).doubleValue();


        return switch (n) {
            case CARTESIAN ->
                String.format("%s%s", "", (value.signum() == 0 && imaginary.signum() != 0) ? String.format("%si", format.format(imag)) :
                    (imaginary.signum() == 0) ? String.format("%s", format.format(real)) :
                            String.format("%s%si", format.format(real),imFormat.format(imag)));

            case POLAR ->
                    String.format("%s%s",format.format(r),
                            (Double.compare(O,0)!=0) ? String.format("*(cosine(%s) + i*sine(%s))", O, O):
                                    "");

            case EXPONENTIAL ->
                    String.format("%s%s",format.format(r),
                            (Double.compare(O,0)!=0) ? String.format("*e^(%s*i)", O):"");

            case SCIENTIFIC ->
                    String.format("%s%s", "", (value.signum() == 0 && imaginary.signum() != 0) ? String.format("i*%sx10^%s", imaginary,imaginaryExp) :
                            (imaginary.signum() == 0) ? String.format("%sx10^%s", value,exp) :
                                    String.format("%sx10^%s + i*%sx10^%s", value,exp,imaginary,imaginaryExp));

            case E_NOTATION ->
            String.format("%s%s", "", (value.signum() == 0 && imaginary.signum() != 0) ? String.format("i*%sE^%s", imaginary,imaginaryExp) :
                    (imaginary.signum() == 0) ? String.format("%sE^%s", value,exp) :
                            String.format("%sE^%s + i*%sE^%s", value,exp,imaginary,imaginaryExp));
            case BINARY -> null;
        };
      }


  /** Two MyNumber expressions are equal if the values they contain are equal
   *
   * @param o The object to compare to
   * @return  A boolean representing the result of the equality test
   */
  @Override
  public boolean equals(Object o) {
      // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
      if (o == null) return false;

      // If the object is compared to itself then return true
      if (o == this) {
          return true;
      }

      // If the object is of another type then return false
      if (!(o instanceof MyNumber)) {
          return false;
      }

      // If the real and imaginary part are equals then return true
      BigDecimal lReal = applyExp(this.value,this.exp);
      BigDecimal rReal = applyExp(((MyNumber) o).value,((MyNumber) o).exp);

      BigDecimal lImaginary = applyExp(this.imaginary,this.imaginaryExp);
      BigDecimal rImaginary = applyExp(((MyNumber) o).imaginary,((MyNumber) o).imaginaryExp);
      return lReal.compareTo(rReal)==0 &&
              lImaginary.compareTo(rImaginary)==0;
      // Used == since the contained value is a primitive value
      // If it had been a Java object, .equals() would be needed
  }

    /** The method hashCode needs to be overridden it the equals method is overridden;
     * 	otherwise there may be problems when you use your object in hashed collections
     * 	such as HashMap, HashSet, LinkedHashSet.
     *
     * @return	The result of computing the hash.
     */
  @Override
  public int hashCode() {
		return value.hashCode();
  }


  public int getInteger()
    {
        return value.intValue();
    }

}
