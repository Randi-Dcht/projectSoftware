package parser;

import cli.InputUser;
import enums.ListOperator;
import enums.TypeString;
import java.util.ArrayList;
import java.util.List;


public class StringRegrex
{
    private StringRegrex() {}


    public static final String REGEX_OPERATOR = "[+\\-*/combgcd//!%^ppcmprime]";

    public static List<Typos> analyse(String str)
    {
        List<Typos> list = new ArrayList<>();
        for (String s : InputUser.cleanInput(str))
        {
            if (s.matches("[0-9]+i+"))
            list.add(new Typos(s, TypeString.COMPLEX));
            else if (s.matches("[0-9]+\\.[0-9]+i+"))
                list.add(new Typos(s, TypeString.REAL_COMPLEX));
            else if (s.matches("[0-9]+"))
                list.add(new Typos(s, TypeString.INTEGER));
            else if (s.matches("[0-9]+\\.[0-9]+"))
                list.add(new Typos(s, TypeString.REAL));
            else if (s.matches(REGEX_OPERATOR))
            {
                for (ListOperator op : ListOperator.values())
                {
                    if (op.getValue().equals(s))
                        list.add(new Typos(s, TypeString.OPERATOR, op));
                }
            }
            else if (s.matches("[()]"))
                list.add(new Typos(s, TypeString.BRACKET));
            else if (s.matches("true|false"))
                list.add(new Typos(s, TypeString.BOOLEAN));
            else if (s.matches("[0-9]+E[0-9]+"))
                list.add(new Typos(s, TypeString.E_NOTATION));
            else if (s.matches("[0-9]+x10\\^[0-9]+"))
                list.add(new Typos(s, TypeString.SCIENTIFIC));
        }
        return list;
    }
}
