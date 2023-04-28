package cli;

import calculator.Notation;
import enums.TypeString;
import regex.Typos;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Class to process the input of user
 */
public class ConvertNotation
{
    private ConvertNotation() {}

    /**
     * Convert the data in infix to postfix for compute
     */
    private static List<Typos> convertInfix(List<Typos> data)
    {
        List<Typos> buff = new ArrayList<>();
        Stack<Typos> stack = new Stack<>();

        for(Typos e : data)
        {
            if (e.getType().equals(TypeString.OPERATOR))
            {
                if (stack.empty())
                    stack.push(e);
                else
                {
                    if (e.getPriority() > stack.peek().getPriority())
                        stack.push(e);
                    else
                    {
                        while (!stack.empty() && e.getPriority() <= stack.peek().getPriority())
                            buff.add(stack.pop());
                        stack.push(e);
                    }
                }
            }
            else if (e.getType().equals(TypeString.BRACKET))
            {
                if (e.getValue().equals(")"))
                {
                    while (!stack.empty() && !stack.peek().getValue().equals("("))
                        buff.add(stack.pop());
                    stack.pop();
                }
                else
                    stack.push(e);
            }
            else
                buff.add(e);
        }
        while (!stack.empty())
            buff.add(stack.pop());

        return buff;
    }


    /**
     * Convert the data in postfix for compute
     */
    public static List<Typos> transformNotation(Notation notationIn, List<Typos> data, boolean isPrint)
    {
        List<Typos> array;

        if (notationIn.equals(Notation.INFIX))
            array = convertInfix(data);
        else
            array = data;

        if (isPrint)
        {
            for (Typos word : array)
                Main.printing(word.getValue() + " ",false);
            Main.printing("\n", false);
        }

        return array;
    }
}
