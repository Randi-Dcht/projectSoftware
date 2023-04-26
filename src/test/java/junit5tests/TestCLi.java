package junit5tests;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class TestCLi
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void out()
    {
        //System.out.print("hello");
        //assertEquals("hello", outContent.toString());
    }

    @After
    public void restoreStreams()
    {
        System.setOut(originalOut);
    }
}
