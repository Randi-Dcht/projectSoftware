package junit5tests;

import calculator.MyNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBasesNumber
{
    @Test
    void test_binary_2()
    {
        MyNumber abinary = new MyNumber( "101", 2);
        assertEquals(abinary.getBase(), 2);
        assertEquals(abinary.getInteger(), 5);
    }


    @Test
    void test_binary_16()
    {
        MyNumber abinary = new MyNumber( "A1", 16);
        assertEquals(abinary.getBase(), 16);
        assertEquals(abinary.getInteger(), 161);
    }

    @Test
    void test_binary_8()
    {
        MyNumber abinary = new MyNumber( "16", 8);
        assertEquals(abinary.getBase(), 8);
        assertEquals(abinary.getInteger(), 14);
    }
}
