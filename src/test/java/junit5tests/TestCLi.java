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

    @BeforeEach
    void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testPrinting()
    {
        Main.printing("test", true);
        assertEquals("test\n", outContent.toString());
        Main.printing("test", false);
        assertEquals("test\ntest", outContent.toString());
    }

    @Test
    void outMain()
    {
        Main.printHelp();
        String text = "=== HELP START===\n" +
                "$> Quit program : .quit\n" +
                "$> Verbose mode : .verbose <true|false> \n" +
                "$> Please enter an expression to evaluate or .quit to exit \n" +
                "$> To change the notation, use the command .mode <mode> where <mode> is cartesian, polar, exponential, scientific or e_notation \n" +
                "$> To change the notation, use the command .notation <notation> where <notation> is infix, prefix, postfix \n" +
                "$> To change the number of decimal, use the command .decim <number> where <number> is the number of decimal you want (15 by default) \n" +
                "$> List of operators : [  +  -  *  /  comb  gcd  //  !  %  pgcd  ^  ppcm  prime  modulus  sqrt]\n" +
                "=== HELP END===\n\n" ;
        assertEquals(text, outContent.toString());
    }

    @Test
    void testError()
    {
        Main.printError("test");
        assertEquals("!!> test\n", outContent.toString());
    }

    @Test
    void testPrintMenu()
    {
        Main.printMenu();
        String text = "$> Please enter an expression to evaluate or .quit to exit \n" +
                "$> To change the notation, use the command .mode <mode> where <mode> is cartesian, polar, exponential, scientific or e_notation \n" +
                "$> To change the notation, use the command .notation <notation> where <notation> is infix, prefix, postfix \n" +
                "$> To change the number of decimal, use the command .decim <number> where <number> is the number of decimal you want (15 by default) \n";
        assertEquals(text, outContent.toString());
    }

    @Test
    void testPrintOperator()
    {
        Main.printOperator();
        assertEquals("$> List of operators : [  +  -  *  /  comb  gcd  //  !  %  pgcd  ^  ppcm  prime  modulus  sqrt]\n", outContent.toString());
    }

    @Test
    void testInputUser()
    {
        System.setIn(new ByteArrayInputStream("1 + 2".getBytes()));
        Main.get_input();
        assertEquals("$>>> $> 3\n", outContent.toString());

        System.setIn(new ByteArrayInputStream(".mode binary".getBytes()));
        Main.get_input();
        assertEquals(Main.getMode(), NumberNotation.BINARY);

        System.setIn(new ByteArrayInputStream(".decim 5".getBytes()));
        Main.get_input();
        assertEquals(Main.getDecimalNumber(), 5);

        System.setIn(new ByteArrayInputStream(".decim 15".getBytes()));
        Main.get_input();
        assertEquals(Main.getDecimalNumber(), 15);

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
        assertEquals(Main.getMode(), NumberNotation.BINARY);

        Main.setMode("cartesian");
        assertEquals(Main.getMode(), NumberNotation.CARTESIAN);

        Main.setMode("exponential");
        assertEquals(Main.getMode(), NumberNotation.EXPONENTIAL);

        Main.setMode("scientific");
        assertEquals(Main.getMode(), NumberNotation.SCIENTIFIC);

        Main.setMode("e_notation");
        assertEquals(Main.getMode(), NumberNotation.E_NOTATION);

        Main.setMode("polar");
        assertEquals(Main.getMode(), NumberNotation.POLAR);

    }

    @Test
    void testDecimal()
    {
        assertEquals(Main.getDecimalNumber(), 15);
    }

}
