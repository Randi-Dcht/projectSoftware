package junit5tests;

import enums.ListOperator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestListOperator
{
    @Test
    void testListOperator()
    {
        ListOperator op = ListOperator.ADD;
        assertEquals(op.getValue(), "+");
        assertEquals(op.getPriority(), 1);
        assertEquals(op.toString(), "ADD");
    }

    @Test
    void testListOperator2()
    {
        ListOperator op = ListOperator.SUB;
        assertEquals(op.getValue(), "-");
        assertEquals(op.getPriority(), 1);
        assertEquals(op.toString(), "SUB");
    }

    @Test
    void testListOperator3()
    {
        ListOperator op = ListOperator.MUL;
        assertEquals(op.getValue(), "*");
        assertEquals(op.getPriority(), 2);
        assertEquals(op.toString(), "MUL");
    }

    @Test
    void testListOperator4()
    {
        ListOperator op = ListOperator.DIV;
        assertEquals(op.getValue(), "/");
        assertEquals(op.getPriority(), 2);
        assertEquals(op.toString(), "DIV");
    }
}
