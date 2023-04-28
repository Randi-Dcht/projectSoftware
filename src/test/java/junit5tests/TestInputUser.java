package junit5tests;


import calculator.*;
import calculator.arithmetics.*;
import cli.InputUser;
import cli.Main;
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
            assertNotEquals(" ", ss);
    }

    @Test
    void testGetNotation()
    {
        String input = "POSTFIX";
        assertEquals(Notation.POSTFIX, InputUser.getNotation(input));
        String input2 = "INFIX";
        assertEquals(Notation.INFIX, InputUser.getNotation(input2));
        String input3 = "PREFIX";
        assertEquals(Notation.PREFIX, InputUser.getNotation(input3));
    }

    @Test
    void testGetMode()
    {
        String input = "e_notation";
        assertEquals(NumberNotation.E_NOTATION, InputUser.getMode(input));
        String input2 = "scientific";
        assertEquals(NumberNotation.SCIENTIFIC, InputUser.getMode(input2));
        String input3 = "polar";
        assertEquals(NumberNotation.POLAR, InputUser.getMode(input3));
        String input4 = "exponential";
        assertEquals(NumberNotation.EXPONENTIAL, InputUser.getMode(input4));
        String input5 = "cartesian";
        assertEquals(NumberNotation.CARTESIAN, InputUser.getMode(input5));
    }

    @Test
    void testGetNumber()
    {
        String input = "0";
        assertEquals(0, InputUser.getNumber(input));
        String input2 = "-1";
        assertEquals(15,InputUser.getNumber(input2));
        String input5 = "5";
        assertEquals(5, InputUser.getNumber(input5));
        String input3 = "a";
        assertEquals(15, InputUser.getNumber(input3));
        String input6 = "8";
        assertEquals(8, InputUser.getNumber(input6));
        String input4 = "";
        assertEquals(15, InputUser.getNumber(input4));

    }

    @Test
    void testGetOperator() throws IllegalConstruction {
        ArrayList<Expression> lst = new ArrayList<>();
        lst.add(new MyNumber(new BigDecimal(1))); lst.add(new MyNumber(new BigDecimal(2)));
        assertEquals(new Plus(lst),InputUser.getOperator(new Typos("+", TypeString.OPERATOR, ListOperator.ADD), lst, Notation.INFIX));
        assertEquals(new Minus(lst),InputUser.getOperator(new Typos("-", TypeString.OPERATOR, ListOperator.SUB), lst, Notation.INFIX));
        assertEquals(new Divides(lst),InputUser.getOperator(new Typos("/", TypeString.OPERATOR, ListOperator.DIV), lst, Notation.INFIX));
        assertEquals(new Times(lst),InputUser.getOperator(new Typos("*", TypeString.OPERATOR, ListOperator.MUL), lst, Notation.INFIX));
        assertEquals(new Combinatorial(lst),InputUser.getOperator(new Typos("comb", TypeString.OPERATOR, ListOperator.COMB), lst, Notation.INFIX));
        assertEquals(new Eucledian(lst),InputUser.getOperator(new Typos("gcd", TypeString.OPERATOR, ListOperator.GCD), lst, Notation.INFIX));
        assertEquals(new EuclidianDivides(lst),InputUser.getOperator(new Typos("//", TypeString.OPERATOR, ListOperator.EUCLIDEAN), lst, Notation.INFIX));
        assertEquals(new Facto(lst),InputUser.getOperator(new Typos("!", TypeString.OPERATOR, ListOperator.FACTO), lst, Notation.INFIX));
        assertEquals(new Modulo(lst),InputUser.getOperator(new Typos("%", TypeString.OPERATOR, ListOperator.MODULO), lst, Notation.INFIX));
        assertEquals(new Pgcd(lst),InputUser.getOperator(new Typos("pgcd", TypeString.OPERATOR, ListOperator.PGCD), lst, Notation.INFIX));
        assertEquals(new Ppcm(lst),InputUser.getOperator(new Typos("ppcm", TypeString.OPERATOR, ListOperator.PPCM), lst, Notation.INFIX));
        assertEquals(new Pow(lst),InputUser.getOperator(new Typos("^", TypeString.OPERATOR, ListOperator.POW), lst, Notation.INFIX));
        assertEquals(new PrimeNumber(lst),InputUser.getOperator(new Typos("prime", TypeString.OPERATOR, ListOperator.PRIME), lst, Notation.INFIX));
        assertEquals(new Modulus(lst),InputUser.getOperator(new Typos("modulus", TypeString.OPERATOR, ListOperator.MODULUS), lst, Notation.INFIX));
        assertEquals(new Sqrt(lst),InputUser.getOperator(new Typos("sqrt", TypeString.OPERATOR, ListOperator.SQRT), lst, Notation.INFIX));
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
        assertEquals(3, inputUser.getUserInput().size());
        assertEquals("1", inputUser.getUserInput().get(0).getValue());
        assertEquals("+", inputUser.getUserInput().get(1).getValue());
        assertEquals("2", inputUser.getUserInput().get(2).getValue());
    }

    @Test
    void testComputeNotation()
    {
        assertEquals(Notation.POSTFIX, new InputUser(Notation.INFIX,NumberNotation.CARTESIAN,15).getNotationCompute());
        assertEquals( Notation.POSTFIX, new InputUser(Notation.POSTFIX,NumberNotation.CARTESIAN,15).getNotationCompute());
        assertEquals(Notation.PREFIX, new InputUser(Notation.PREFIX,NumberNotation.CARTESIAN,15).getNotationCompute());
    }

    @Test
    void testCompute()
    {
        InputUser inputUser = new InputUser(Notation.INFIX,NumberNotation.CARTESIAN,15);

        assertEquals(NumberNotation.CARTESIAN, inputUser.getMode());

        inputUser.setUserInput(StringRegex.analyse("1 + 2"));
        assertEquals(new MyNumber(new BigDecimal(3)), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("1.3 + 2.5i"));
        assertEquals(new MyNumber(new BigDecimal("1.3"),new BigDecimal("2.5")), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("2 * 3 + 2 * 3"));
        assertEquals(new MyNumber(new BigDecimal(12)), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("( 2 + 2 ) * 3"));
        assertEquals( new MyNumber(new BigDecimal(12)), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("( ( 2 + 2 ) * 3 )"));
        assertEquals(new MyNumber(new BigDecimal(12)), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("( ( 2 + 2i ) * 3 )"));
        assertEquals(new MyNumber(new BigDecimal(6),new BigDecimal(6)), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("( ( 2i - -2i ) * 3 )"));
        assertEquals(new MyNumber(new BigDecimal(0),new BigDecimal(12)), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("( ( 2x10^2 + 2x10^2i ) * 3 )"));
        assertEquals(new MyNumber(new BigDecimal(6),2,new BigDecimal(6),2), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("( ( 5E4 + 9E1i ) * 4 )"));
        assertEquals(new MyNumber(new BigDecimal(20),4,new BigDecimal(36),1), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("sqrt 16"));
        assertEquals(new MyNumber(new BigDecimal(4)), inputUser.compute(false));

        inputUser.setMode(NumberNotation.SCIENTIFIC);
        assertEquals(NumberNotation.SCIENTIFIC, inputUser.getMode());

        inputUser.setMode(NumberNotation.CARTESIAN);
        assertEquals(NumberNotation.CARTESIAN, inputUser.getMode());

        inputUser.setMode(NumberNotation.E_NOTATION);
        assertEquals(NumberNotation.E_NOTATION, inputUser.getMode());

        inputUser.setMode(NumberNotation.EXPONENTIAL);
        assertEquals(NumberNotation.EXPONENTIAL, inputUser.getMode());

        inputUser.setMode(NumberNotation.POLAR);
        assertEquals(NumberNotation.POLAR, inputUser.getMode());

        inputUser.setDecimalNumber(0);
        assertEquals(0, inputUser.getDecimalNumber());

        inputUser.setDecimalNumber(16);
        assertEquals(16,inputUser.getDecimalNumber());

        Main.setMode("binary");
        inputUser.setUserInput(StringRegex.analyse("2x01 + 2x10"));
        assertEquals( new MyNumber(new BigDecimal(3)), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("16xA1 + 16xF2"));
        assertEquals(new MyNumber(new BigDecimal(403)), inputUser.compute(false));

        inputUser.setUserInput(StringRegex.analyse("4x10 + 16xF2"));
        assertEquals(new MyNumber(new BigDecimal(246)), inputUser.compute(false));

        Main.setMode(NumberNotation.CARTESIAN);

    }
}
