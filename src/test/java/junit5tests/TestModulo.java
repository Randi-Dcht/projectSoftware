package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Notation;
import calculator.arithmetics.Modulo;
import calculator.arithmetics.Ppcm;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.Module;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestModulo
{

    @Test
    void testConstructor1()
    {
        assertThrows(IllegalConstruction.class, () -> new Modulo(null));
    }

    @Test
    void testConstructor2()
    {
        try {
            assertTrue(new Modulo(List.of(new MyNumber(1)), Notation.INFIX) instanceof Modulo);
        }catch (IllegalConstruction e){fail();}
    }

    @Test
    void testCalculatePpcm() throws IllegalConstruction
    {
        Modulo op = new Modulo(new ArrayList<Expression>());
        assertEquals(0, op.op(new MyNumber(1), new MyNumber(1)).getInteger());
        assertEquals(6, op.op(new MyNumber(13), new MyNumber(7)).getInteger());
        assertEquals(2, op.op(new MyNumber(8), new MyNumber(6)).getInteger());
        assertEquals(20, op.op(new MyNumber(100), new MyNumber(40)).getInteger());
        assertEquals(-1, op.op(new MyNumber(0), new MyNumber(0)).getInteger());
        assertEquals(-1, op.op(new MyNumber(50), new MyNumber(0)).getInteger());
    }
}
