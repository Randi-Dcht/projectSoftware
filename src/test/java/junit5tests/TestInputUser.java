package junit5tests;


import calculator.*;
import cli.InputUser;
import enums.ListOperator;
import enums.TypeString;
import org.junit.jupiter.api.Test;
import parser.StringRegex;
import parser.Typos;
import java.math.BigDecimal;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(InputUser.getOperator(new Typos("+", TypeString.OPERATOR, ListOperator.ADD), lst, Notation.INFIX), new Plus(lst));
        assertEquals(InputUser.getOperator(new Typos("-", TypeString.OPERATOR, ListOperator.SUB), lst, Notation.INFIX), new Minus(lst));
        assertEquals(InputUser.getOperator(new Typos("/", TypeString.OPERATOR, ListOperator.DIV), lst, Notation.INFIX), new Divides(lst));
        assertEquals(InputUser.getOperator(new Typos("*", TypeString.OPERATOR, ListOperator.MUL), lst, Notation.INFIX), new Times(lst));

    }

    @Test
    void testInstance()
    {
        InputUser inputUser = new InputUser(Notation.PREFIX);
        assertTrue(inputUser instanceof InputUser);
    }

    @Test
    void testInstance2()
    {
        InputUser inputUser = new InputUser(Notation.INFIX);
        assertSame(inputUser.getNotation(), Notation.INFIX);
        inputUser.setNotation(Notation.POSTFIX);
        assertSame(inputUser.getNotation(), Notation.POSTFIX);

        inputUser.setUserInput(StringRegex.analyse("1 + 2"));
        assertEquals(inputUser.getUserInput().size(), 3);
        assertEquals(inputUser.getUserInput().get(0).getValue(), "1");
        assertEquals(inputUser.getUserInput().get(1).getValue(), "+");
        assertEquals(inputUser.getUserInput().get(2).getValue(), "2");
    }

    @Test
    void testCompute()
    {
        InputUser inputUser = new InputUser(Notation.INFIX);
        inputUser.setUserInput(StringRegex.analyse("1 + 2"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(3)));

        inputUser.setUserInput(StringRegex.analyse("2 * 3 + 2 * 3"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(12)));

        inputUser.setUserInput(StringRegex.analyse("( 2 + 2 ) * 3"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(12)));

        inputUser.setUserInput(StringRegex.analyse("( ( 2 + 2 ) * 3 )"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(12)));
    }
}
