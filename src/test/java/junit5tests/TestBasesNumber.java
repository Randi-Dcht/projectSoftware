package junit5tests;

import calculator.BasesNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBasesNumber
{
    @Test
    void testgetBasesNumber()
    {
        BasesNumber n = new BasesNumber(2, "101");
        assertEquals(n.getBase(), 2);
        assertEquals(n.getValuetoString(), "101");
    }


    @Test
    void testsetBasesNumber()
    {
        BasesNumber n = new BasesNumber(2, "101");
        n.setBase(10);
        assertEquals(n.getBase(), 10);
        assertEquals(n.getValuetoString(), "101");

        n.setValue("111");
        assertEquals(n.getBase(), 10);
        assertEquals(n.getValuetoString(), "111");
    }


    @Test
    void testgetBasesNumber2()
    {
        BasesNumber n = new BasesNumber(2, "11");
        assertEquals(n.getValue(), 3);
        n.setValue("111");
        assertEquals(n.getValue(), 7);
    }

    @Test
    void testgetBasesNumber16()
    {
        BasesNumber n = new BasesNumber(16, "A");
        assertEquals(n.getValue(), 10);
        n.setValue("FA");
        assertEquals(n.getValue(), 250);
    }

    @Test
    void testgetBasesNumber36()
    {
        BasesNumber n = new BasesNumber(36, "1Z");
        assertEquals(n.getValue(), 71);
    }

    @Test
    void testgetBasesNumberGetter()
    {
        BasesNumber n = new BasesNumber(36, "1Z");
        assertEquals(n.countDepth(), 0);
        assertEquals(n.countOps(), 0);
        assertEquals(n.countNbs(), 1);
    }
}
