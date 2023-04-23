package junit5tests;

import calculator.*;
import calculator.arithmetics.Pgcd;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPgcd
{

    @Test
    void testConstructor1()
    {
        assertThrows(IllegalConstruction.class, () -> new Pgcd(null));
    }

    @Test
    void testConstructor2()
    {
        try {
            assertTrue(new Pgcd(Arrays.asList(new MyNumber(1)), Notation.INFIX) instanceof Pgcd);
        }catch (IllegalConstruction e){fail();}
    }

    @Test
    void testCalculatePgcd() throws IllegalConstruction
    {
        List<Expression> params = Arrays.asList(new MyNumber(8), new MyNumber(6));
        Pgcd op = new Pgcd(params);
        assertEquals(2, op.op(new MyNumber(8), new MyNumber(6)).getInteger());
        assertEquals(20, op.op(new MyNumber(100), new MyNumber(40)).getInteger());
        assertEquals(0, op.op(new MyNumber(0), new MyNumber(0)).getInteger());
        assertEquals(1, op.op(new MyNumber(1), new MyNumber(1)).getInteger());
        assertEquals(1, op.op(new MyNumber(13), new MyNumber(7)).getInteger());
    }


    @Test
    void testCalculatePgcd2()
    {
        assertEquals(2, Pgcd.pgcd(8, 6));
        assertEquals(20, Pgcd.pgcd(100, 40));
    }
}
