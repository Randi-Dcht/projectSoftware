package parser;

import enums.ListOperator;
import enums.TypeString;

public class Typos
{
    private final String value;
    private final TypeString type;

    private final ListOperator operator;

    public Typos(String value, TypeString type, ListOperator operator)
    {
        this.value = value;
        this.type = type;
        this.operator = operator;
    }

    public Typos(String value, TypeString type)
    {
        this(value, type, null);
    }

    public String getValue()
    {
        return value;
    }

    public TypeString getType()
    {
        return type;
    }

    public ListOperator getOperator()
    {

        return operator;
    }

    public int getPriority()
    {
        if (operator == null)
            return 0;
        return operator.getPriority();
    }
}
