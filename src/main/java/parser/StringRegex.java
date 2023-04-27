package parser;

import calculator.NumberNotation;
import cli.InputUser;
import cli.Main;
import enums.ListOperator;
import enums.TypeString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StringRegex
{
    private StringRegex() {}


    public static final String REGEX_OPERATOR = "[+\\-*/combgcd//!%^ppcmprimesqrtmodulus]";
    public static final List<String> REGEX_STRING_OPERATOR = Arrays.asList("comb","gcd","ppcm","pgcd","prime","sqrt","modulus");

    public static List<Typos> analyse(String str)
    {
        List<Typos> list = new ArrayList<>();
        for (String s : InputUser.cleanInput(str))
        {
            if (Main.getMode().equals(NumberNotation.BINARY) && s.matches("[0-9]+x[0-9A-Za-z]+"))
                list.add(new Typos(s, TypeString.BINARY));
            else if (s.matches("[+-]?+[0-9]+i+"))
                list.add(new Typos(s, TypeString.COMPLEX));
            else if (s.matches("[+-]?+[0-9]+\\.[0-9]+i+"))
                list.add(new Typos(s, TypeString.REAL_COMPLEX));
            else if (s.matches("[+-]?+[0-9]+E+[+-]?+[0-9]+i+"))
                list.add(new Typos(s, TypeString.E_NOTATION_COMPLEX));
            else if (s.matches("[+-]?+[0-9]+x10\\^+[+-]?+[0-9]+i+"))
                list.add(new Typos(s, TypeString.SCIENTIFIC_COMPLEX));
            else if (s.matches("[+-]?+[0-9]+"))
                list.add(new Typos(s, TypeString.INTEGER));
            else if (s.matches("[+-]?+[0-9]+\\.[0-9]+"))
                list.add(new Typos(s, TypeString.REAL));
            else if (s.matches(REGEX_OPERATOR) || REGEX_STRING_OPERATOR.contains(s))
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
            else if (s.matches("[+-]?+[0-9]+E+[+-]?+[0-9]+"))
                list.add(new Typos(s, TypeString.E_NOTATION));
            else if (s.matches("[+-]?+[0-9]+x10\\^+[+-]?+[0-9]+"))
                list.add(new Typos(s, TypeString.SCIENTIFIC));
        }

        return list;
    }
}
