package junit5tests;

import calculator.Notation;
import org.junit.jupiter.api.Test;
import parser.StringRegex;
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
        List<Typos> list = StringRegex.analyse(s);
        List<Typos> listing = transformNotation(Notation.INFIX, list, false);

        assertEquals(5, listing.size());
        assertEquals("2", listing.get(0).getValue());
        assertEquals("2", listing.get(1).getValue());
        assertEquals("*", listing.get(2).getValue());
        assertEquals("2", listing.get(3).getValue());
        assertEquals("+", listing.get(4).getValue());
    }

    @Test
    void testConvertNotation_2_infix()
    {
        String s = "1 * 2 + 3 * 4";
        List<Typos> list = StringRegex.analyse(s);
        List<Typos> listing = transformNotation(Notation.INFIX, list, false);

        assertEquals(7, listing.size());
        assertEquals("1",listing.get(0).getValue());
        assertEquals("2",listing.get(1).getValue());
        assertEquals("*",listing.get(2).getValue());
        assertEquals("3",listing.get(3).getValue());
        assertEquals("4",listing.get(4).getValue());
        assertEquals("*",listing.get(5).getValue());
        assertEquals("+",listing.get(6).getValue());
    }

    @Test
    void testConvertNotation_3_infix()
    {
        String s = "1 + 2 * 3 - 2 * 1";
        List<Typos> list = StringRegex.analyse(s);
        List<Typos> listing = transformNotation(Notation.INFIX, list, false);

        assertEquals(9, listing.size());
        assertEquals("1", listing.get(0).getValue());
        assertEquals("2", listing.get(1).getValue());
        assertEquals("3", listing.get(2).getValue());
        assertEquals("*", listing.get(3).getValue());
        assertEquals("+", listing.get(4).getValue());
        assertEquals("2", listing.get(5).getValue());
        assertEquals("1", listing.get(6).getValue());
        assertEquals("*", listing.get(7).getValue());
        assertEquals("-", listing.get(8).getValue());
    }

    @Test
    void testConvertNotation_bracket()
    {
        String s = "( 2 + 2 ) * 3";
        List<Typos> list = StringRegex.analyse(s);
        List<Typos> listing = transformNotation(Notation.INFIX, list, false);

        assertEquals(5, listing.size());
        assertEquals("2", listing.get(0).getValue());
        assertEquals("2", listing.get(1).getValue());
        assertEquals("+", listing.get(2).getValue());
        assertEquals("3", listing.get(3).getValue());
        assertEquals("*", listing.get(4).getValue());
    }

    @Test
    void check_transform()
    {
        String s = "2 2 +";
        List<Typos> list = StringRegex.analyse(s);
        List<Typos> listing = transformNotation(Notation.POSTFIX, list, false);

        assertEquals(3, listing.size());
        assertEquals("2", listing.get(0).getValue());
        assertEquals("2", listing.get(1).getValue());
        assertEquals("+", listing.get(2).getValue());
    }
}
