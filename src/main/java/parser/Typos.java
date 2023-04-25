package parser;

import enums.TypeString;

public class Typos
{
    private final String value;
    private final TypeString type;

    public Typos(String value, TypeString type)
    {
        this.value = value;
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public TypeString getType()
    {
        return type;
    }
}
