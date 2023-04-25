package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Notation;
import calculator.arithmetics.Combinatorial;
import calculator.arithmetics.Ppcm;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCombinatorial
{
    @Test
    void testConstructor1()
    {
        assertThrows(IllegalConstruction.class, () -> new Combinatorial(null));
    }

    @Test
    void testConstructor2()
    {
        try {
            assertTrue(new Combinatorial(Arrays.asList(new MyNumber(1)), Notation.INFIX) instanceof Combinatorial);
        }catch (IllegalConstruction e){fail();}
    }

    @Test
    void testCalculateCombi() throws IllegalConstruction
    {
        Combinatorial op = new Combinatorial(new ArrayList<Expression>());
        assertEquals(10, op.op(new MyNumber(5), new MyNumber(2)).getInteger());
        assertEquals(10, op.op(new MyNumber(5), new MyNumber(3)).getInteger());
    }
}
