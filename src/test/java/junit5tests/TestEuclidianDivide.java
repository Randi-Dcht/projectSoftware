package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Notation;
import calculator.arithmetics.EuclidianDivides;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestEuclidianDivide
{
    @Test
    void testConstructor1()
    {
        assertThrows(IllegalConstruction.class, () -> new EuclidianDivides(null));
    }

    @Test
    void testConstructor2()
    {
        try {
            assertTrue(new EuclidianDivides(List.of(new MyNumber(1)), Notation.INFIX) instanceof EuclidianDivides);
        }catch (IllegalConstruction e){fail();}
    }

    @Test
    void testCalculateEuclidianDivide1() throws IllegalConstruction
    {
        EuclidianDivides op = new EuclidianDivides(new ArrayList<Expression>());
        assertArrayEquals(new int[]{3, 2}, op.euclidianDivision(17, 5));
        assertArrayEquals(new int[]{4, 4}, op.euclidianDivision(28, 6));
        assertArrayEquals(new int[]{-1, -1}, op.euclidianDivision(0, 0));
        assertArrayEquals(new int[]{1, 0}, op.euclidianDivision(1, 1));
        assertArrayEquals(new int[]{-1, -1}, op.euclidianDivision(40, 0));
    }


    @Test
    void testCalculateEuclidianDivide2() throws IllegalConstruction
    {
        EuclidianDivides op = new EuclidianDivides(new ArrayList<Expression>());
       assertEquals(2, op.op(new MyNumber(17), new MyNumber(5)).getInteger());
       assertEquals(-1, op.op(new MyNumber(17), new MyNumber(0)).getInteger());
    }
}
