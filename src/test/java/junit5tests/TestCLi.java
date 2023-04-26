package junit5tests;

import cli.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class TestCLi
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream  inContent  = new ByteArrayInputStream("1 + 2".getBytes());
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn  = System.in;

    @Before
    public void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void out_Main()
    {
        Main.printHelp();
        //assertEquals("hello", outContent.toString());

        Main.printMenu();
        //assertEquals("hello", outContent.toString());
    }

    public void in_Main()
    {
        System.setIn(inContent);
        Main.get_input();
        //assertEquals("hello", outContent.toString());
    }

    @After
    public void restoreStreams()
    {
        System.setOut(originalOut);
    }
}
