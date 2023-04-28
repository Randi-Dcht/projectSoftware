package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.*;

import java.math.BigDecimal;
import java.util.ArrayList;

class TestMyNumber {

	private final BigDecimal value = new BigDecimal (8);
	private final BigDecimal imaginary = new BigDecimal (3);
	private final int exp =2;
	private MyNumber number;
	private MyNumber realnumber;
	private MyNumber complexNumber;
	private  MyNumber onlyComplexNumber;
	@BeforeEach
	void setUp() {

		number = new MyNumber(value);
		realnumber = new MyNumber(value,exp);
		complexNumber = new MyNumber(value,imaginary);
		onlyComplexNumber = new MyNumber(new BigDecimal("0"),imaginary);
	}

	@Test
	void testEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(value), number);
		// Two MyNumbers containing a distinct value should not be equal:
		BigDecimal otherValue = new BigDecimal(7);
		assertNotEquals(new MyNumber(otherValue),number);
		assertEquals(number, number); // Identity check (for coverage, as this should always be true)
		assertNotEquals(number, value); // number is of type MyNumber, while value is of type int, so not equal

		assertNotEquals(null,number);
		assertEquals(new MyNumber(value,imaginary), complexNumber);
		assertNotEquals(complexNumber,number);
		assertEquals(complexNumber, complexNumber);
		assertNotEquals(null, complexNumber);

		assertEquals(new MyNumber(new BigDecimal("0"),imaginary), onlyComplexNumber);
		assertNotEquals(complexNumber,onlyComplexNumber);
		assertEquals(onlyComplexNumber, onlyComplexNumber);
		assertNotEquals(null, onlyComplexNumber);


		assertEquals(new MyNumber(value,exp),realnumber);

		assertNotEquals(realnumber,number);
		assertEquals(realnumber,realnumber);
		assertNotEquals(realnumber, value);

		try {
			assertNotEquals(new Times(new ArrayList<>()), number);
		}
		catch (IllegalConstruction e) {fail();}


	}


	@Test
	void testToString() {
		assertEquals(new MyNumber(value).toString(), number.toString());
		assertEquals(new MyNumber(value,imaginary).toString(), complexNumber.toString());
		assertEquals(new MyNumber(new BigDecimal(0),imaginary).toString(), onlyComplexNumber.toString());
		assertEquals("10", new MyNumber("1010", 2).toString());
	}

}
