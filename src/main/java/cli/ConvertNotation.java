package cli;

import calculator.Notation;

import java.util.ArrayList;
import java.util.List;

public class ConvertNotation
{

    /**
     * Convert the data in infix to postfix for compute
     */
    private static List<String> convertInfix(List<String> data)
    {
        List<String> buff = new ArrayList<>();
        String stock= "";
        int openBracket = 0;

        for (String word : data)
        {
            switch (word)
            {
                case "(" -> openBracket++;
                case ")" -> {
                    buff.add(stock);
                    openBracket--;
                }
                case "+", "-", "*", "/" -> stock = word; //TODO replace this because same case in switch
                default -> buff.add(word);
            }
        }

        if (openBracket != 0)
            throw new IllegalArgumentException("Error: open bracket not close");

        return buff;
    }


    /**
     * Convert the data in prefix to postfix for compute
     */
    private static List<String> convertPrefix(List<String> data)
    {
        List<String> buff = new ArrayList<>();



        return buff;
    }


    /**
     * Convert the data in postif for compute
     */
    public static List<String> transformNotation(Notation notationIn, List<String> data, boolean isPrint)
    {
        List<String> array;

        if (notationIn.equals(Notation.INFIX))
            array = convertInfix(data);
        else if (notationIn.equals(Notation.PREFIX))
            array = convertPrefix(data);
        else
            array = data;

        if (isPrint)
        {
            for (String word : array)
                System.out.print(word + " ");
            System.out.println(" ");
        }

        return array;
    }
}
