package parser;

import enums.TypeString;

import java.util.ArrayList;
import java.util.List;

public class StringRegrex
{
    public static final String REGEX_OPERATOR = "[+\\-*/]";

    public static List<Typos> analyse(String str)
    {
        List<Typos> list = new ArrayList<>();
        for (String s : str.split(" "))
        {
            if (s.matches("[0-9]+"))
                list.add(new Typos(s, TypeString.INTEGER));
            else if (s.matches("[0-9]+\\.[0-9]+"))
                list.add(new Typos(s, TypeString.REAL));
            else if (s.matches(REGEX_OPERATOR))
                list.add(new Typos(s, TypeString.OPERATOR));
            else if (s.matches("()"))
                list.add(new Typos(s, TypeString.BRACKET));
        }
        return list;
    }
}
