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
        assertEquals(2, abinary.getBase());
        assertEquals(5, abinary.getInteger());
    }


    @Test
    void test_binary_16()
    {
        MyNumber abinary = new MyNumber( "A1", 16);
        assertEquals(16, abinary.getBase());
        assertEquals(161, abinary.getInteger());
    }

    @Test
    void test_binary_8()
    {
        MyNumber abinary = new MyNumber( "16", 8);
        assertEquals(8, abinary.getBase());
        assertEquals(14, abinary.getInteger());
    }
}
