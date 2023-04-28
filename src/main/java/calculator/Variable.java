package calculator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The variable class used to store the variables
 */
public class Variable extends MyNumber {

    /**
     * The name of the variable
     */
    private String name;

    /**
     * The expression of the variable
     */
    private Expression expression;

    /**
     * The time stamp of the variable
     */
    private String timeStamp;

    /**
     * The constructor of the variable
     * used to log custom variables in the memory
     * @param name the name of the variable
     * @param number the value of the variable
     * @param expression the expression of the variable
     */
    public Variable(String name, MyNumber number, Expression expression) {
        super(number.getValue(), number.getexp(),  number.getImaginary(), number.getImaginaryExp());
        this.name = name;
        this.expression = expression;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy.HH:mm:ss");
        this.timeStamp = df.format(new Date());
    }

    /**
     * The constructor of the variable
     * used to log variables in the log
     * @param name the name of the variable
     * @param number the value of the variable
     * @param expression the expression of the variable
     * @param timeStamp the time stamp of the variable
     */
    public Variable(String name, MyNumber number, Expression expression, String timeStamp) {
        super(number.getValue(), number.getexp(),  number.getImaginary(), number.getImaginaryExp());
        this.name = name;
        this.expression = expression;
        this.timeStamp = timeStamp;
    }

    /**
     * The constructor of the variable
     * used create a variable without expression
     * @param name the name of the variable
     * @param number the value of the variable
     */
    public Variable(String name, MyNumber number) {
        super(number.getValue(), number.getexp(),  number.getImaginary(), number.getImaginaryExp());
        this.name = name;
        this.expression = number;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy.HH:mm:ss");
        this.timeStamp = df.format(new Date());
    }

    /**
     * Get the name of the variable
     * @return the name of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the variable
     * @param name the name of the variable
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the expression of the variable
     * @return the expression of the variable
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Set the expression of the variable
     * @param expression the expression of the variable
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Get the time stamp of the variable
     * @return the time stamp of the variable
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * String representation of the variable with details
     * @return the name, value and expression of the variable
     */
    public String toStringDetails() {
        return this.timeStamp + " : " + this.name + " = " + this.toString() + " = " + this.expression;
    }
}
