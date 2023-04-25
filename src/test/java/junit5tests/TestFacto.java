package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Notation;
import calculator.arithmetics.Facto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

class TestFacto
{
    @Test
    void testConstructor1()
    {
        assertThrows(IllegalConstruction.class, () -> new Facto(null));
    }

    @Test
    void testConstructor2()
    {
        try {
            assertTrue(new Facto(Arrays.asList(new MyNumber(1)), Notation.INFIX) instanceof Facto);
        }catch (IllegalConstruction e){fail();}
    }

    @Test
    void testCalculateFacto() throws IllegalConstruction
    {
        Facto op = new Facto(new ArrayList<Expression>());
        assertEquals(120, op.op(new MyNumber(5), new MyNumber(0)).getInteger());
        assertEquals(1, op.op(new MyNumber(1), new MyNumber(0)).getInteger());
        assertEquals(1, op.op(new MyNumber(0), new MyNumber(0)).getInteger());

        assertEquals(120, op.op(5).getInteger());
        assertEquals(1, op.op(1).getInteger());
        assertEquals(1, op.op(0).getInteger());
    }
}
