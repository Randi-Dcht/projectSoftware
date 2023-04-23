package junit5tests;

import calculator.*;
import calculator.arithmetics.Pgcd;
import calculator.arithmetics.Ppcm;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPpcm
{
    @Test
    void testConstructor1()
    {
        assertThrows(IllegalConstruction.class, () -> new Ppcm(null));
    }

    @Test
    void testConstructor2()
    {
        try {
            assertTrue(new Ppcm(Arrays.asList(new MyNumber(1)), Notation.INFIX) instanceof Ppcm);
        }catch (IllegalConstruction e){fail();}
    }

    @Test
    void testCalculatePpcm() throws IllegalConstruction
    {
        Ppcm op = new Ppcm(new ArrayList<Expression>());
        assertEquals(24, op.op(new MyNumber(8), new MyNumber(6)).getInteger());
        assertEquals(200, op.op(new MyNumber(100), new MyNumber(40)).getInteger());
        assertEquals(0, op.op(new MyNumber(0), new MyNumber(0)).getInteger());
        assertEquals(1, op.op(new MyNumber(1), new MyNumber(1)).getInteger());
        assertEquals(91, op.op(new MyNumber(13), new MyNumber(7)).getInteger());
        assertEquals(0, op.op(new MyNumber(0), new MyNumber(1)).getInteger());
        assertEquals(0, op.op(new MyNumber("1i25"), new MyNumber("0i2")).getInteger());
    }
}
