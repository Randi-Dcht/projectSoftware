package junit5tests;

import calculator.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

    @Test
    void loadMemory() {
        Memory m = new Memory(100);
        assert m.getMemory().isEmpty();
        File memory = new File("memlog/testmemory.txt");
        m.loadFile(memory);
        assert m.getMemory().get(0).getName().equals("v1");
        assert m.getMemory().get(1).getName().equals("v2");
        assert m.getMemory().get(2).getName().equals("v3");
        assert m.getMemory().get(3).getName().equals("v4");
        assert m.getMemory().get(4).getName().equals("v5");
    }

    @Test
    void loadLog() {
        Memory log = new Memory();
        assert log.getMemory().isEmpty();
        File memory = new File("memlog/testlog.txt");
        log.loadFile(memory);
        assert log.getMemory().get(0).getName().equals("v1");
        assert log.getMemory().get(1).getName().equals("v2");
        assert log.getMemory().get(2).getName().equals("v3");
        assert log.getMemory().get(3).getName().equals("v4");
        assert log.getMemory().get(4).getName().equals("v5");
        assert log.getMemory().get(5).getName().equals("test1");
        assert log.getMemory().get(6).getName().equals("test2");
    }

    @Test
    void saveMemory() throws IllegalConstruction {
        Memory m = new Memory(100);
        assert m.getMemory().isEmpty();
        File memory = new File("memlog/testmemory.txt");
        m.loadFile(memory);
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, new MyNumber(new BigDecimal(3)), new MyNumber(new BigDecimal(4)), new MyNumber(new BigDecimal(5)));
        Expression e = new Plus(params,Notation.PREFIX);
        m.add("a", new MyNumber(new BigDecimal(12)), e);
        m.save("testmemory.txt");
        try {
            Scanner sc = new Scanner(memory);
            String line = sc.nextLine();
            String[] data = line.split(" %%% ");
            assert data[1].equals("v1");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("v2");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("v3");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("v4");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("v5");
        } catch (Exception i) {
            System.out.println("Error reading log or memory file");
            System.out.println(i.getMessage());
        }
    }

    @Test
    void testLog() throws IllegalConstruction {
        Memory log = new Memory();
        assert log.getMemory().isEmpty();
        File memory = new File("memlog/testlog.txt");
        log.loadFile(memory);
        log.save("testmemory.txt");
        try {
            Scanner sc = new Scanner(memory);
            String line = sc.nextLine();
            String[] data = line.split(" %%% ");
            assert data[1].equals("v1");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("v2");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("v3");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("v4");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("v5");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("test1");
            line = sc.nextLine();
            data = line.split(" %%% ");
            assert data[1].equals("test2");

        } catch (Exception i) {
            System.out.println("Error reading log or memory file");
            System.out.println(i.getMessage());
        }
    }
}