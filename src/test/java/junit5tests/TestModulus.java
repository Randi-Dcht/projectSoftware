package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestModulus {

    private final int value1 = 8;
    private final int value2 = 6;
    private Modulus op;
    private List<Expression> params;

    @BeforeEach
    void setUp() {
        params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
        try { op = new Modulus(params); }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Modulus(null));
    }

    @Test
    void testConstructor2() {
        // A Plus expression should not be the same as a Times expression
        try {
            assertNotSame(op, new Modulus(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should not be equal
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Modulus e = new Modulus(p, Notation.INFIX);
            assertEquals(op, e);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testNull() {
        assertDoesNotThrow(() -> op==null); // Direct way to test if the null case is handled.
    }

    @Test
    void testHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Modulus e = new Modulus(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Modulus(params));
    }

    @Test
    void testRealSolution() {
        MyNumber sol = op.op(new MyNumber(value1,value2));
        assertEquals(sol.isComplex(), false);
    }

}
