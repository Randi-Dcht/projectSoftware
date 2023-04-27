package junit5tests;

import calculator.NumberNotation;
import cli.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCLi
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream  inContent  = new ByteArrayInputStream("1 + 2".getBytes());
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn  = System.in;

    @Before
    void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void out_Main()
    {
        Main.printHelp();
        //assertEquals("hello", outContent.toString());

        Main.printMenu();
        //assertEquals("hello", outContent.toString());
    }

    void in_Main()
    {
        System.setIn(inContent);
        Main.get_input();
        //assertEquals("hello", outContent.toString());
    }

    @After
    void restoreStreams()
    {
        System.setOut(originalOut);
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
    void test_decimal()
    {
        assertEquals(Main.getDecimalNumber(), 15);
    }

}
