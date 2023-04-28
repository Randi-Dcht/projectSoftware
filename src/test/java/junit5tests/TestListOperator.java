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
        assertEquals("+", op.getValue());
        assertEquals(1, op.getPriority(), 1);
        assertEquals("ADD", op.toString());
    }

    @Test
    void testListOperator2()
    {
        ListOperator op = ListOperator.SUB;
        assertEquals("-", op.getValue());
        assertEquals(1, op.getPriority());
        assertEquals("SUB", op.toString());
    }

    @Test
    void testListOperator3()
    {
        ListOperator op = ListOperator.MUL;
        assertEquals("*", op.getValue());
        assertEquals(2, op.getPriority(), 2);
        assertEquals("MUL", op.toString());
    }

    @Test
    void testListOperator4()
    {
        ListOperator op = ListOperator.DIV;
        assertEquals("/", op.getValue());
        assertEquals(2, op.getPriority());
        assertEquals("DIV", op.toString());
    }
}
