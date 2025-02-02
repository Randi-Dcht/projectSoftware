package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCounting {

    private BigDecimal value1, value2;
    private Expression e;

    @BeforeEach
    void setUp() {
        value1 = new BigDecimal(8);
        value2 = new BigDecimal(6);
        e = null;
    }

    @Test
    void testNumberCounting() {
        e = new MyNumber(value1);
        //test whether a number has zero depth (i.e. no nested expressions)
        assertEquals( Integer.valueOf(0), e.countDepth());
        //test whether a number contains zero operations
        assertEquals(Integer.valueOf(0), e.countOps());
        //test whether a number contains 1 number
        assertEquals(Integer.valueOf(1), e.countNbs());
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testOperationCounting(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
        //Operation op = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	e = new Plus(params);
                case "-"	->	e = new Minus(params);
                case "*"	->	e = new Times(params);
                case "/"	->	e = new Divides(params);
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
        //test whether a binary operation has depth 1
        assertEquals(Integer.valueOf(1), e.countDepth(),"counting depth of an Operation");
        //test whether a binary operation contains 1 operation
        assertEquals(Integer.valueOf(1), e.countOps());
        //test whether a binary operation contains 2 numbers
        assertEquals(Integer.valueOf(2), e.countNbs());
    }

}
