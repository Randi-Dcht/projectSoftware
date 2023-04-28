package junit5tests;

import calculator.NumberNotation;
import cli.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
