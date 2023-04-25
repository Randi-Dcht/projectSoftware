package enums;

public enum ListOperator
{
    ADD("+", 1, 2),
    SUB("-", 1, 2),
    MUL("*", 2, 2),
    DIV("/", 2, 2),
    FACT("fact", 4, 2),
    MOD("mod", 4, 2),
    ;

    private final String value;
    private final int priority;
    private final int numberArgs;

    ListOperator(String value, int priority, int numberArgs)
    {
        this.value = value;
        this.priority = priority;
        this.numberArgs = numberArgs;
    }

    public String getValue()
    {
        return value;
    }

    public int getPriority()
    {
        return priority;
    }

    public int getNumberArgs()
    {
        return numberArgs;
    }
}
