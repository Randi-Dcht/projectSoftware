package junit5tests;



import calculator.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestCalculator
{
    @Test
    void testConstructor1()
    {
        // test calculator constructor
        assertTrue(new Calculator() instanceof Calculator);
    }

    @Test
    void testExpression() throws IllegalConstruction
    {
        List<Expression> l = List.of(new MyNumber(1), new MyNumber(2));
        MyNumber m = new Calculator().eval(new Plus(l, Notation.PREFIX));
        assertEquals(3, m.getInteger());
    }
}
