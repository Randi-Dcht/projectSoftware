package junit5tests;

import calculator.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {

    @Test
    void add() throws IllegalConstruction {
        Memory m = new Memory(100);
        Memory l = new Memory();
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, new MyNumber(new BigDecimal(3)), new MyNumber(new BigDecimal(4)), new MyNumber(new BigDecimal(5)));
        Expression e = new Plus(params,Notation.PREFIX);
        m.add("a", new MyNumber(new BigDecimal(12)), e);
        l.add(new MyNumber(new BigDecimal(12)), e);
        assertEquals(m.getMemory().get(0).getName(), "a");
        assertEquals(m.getMemory().get(0).getValue(), new BigDecimal(12));
        assertEquals(m.getMemory().get(0).getExpression(), e);
        assertEquals(l.getMemory().get(0).getValue(), new BigDecimal(12));
        assertEquals(l.getMemory().get(0).getExpression(), e);
        List<Expression> params2 = new ArrayList<>();
        Collections.addAll(params2, new MyNumber(new BigDecimal(3)), new MyNumber(new BigDecimal(3)), new MyNumber(new BigDecimal(3)));
        Expression e2 = new Plus(params,Notation.PREFIX);
        m.add("a",  new MyNumber(new BigDecimal(9)), e2);
        assertEquals(m.getMemory().get(0).getName(), "a");
        assertEquals(m.getMemory().get(0).getValue(), new BigDecimal(9));
        assertEquals(m.get("a"), new Variable("a", new MyNumber(new BigDecimal(9)), e2));

        for (int i = 0; i < 98; i++) {
            m.add("a", new MyNumber(new BigDecimal(9)), e2);
        }
        assertThrows(OutOfMemoryError.class, () -> m.add("a", new MyNumber(new BigDecimal(9)), e2));
    }

    @Test
    void remove() throws IllegalConstruction {
        Memory m = new Memory(100);
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, new MyNumber(new BigDecimal(3)), new MyNumber(new BigDecimal(4)), new MyNumber(new BigDecimal(5)));
        Expression e = new Plus(params,Notation.PREFIX);
        m.add("a", new MyNumber(new BigDecimal(12)), e);
        m.remove("a");
        assert m.getMemory().isEmpty();
        assertThrows(RuntimeException.class, () -> m.remove("a"));
    }

    @Test
    void clearMemory() throws IllegalConstruction {
        Memory m = new Memory(100);
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, new MyNumber(new BigDecimal(3)), new MyNumber(new BigDecimal(4)), new MyNumber(new BigDecimal(5)));
        Expression e = new Plus(params,Notation.PREFIX);
        m.add("a", new MyNumber(new BigDecimal(12)), e);
        m.clear();
        assert m.getMemory().isEmpty();
    }

    @Test
    void size() throws IllegalConstruction {
        Memory m = new Memory(100);
        assert m.getMaxSize() == 100;
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, new MyNumber(new BigDecimal(3)), new MyNumber(new BigDecimal(4)), new MyNumber(new BigDecimal(5)));
        Expression e = new Plus(params,Notation.PREFIX);
        for(int i = 0; i < 100; i++) {
            m.add("a", new MyNumber(new BigDecimal(12)), e);
        }
        assertThrows(RuntimeException.class, () -> m.setMaxSize(50));
    }
}