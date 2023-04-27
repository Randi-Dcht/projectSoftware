package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Notation;
import calculator.arithmetics.Eucledian;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestEucledian
{

    @Test
    void testConstructor1()
    {
        assertThrows(IllegalConstruction.class, () -> new Eucledian(null));
    }

    @Test
    void testConstructor2()
    {
        try {
            assertTrue(new Eucledian(List.of(new MyNumber(1)), Notation.INFIX) instanceof Eucledian);
        }catch (IllegalConstruction e){fail();}
    }

    @Test
    void testCalculateEuclidian() throws IllegalConstruction
    {
        Eucledian op = new Eucledian(new ArrayList<Expression>());
        assertEquals(8, op.op(new MyNumber(24), new MyNumber(16)).getInteger());
        assertEquals(27, op.op(new MyNumber(81), new MyNumber(27)).getInteger());
        assertEquals(5, op.op(new MyNumber(50), new MyNumber(15)).getInteger());
        assertEquals(12, op.op(new MyNumber(144), new MyNumber(60)).getInteger());
    }
}
