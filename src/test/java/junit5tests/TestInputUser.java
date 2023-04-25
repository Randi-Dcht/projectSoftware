package junit5tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import calculator.*;
import cli.InputUser;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

class TestInputUser
{
    @Test
    void testCleanInput()
    {
        String input = "  1 + 2  ";
        for (String ss : InputUser.cleanInput(input))
            assertNotEquals(ss, " ");
    }

    @Test
    void testGetNotation()
    {
        String input = "POSTFIX";
        assertEquals(InputUser.getNotation(input), Notation.POSTFIX);
        String input2 = "INFIX";
        assertEquals(InputUser.getNotation(input2), Notation.INFIX);
        String input3 = "PREFIX";
        assertEquals(InputUser.getNotation(input3), Notation.PREFIX);
    }

    @Test
    void testGetOperator() throws IllegalConstruction {
        ArrayList<Expression> lst = new ArrayList<>();
        lst.add(new MyNumber(new BigDecimal(1))); lst.add(new MyNumber(new BigDecimal(2)));
        assertEquals(InputUser.getOperator("+", lst, Notation.INFIX), new Plus(lst));
        assertEquals(InputUser.getOperator("-", lst, Notation.INFIX), new Minus(lst));
        assertEquals(InputUser.getOperator("/", lst, Notation.INFIX), new Divides(lst));
        assertEquals(InputUser.getOperator("*", lst, Notation.INFIX), new Times(lst));

    }

    @Test
    void testInstance()
    {
        InputUser inputUser = new InputUser(Notation.PREFIX);
        assertTrue(inputUser instanceof InputUser);
    }
}
