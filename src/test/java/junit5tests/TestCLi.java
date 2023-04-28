package junit5tests;

import calculator.*;
import cli.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import regex.StringRegex;
import regex.Typos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static cli.ConvertNotation.transformNotation;
import static org.junit.jupiter.api.Assertions.*;

class TestCLi
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn  = System.in;

    private String symbol = "\n";

    @BeforeEach
    void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));

        if (System.getProperty("os.name").contains("Windows"))
            symbol = "\r\n";
        else if (System.getProperty("os.name").contains("Linux"))
            symbol = "\n";
        else if (System.getProperty("os.name").contains("Mac"))
            symbol = "\r";
    }

    @Test
    void testPrinting()
    {
        Main.printing("test", true);
        assertEquals("test"+symbol, outContent.toString());
        Main.printing("test", false);
        assertEquals("test" + symbol + "test", outContent.toString());
    }

    @Test
    void outMain()
    {
        Main.printHelp();
        String text = "=== HELP START===" + symbol +
                "$> Quit program : .quit" + symbol +
                "$> Verbose mode : .verbose <true|false>" +  symbol +
                "$> Please enter an expression to evaluate or .quit to exit" + symbol +
                "$> To change the notation, use the command .mode <mode> where <mode> is cartesian, polar, exponential, scientific or e_notation" + symbol +
                "$> To change the notation, use the command .notation <notation> where <notation> is infix, prefix, postfix" + symbol +
                "$> To change the number of decimal, use the command .decim <number> where <number> is the number of decimal you want (15 by default)" + symbol +
                "$> List of operators : [  +  -  *  /  comb  gcd  //  !  %  pgcd  ^  ppcm  prime  modulus  sqrt]" + symbol +
                "=== HELP END===" + symbol ;
        assertEquals(text, outContent.toString());
    }

    @Test
    void testError()
    {
        Main.printError("test");
        assertEquals("!!> test" + symbol, outContent.toString());
    }

    @Test
    void testPrintMenu()
    {
        Main.printMenu();
        String text = "$> Please enter an expression to evaluate or .quit to exit" + symbol +
                "$> To change the notation, use the command .mode <mode> where <mode> is cartesian, polar, exponential, scientific or e_notation" + symbol +
                "$> To change the notation, use the command .notation <notation> where <notation> is infix, prefix, postfix" + symbol +
                "$> To change the number of decimal, use the command .decim <number> where <number> is the number of decimal you want (15 by default)"+ symbol;
        assertEquals(text, outContent.toString());
    }

    @Test
    void testPrintOperator()
    {
        Main.printOperator();
        assertEquals("$> List of operators : [  +  -  *  /  comb  gcd  //  !  %  pgcd  ^  ppcm  prime  modulus  sqrt]" +symbol, outContent.toString());
    }

    @Test
    void testPrintTransform()
    {
        String s = "2 2 +";
        List<Typos> list = StringRegex.analyse(s);
        List<Typos> listing = transformNotation(Notation.INFIX, list, true);
        String txt = "2 2 + " + symbol;
        assertEquals(txt, outContent.toString());
    }

    @Test
    void testPrintCalculator() throws IllegalConstruction
    {
        List<Expression> l = List.of(new MyNumber(1), new MyNumber(2));

        new Calculator().print(new Plus(l, Notation.PREFIX));
        String e1 = "The result of evaluating expression + (1, 2)" + symbol + "is: 3.\n" + symbol;
        assertEquals(e1, outContent.toString());

        new Calculator().printExpressionDetails(new Plus(l, Notation.PREFIX));
        e1 += e1 + "It contains 1 levels of nested expressions, 1 operations and 2 numbers.\n" + symbol;
        assertEquals(e1, outContent.toString());

    }

    @Test
    void testInputUser()
    {
        System.setIn(new ByteArrayInputStream("1 + 2".getBytes()));
        Main.get_input();
        assertEquals("$>>> $> 3" +symbol, outContent.toString());

        System.setIn(new ByteArrayInputStream(".mode binary".getBytes()));
        Main.get_input();
        assertEquals(NumberNotation.BINARY, Main.getMode());

        System.setIn(new ByteArrayInputStream(".decim 5".getBytes()));
        Main.get_input();
        assertEquals(5, Main.getDecimalNumber());

        System.setIn(new ByteArrayInputStream(".decim 15".getBytes()));
        Main.get_input();
        assertEquals(15, Main.getDecimalNumber());

        Main.setMode("cartesian");

        assertTrue(Main.isRunning());
        System.setIn(new ByteArrayInputStream(".quit".getBytes()));
        Main.get_input();
        assertFalse(Main.isRunning());

        System.setIn(new ByteArrayInputStream(".verbose true".getBytes()));
        Main.get_input();
        assertTrue(Main.isVerbose());

        System.setIn(new ByteArrayInputStream(".verbose false".getBytes()));
        Main.get_input();
        assertFalse(Main.isVerbose());

        System.setIn(new ByteArrayInputStream(".notation infix".getBytes()));
        Main.get_input();
        assertSame(Notation.INFIX, Main.getNotation());

        System.setIn(new ByteArrayInputStream(".notation prefix".getBytes()));
        Main.get_input();
        assertSame(Notation.PREFIX, Main.getNotation());

        System.setIn(new ByteArrayInputStream(".notation postfix".getBytes()));
        Main.get_input();
        assertSame(Notation.POSTFIX, Main.getNotation());
    }

    @Test
    void testErrorInput()
    {
        System.setIn(new ByteArrayInputStream(" ".getBytes()));
        Main.get_input();
        assertEquals("$>>> $> Please enter a valid expression !" + symbol, outContent.toString());
    }

    @Test
    void testMain()
    {
        String[] args = new String[0];
        System.setIn(new ByteArrayInputStream(".quit".getBytes()));
        Main.main(args);
        assertTrue(true); //check if close loop
    }

    @AfterEach
    void restoreStreams()
    {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void test_mode()
    {
        Main.setMode("binary");
        assertEquals(NumberNotation.BINARY, Main.getMode());

        Main.setMode("cartesian");
        assertEquals(NumberNotation.CARTESIAN, Main.getMode());

        Main.setMode("exponential");
        assertEquals(NumberNotation.EXPONENTIAL, Main.getMode());

        Main.setMode("scientific");
        assertEquals(NumberNotation.SCIENTIFIC, Main.getMode());

        Main.setMode("e_notation");
        assertEquals(NumberNotation.E_NOTATION, Main.getMode());

        Main.setMode("polar");
        assertEquals(NumberNotation.POLAR, Main.getMode());

    }

    @Test
    void testDecimal()
    {
        assertEquals(15, Main.getDecimalNumber());
    }

}
