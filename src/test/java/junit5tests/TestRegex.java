package junit5tests;

import cli.Main;
import enums.ListOperator;
import enums.TypeString;
import org.junit.jupiter.api.Test;
import parser.StringRegex;
import parser.Typos;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class TestRegex
{
    @Test
    void testRegrex()
    {
        String s = "1 + 2";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(list.size(), 3);
        assertEquals(list.get(0).getValue(), "1", "list contains 1");
        assertEquals(list.get(1).getValue(), "+", "list contains +");
        assertEquals(list.get(2).getValue(), "2", "list contains 2");
    }

    @Test
    void integerVsReal()
    {
        String s = "1.0 2 2.8 5 8";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(list.size(), 5);
        assertEquals(list.get(0).getType(), TypeString.REAL);
        assertEquals(list.get(1).getType(), TypeString.INTEGER);
        assertEquals(list.get(2).getType(), TypeString.REAL);
        assertEquals(list.get(3).getType(), TypeString.INTEGER);
        assertEquals(list.get(4).getType(), TypeString.INTEGER);
    }

    @Test
    void testTypos()
    {
        Typos t = new Typos("1", TypeString.INTEGER);
        assertEquals(t.getValue(), "1");
        assertEquals(t.getType(), TypeString.INTEGER);

        t = new Typos("-", TypeString.OPERATOR);
        assertEquals(t.getValue(), "-");
        assertEquals(t.getType(), TypeString.OPERATOR);

        t = new Typos("(", TypeString.BRACKET);
        assertEquals(t.getValue(), "(");
        assertEquals(t.getType(), TypeString.BRACKET);
    }


    @Test
    void testBoolean()
    {
        String s = "true 1";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getType(), TypeString.BOOLEAN);
        assertEquals(list.get(1).getType(), TypeString.INTEGER);
    }

    @Test
    void testScientific_ENotation()
    {
        String s = "1x10^2 1E2";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getType(), TypeString.SCIENTIFIC);
        assertEquals(list.get(1).getType(), TypeString.E_NOTATION);
    }

    @Test
    void test_Regrex()
    {
        String s = "( 1x10^2 1E2 )";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(list.size(), 4);
        assertEquals(list.get(0).getType(), TypeString.BRACKET);
        assertEquals(list.get(3).getType(), TypeString.BRACKET);
        assertEquals(list.get(1).getValue(), "1x10^2");
        assertEquals(list.get(2).getValue(), "1E2");
    }

    @Test
    void test_operator()
    {
        String s = "+ - * /";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(list.size(), 4);
        assertEquals(list.get(0).getOperator(), ListOperator.ADD);
        assertEquals(list.get(1).getOperator(), ListOperator.SUB);
        assertEquals(list.get(2).getOperator(), ListOperator.MUL);
        assertEquals(list.get(3).getOperator(), ListOperator.DIV);
    }


    @Test
    void test_operator_2()
    {
        String s = "+ - * /";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(list.size(), 4);
        assertEquals(list.get(0).getPriority(), 1);
        assertEquals(list.get(1).getPriority(), 1);
        assertEquals(list.get(2).getPriority(), 2);
        assertEquals(list.get(3).getPriority(), 2);
    }

    @Test
    void test_binary()
    {
        Main.setMode("binary");


        String s = "2x1 2x0 2x1 2x0101";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(list.size(), 4);
        assertEquals(list.get(0).getType(), TypeString.BINARY);


        s = "4x402";
        list = StringRegex.analyse(s);
        assertEquals(list.get(0).getType(), TypeString.BINARY);


        s = "16x1F";
        list = StringRegex.analyse(s);
        assertEquals(list.get(0).getType(), TypeString.BINARY);


        Main.setMode("cartesian");
    }
}
