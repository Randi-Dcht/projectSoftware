package junit5tests;


import calculator.*;
import calculator.arithmetics.*;
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
    void testGetMode()
    {
        String input = "e_notation";
        assertEquals(InputUser.getMode(input), NumberNotation.E_NOTATION);
        String input2 = "scientific";
        assertEquals(InputUser.getMode(input2), NumberNotation.SCIENTIFIC);
        String input3 = "polar";
        assertEquals(InputUser.getMode(input3), NumberNotation.POLAR);
        String input4 = "exponential";
        assertEquals(InputUser.getMode(input4), NumberNotation.EXPONENTIAL);
        String input5 = "cartesian";
        assertEquals(InputUser.getMode(input5), NumberNotation.CARTESIAN);
    }

    @Test
    void testGetNumber()
    {
        String input = "0";
        assertEquals(InputUser.getNumber(input), 0);
        String input2 = "-1";
        assertEquals(InputUser.getNumber(input2), 15);
        String input5 = "5";
        assertEquals(InputUser.getNumber(input5), 5);
        String input3 = "a";
        assertEquals(InputUser.getNumber(input3), 15);
        String input6 = "8";
        assertEquals(InputUser.getNumber(input6), 8);
        String input4 = "";
        assertEquals(InputUser.getNumber(input4), 15);

    }

    @Test
    void testGetOperator() throws IllegalConstruction {
        ArrayList<Expression> lst = new ArrayList<>();
        lst.add(new MyNumber(new BigDecimal(1))); lst.add(new MyNumber(new BigDecimal(2)));
        assertEquals(InputUser.getOperator(new Typos("+", TypeString.OPERATOR, ListOperator.ADD), lst, Notation.INFIX), new Plus(lst));
        assertEquals(InputUser.getOperator(new Typos("-", TypeString.OPERATOR, ListOperator.SUB), lst, Notation.INFIX), new Minus(lst));
        assertEquals(InputUser.getOperator(new Typos("/", TypeString.OPERATOR, ListOperator.DIV), lst, Notation.INFIX), new Divides(lst));
        assertEquals(InputUser.getOperator(new Typos("*", TypeString.OPERATOR, ListOperator.MUL), lst, Notation.INFIX), new Times(lst));
        assertEquals(InputUser.getOperator(new Typos("comb", TypeString.OPERATOR, ListOperator.COMB), lst, Notation.INFIX), new Combinatorial(lst));
        assertEquals(InputUser.getOperator(new Typos("gcd", TypeString.OPERATOR, ListOperator.GCD), lst, Notation.INFIX), new Eucledian(lst));
        assertEquals(InputUser.getOperator(new Typos("//", TypeString.OPERATOR, ListOperator.EUCLIDEAN), lst, Notation.INFIX), new EuclidianDivides(lst));
        assertEquals(InputUser.getOperator(new Typos("!", TypeString.OPERATOR, ListOperator.FACTO), lst, Notation.INFIX), new Facto(lst));
        assertEquals(InputUser.getOperator(new Typos("%", TypeString.OPERATOR, ListOperator.MODULO), lst, Notation.INFIX), new Modulo(lst));
        assertEquals(InputUser.getOperator(new Typos("pgcd", TypeString.OPERATOR, ListOperator.PGCD), lst, Notation.INFIX), new Pgcd(lst));
        assertEquals(InputUser.getOperator(new Typos("ppcm", TypeString.OPERATOR, ListOperator.PPCM), lst, Notation.INFIX), new Ppcm(lst));
        assertEquals(InputUser.getOperator(new Typos("^", TypeString.OPERATOR, ListOperator.POW), lst, Notation.INFIX), new Pow(lst));
        assertEquals(InputUser.getOperator(new Typos("prime", TypeString.OPERATOR, ListOperator.PRIME), lst, Notation.INFIX), new PrimeNumber(lst));
        assertEquals(InputUser.getOperator(new Typos("modulus", TypeString.OPERATOR, ListOperator.MODULUS), lst, Notation.INFIX), new Modulus(lst));
        assertEquals(InputUser.getOperator(new Typos("sqrt", TypeString.OPERATOR, ListOperator.SQRT), lst, Notation.INFIX), new Sqrt(lst));
    }

    @Test
    void testInstance()
    {
        InputUser inputUser = new InputUser(Notation.PREFIX,NumberNotation.CARTESIAN,15);
        assertTrue(inputUser instanceof InputUser);
    }

    @Test
    void testInstance2()
    {
        InputUser inputUser = new InputUser(Notation.INFIX,NumberNotation.CARTESIAN,15);
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
        InputUser inputUser = new InputUser(Notation.INFIX,NumberNotation.CARTESIAN,15);

        assertEquals(inputUser.getMode(),NumberNotation.CARTESIAN);

        inputUser.setUserInput(StringRegex.analyse("1 + 2"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(3)));

        inputUser.setUserInput(StringRegex.analyse("1.3 + 2.5i"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal("1.3"),new BigDecimal("2.5")));

        inputUser.setUserInput(StringRegex.analyse("2 * 3 + 2 * 3"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(12)));

        inputUser.setUserInput(StringRegex.analyse("( 2 + 2 ) * 3"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(12)));

        inputUser.setUserInput(StringRegex.analyse("( ( 2 + 2 ) * 3 )"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(12)));

        inputUser.setUserInput(StringRegex.analyse("( ( 2 + 2i ) * 3 )"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(6),new BigDecimal(6)));

        inputUser.setUserInput(StringRegex.analyse("( ( 2i - -2i ) * 3 )"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(0),new BigDecimal(12)));

        inputUser.setUserInput(StringRegex.analyse("( ( 2x10^2 + 2x10^2i ) * 3 )"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(6),2,new BigDecimal(6),2));

        inputUser.setUserInput(StringRegex.analyse("( ( 5E4 + 9E1i ) * 4 )"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(20),4,new BigDecimal(36),1));

        inputUser.setUserInput(StringRegex.analyse("sqrt 16"));
        assertEquals(inputUser.compute(false), new MyNumber(new BigDecimal(4)));

        inputUser.setMode(NumberNotation.SCIENTIFIC);
        assertEquals(inputUser.getMode(),NumberNotation.SCIENTIFIC);

        inputUser.setMode(NumberNotation.CARTESIAN);
        assertEquals(inputUser.getMode(),NumberNotation.CARTESIAN);

        inputUser.setMode(NumberNotation.E_NOTATION);
        assertEquals(inputUser.getMode(),NumberNotation.E_NOTATION);

        inputUser.setMode(NumberNotation.EXPONENTIAL);
        assertEquals(inputUser.getMode(),NumberNotation.EXPONENTIAL);

        inputUser.setMode(NumberNotation.POLAR);
        assertEquals(inputUser.getMode(),NumberNotation.POLAR);

        inputUser.setDecimalNumber(0);
        assertEquals(inputUser.getDecimalNumber(),0);

        inputUser.setDecimalNumber(16);
        assertEquals(inputUser.getDecimalNumber(),16);

    }
}
