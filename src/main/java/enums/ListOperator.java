package enums;

public enum ListOperator
{
    ADD("+", 1),
    SUB("-", 1),
    MUL("*", 2),
    DIV("/", 2),
    FACT("fact", 4),
    MOD("mod", 4),
    ;

    private final String value;
    private final int priority;

    ListOperator(String value, int priority)
    {
        this.value = value;
        this.priority = priority;
    }

    public String getValue()
    {
        return value;
    }

    public int getPriority()
    {
        return priority;
    }
}
