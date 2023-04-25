package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Notation;
import calculator.arithmetics.Modulo;
import calculator.arithmetics.Pow;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPow
{

    @Test
    void testConstructor1()
    {
        assertThrows(IllegalConstruction.class, () -> new Pow(null));
        assertThrows(IllegalConstruction.class, () -> new Pow(null, Notation.INFIX));
    }

    @Test
    void testConstructor2()
    {
        try {
            assertTrue(new Pow(List.of(new MyNumber(1)), Notation.INFIX) instanceof Pow);
        }catch (IllegalConstruction e){fail();}
    }

    @Test
    void testCalculatePow() throws IllegalConstruction
    {
        Pow op = new Pow(new ArrayList<Expression>());
        assertEquals(1, op.op(new MyNumber(1), new MyNumber(2)).getInteger());
        assertEquals(8, op.op(new MyNumber(2), new MyNumber(3)).getInteger());
        assertEquals(-1, op.op(new MyNumber(0), new MyNumber(0)).getInteger());
        assertEquals(0, op.op(new MyNumber(0), new MyNumber(1)).getInteger());
    }
}
