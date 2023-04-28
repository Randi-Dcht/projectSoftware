package junit5tests;

import cli.Main;
import enums.ListOperator;
import enums.TypeString;
import org.junit.jupiter.api.Test;
import regex.StringRegex;
import regex.Typos;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class TestRegex
{
    @Test
    void testRegrex()
    {
        String s = "1 + 2";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(3, list.size());
        assertEquals("1",list.get(0).getValue(), "list contains 1");
        assertEquals("+",list.get(1).getValue(), "list contains +");
        assertEquals("2",list.get(2).getValue(), "list contains 2");
    }

    @Test
    void integerVsReal()
    {
        String s = "1.0 2 2.8 5 8";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(5, list.size());
        assertEquals(TypeString.REAL, list.get(0).getType());
        assertEquals(TypeString.INTEGER, list.get(1).getType());
        assertEquals(TypeString.REAL, list.get(2).getType());
        assertEquals(TypeString.INTEGER, list.get(3).getType());
        assertEquals(TypeString.INTEGER, list.get(4).getType());
    }

    @Test
    void testTypos()
    {
        Typos t = new Typos("1", TypeString.INTEGER);
        assertEquals("1",t.getValue());
        assertEquals(TypeString.INTEGER, t.getType());

        t = new Typos("-", TypeString.OPERATOR);
        assertEquals("-", t.getValue());
        assertEquals(TypeString.OPERATOR, t.getType());

        t = new Typos("(", TypeString.BRACKET);
        assertEquals("(", t.getValue());
        assertEquals(TypeString.BRACKET, t.getType());
    }


    @Test
    void testBoolean()
    {
        String s = "true 1";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(2, list.size());
        assertEquals(TypeString.BOOLEAN, list.get(0).getType());
        assertEquals(TypeString.INTEGER, list.get(1).getType());
    }

    @Test
    void testScientific_ENotation()
    {
        String s = "1x10^2 1E2";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(2, list.size());
        assertEquals(TypeString.SCIENTIFIC, list.get(0).getType());
        assertEquals(TypeString.E_NOTATION, list.get(1).getType());
    }

    @Test
    void test_Regrex()
    {
        String s = "( 1x10^2 1E2 )";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(4, list.size());
        assertEquals(TypeString.BRACKET, list.get(0).getType());
        assertEquals(TypeString.BRACKET, list.get(3).getType());
        assertEquals("1x10^2", list.get(1).getValue());
        assertEquals("1E2", list.get(2).getValue());
    }

    @Test
    void test_operator()
    {
        String s = "+ - * /";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(4, list.size());
        assertEquals(ListOperator.ADD, list.get(0).getOperator());
        assertEquals(ListOperator.SUB, list.get(1).getOperator());
        assertEquals(ListOperator.MUL, list.get(2).getOperator());
        assertEquals(ListOperator.DIV, list.get(3).getOperator());
    }


    @Test
    void test_operator_2()
    {
        String s = "+ - * /";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(4, list.size());
        assertEquals(1, list.get(0).getPriority());
        assertEquals(1, list.get(1).getPriority());
        assertEquals(2, list.get(2).getPriority());
        assertEquals(2, list.get(3).getPriority());
    }

    @Test
    void test_binary()
    {
        Main.setMode("binary");


        String s = "2x1 2x0 2x1 2x0101";
        List<Typos> list = StringRegex.analyse(s);
        assertEquals(4, list.size());
        assertEquals(TypeString.BINARY, list.get(0).getType());


        s = "4x402";
        list = StringRegex.analyse(s);
        assertEquals(TypeString.BINARY, list.get(0).getType());


        s = "16x1F";
        list = StringRegex.analyse(s);
        assertEquals(TypeString.BINARY, list.get(0).getType());


        Main.setMode("cartesian");
    }
}
