package calculator;


import java.math.BigDecimal;
import java.math.MathContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A very simple calculator in Java
 * University of Mons - UMONS
 * Département d'Informatique
 * Faculté des Sciences
 *
 * @author tommens
 */
public class Main {

	/**
	 * This is the main method of the application.
	 * It provides examples of how to use it to construct and evaluate arithmetic expressions.
	 *
	 * @param args	Command-line parameters are not used in this version
	 */
	public static void main(String[] args) {

  	Expression e;
  	Calculator c = new Calculator();

	  MathContext precision = new MathContext(15);


	  Memory log = new Memory();
	  log.loadLog();
	  //log.clearMemory();
	  //log.add("test",new MyNumber(new BigDecimal(5.5)),null);
	  System.out.println(log.getMemory());


		try{

		e = new MyNumber(new BigDecimal(-8.5,precision) );
		c.print(e);
		c.eval(e);

	    List<Expression> params = new ArrayList<>();
	    Collections.addAll(params, new MyNumber(new BigDecimal(-3.5,precision) ), new MyNumber(new BigDecimal(-4,precision) ), new MyNumber(new BigDecimal(5,precision)));
	    e = new Plus(params,Notation.PREFIX);
		log.add("test",new MyNumber(new BigDecimal(-4,precision) ),e);
		c.printExpressionDetails(e);
		c.eval(e);

		List<Expression> params7 = new ArrayList<>();
		Collections.addAll(params7, new MyNumber(new BigDecimal(-3,precision) ), new MyNumber(new BigDecimal(-4,precision) ), new MyNumber(new BigDecimal(5,precision)));
		e = new Plus(params7,Notation.PREFIX);
		c.printExpressionDetails(e);
		c.eval(e);

		List<Expression> params2 = new ArrayList<>();
		Collections.addAll(params2, new MyNumber(new BigDecimal(-5,precision)), new MyNumber(new BigDecimal(3,precision)));
		e = new Minus(params2, Notation.INFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params6 = new ArrayList<>();
		Collections.addAll(params6, new MyNumber(new BigDecimal(7.99,precision)), new MyNumber(new BigDecimal(2.75,precision)));
		e = new Divides(params6, Notation.INFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params9 = new ArrayList<>();
		Collections.addAll(params9, new MyNumber(new BigDecimal(1)), new MyNumber(new BigDecimal(3)));
		e = new Divides(params9, Notation.INFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params8 = new ArrayList<>();
		Collections.addAll(params8, new MyNumber(new BigDecimal(7.9900000000000002131628207280300557613372802734375)), new MyNumber(new BigDecimal(0.01,precision)));
		e = new Times(params8, Notation.INFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params3 = new ArrayList<>();
		Collections.addAll(params3, new Plus(params), new Minus(params2));
		e = new Times(params3);
		c.printExpressionDetails(e);
		c.eval(e);

		List<Expression> params4 = new ArrayList<>();
		Collections.addAll(params4, new Plus(params), new Minus(params2), new MyNumber(new BigDecimal(-5)));
		e = new Divides(params4,Notation.POSTFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params5 = new ArrayList<>();
		Collections.addAll(params5, new MyNumber(new BigDecimal(6.95,precision)), new MyNumber(new BigDecimal(7.40,precision)));
		e = new Minus(params5, Notation.INFIX);
		c.print(e);
		c.eval(e);


		List<Expression> params11 = new ArrayList<>();
		Collections.addAll(params11, new MyNumber(new BigDecimal(8,precision)), new MyNumber(new BigDecimal(6,precision),4));
		e = new Plus(params11, Notation.INFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params10 = new ArrayList<>();
		Collections.addAll(params10, new MyNumber(new BigDecimal(8,precision)), new MyNumber(new BigDecimal(6,precision),4));
		e = new Minus(params10, Notation.INFIX);
		c.print(e);
		c.eval(e);


		List<Expression> params12 = new ArrayList<>();
		Collections.addAll(params12, new MyNumber(new BigDecimal(8,precision)), new MyNumber(new BigDecimal(6,precision),4));
		e = new Times(params12, Notation.INFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params13 = new ArrayList<>();
		Collections.addAll(params13, new MyNumber(new BigDecimal(8,precision)), new MyNumber(new BigDecimal(4,precision),4));
		e = new Divides(params13, Notation.INFIX);
		c.print(e);
		c.eval(e);

		//log.clearMemory();
		log.display();
		log.saveLog();

	}
	catch(IllegalConstruction exception) {
		System.out.println("cannot create operations without parameters");
		}
 	}
}
