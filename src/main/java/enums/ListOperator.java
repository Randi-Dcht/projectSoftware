package enums;

/**
 * List of operator in calculator
 */
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
    MODULUS("modulus", 4, 1),
    SQRT("sqrt", 4, 1),
    ;

    /**Value of the operator*/
    private final String value;
    /**Priority of the operator*/
    private final int priority;
    /**Number of arguments of the operator*/
    private final int numberArgs;

    /**
     * Constructor
     * @param value : value of the operator
     * @param priority : priority of the operator
     * @param numberArgs : number of arguments of the operator
     */
    ListOperator(String value, int priority, int numberArgs)
    {
        this.value = value;
        this.priority = priority;
        this.numberArgs = numberArgs;
    }

    /**
     * Get the value
     * @return the operator
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Get the priority
     * @return the priority
     */
    public int getPriority()
    {
        return priority;
    }


    /**
     * Get the number of arguments
     * @return the number of arguments
     */
    public int getNumberArgs()
    {
        return numberArgs;
    }
}
