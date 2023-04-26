package junit5tests;

import calculator.Notation;
import org.junit.jupiter.api.Test;
import parser.StringRegrex;
import parser.Typos;
import java.util.List;
import static cli.ConvertNotation.transformNotation;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestConvertNotation
{
    @Test
    void testConvertNotation_1_infix()
    {
        String s = "2 * 2 + 2";
        List<Typos> list = StringRegrex.analyse(s);
        List<Typos> listing = transformNotation(Notation.INFIX, list, false);

        assertEquals(listing.size(), 5);
        assertEquals(listing.get(0).getValue(), "2");
        assertEquals(listing.get(1).getValue(), "2");
        assertEquals(listing.get(2).getValue(), "*");
        assertEquals(listing.get(3).getValue(), "2");
        assertEquals(listing.get(4).getValue(), "+");
    }

    @Test
    void testConvertNotation_2_infix()
    {
        String s = "1 * 2 + 3 * 4";
        List<Typos> list = StringRegrex.analyse(s);
        List<Typos> listing = transformNotation(Notation.INFIX, list, false);

        assertEquals(listing.size(), 7);
        assertEquals(listing.get(0).getValue(), "1");
        assertEquals(listing.get(1).getValue(), "2");
        assertEquals(listing.get(2).getValue(), "*");
        assertEquals(listing.get(3).getValue(), "3");
        assertEquals(listing.get(4).getValue(), "4");
        assertEquals(listing.get(5).getValue(), "*");
        assertEquals(listing.get(6).getValue(), "+");
    }

    @Test
    void testConvertNotation_3_infix()
    {
        String s = "1 + 2 * 3 - 2 * 1";
        List<Typos> list = StringRegrex.analyse(s);
        List<Typos> listing = transformNotation(Notation.INFIX, list, false);

        assertEquals(listing.size(), 9);
        assertEquals(listing.get(0).getValue(), "1");
        assertEquals(listing.get(1).getValue(), "2");
        assertEquals(listing.get(2).getValue(), "3");
        assertEquals(listing.get(3).getValue(), "*");
        assertEquals(listing.get(4).getValue(), "+");
        assertEquals(listing.get(5).getValue(), "2");
        assertEquals(listing.get(6).getValue(), "1");
        assertEquals(listing.get(7).getValue(), "*");
        assertEquals(listing.get(8).getValue(), "-");
    }

    @Test
    void testConvertNotation_bracket()
    {
        String s = "( 2 + 2 ) * 3";
        List<Typos> list = StringRegrex.analyse(s);
        List<Typos> listing = transformNotation(Notation.INFIX, list, false);

        assertEquals(listing.size(), 5);
        assertEquals(listing.get(0).getValue(), "2");
        assertEquals(listing.get(1).getValue(), "2");
        assertEquals(listing.get(2).getValue(), "+");
        assertEquals(listing.get(3).getValue(), "3");
        assertEquals(listing.get(4).getValue(), "*");
    }
}
