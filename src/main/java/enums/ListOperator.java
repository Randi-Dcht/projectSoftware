package enums;

public enum ListOperator
{
    ADD("+", 1, 2),
    SUB("-", 1, 2),
    MUL("*", 2, 2),
    DIV("/", 2, 2),
    COMB("comb", 4, 2),
    GCD("gcd", 4, 2),
    EUCLIDEAN("//", 4, 2),
    FACTO("!", 4, 1),
    MODULO("%", 4, 2),
    PGCD("pgcd", 4, 2),
    POW("^", 5, 2),
    PPCM("ppcm", 4, 2),
    PRIME("prime", 4, 2),

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
