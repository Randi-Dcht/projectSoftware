package regex;

import enums.ListOperator;
import enums.TypeString;

/**
 * Class to process the input of user
 */
public class Typos
{
    /**
     * Value of the input
     */
    private final String value;
    /**
     * Type of the input
     */
    private final TypeString type;
    /**
     * Operator of the input
     */
    private final ListOperator operator;

    /**
     * Constructor
     * @param value : value of the input
     * @param type : type of the input
     * @param operator : operator of the input
     */
    public Typos(String value, TypeString type, ListOperator operator)
    {
        this.value = value;
        this.type = type;
        this.operator = operator;
    }

    /**
     * Constructor
     * @param value : value of the input
     * @param type : type of the input
     */
    public Typos(String value, TypeString type)
    {
        this(value, type, null);
    }

    /**
     * @return value of the input
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @return type of the input
     */
    public TypeString getType()
    {
        return type;
    }

    /**
     * @return operator of the input
     */
    public ListOperator getOperator()
    {

        return operator;
    }

    /**
     * @return priority of the operator
     */
    public int getPriority()
    {
        if (operator == null)
            return 0;
        return operator.getPriority();
    }
}
