package junit5tests;

import calculator.MyNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBasesNumber
{
    @Test
    void test_binary_1()
    {
        MyNumber abinary = new MyNumber( "101", 2);
        assertEquals(abinary.getBase(), 2);
        assertEquals(abinary.getInteger(), 5);
    }
}
