package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Notation;
import calculator.arithmetics.PrimeNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestPrimeNumber
{
    @Test
    void testConstructor1()
    {
        assertThrows(IllegalConstruction.class, () -> new PrimeNumber(null));
    }

    @Test
    void testConstructor2()
    {
        try {
            assertTrue(new PrimeNumber(Arrays.asList(new MyNumber(1)), Notation.INFIX) instanceof PrimeNumber);
        }catch (IllegalConstruction e){fail();}
    }

    @Test
    void testCalculatePrimeNumber1() throws IllegalConstruction
    {
        List<Expression> params = Arrays.asList(new MyNumber(8), new MyNumber(6));
        PrimeNumber op = new PrimeNumber(params);
        assertEquals(1, op.calculate(6, 35));
        assertEquals(3, op.calculate(6, 27));
        assertEquals(5, op.calculate(5, 10));
        assertEquals(0, op.calculate(0, 0));
    }

    @Test
    void testCalculatePrimeNumber2() throws IllegalConstruction
    {
        List<Expression> params = Arrays.asList(new MyNumber(8), new MyNumber(6));
        PrimeNumber op = new PrimeNumber(params);
        assertEquals(1, op.op(new MyNumber(6), new MyNumber(35)).getInteger());
        assertEquals(0, op.op(new MyNumber(6), new MyNumber(27)).getInteger());
        assertEquals(0, op.op(new MyNumber(5), new MyNumber(10)).getInteger());
    }
}
