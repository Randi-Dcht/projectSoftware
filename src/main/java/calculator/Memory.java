package calculator;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


/**
 * The memory class used to store the variables and the log
 */

public class Memory {

    /**
     * The memory is a arraylist of variables
     */
    private ArrayList<Variable> memory;

    /**
     * The size of the memory
     * -1 means infinite
     */
    private int maxSize;

    /**
     * The path to the log and memory files
     */
    private static final String path = "memlog/";

    /**
     * The name of the log file
     */
    private static final String logFile = "log.txt";

    /**
     * The name of the memory file
     */
    private static final String MemoryFile = "memory.txt";


    /**
     * The constructor of the memory
     * @param maxSize the maxSize of the memory
     */
    public Memory(int maxSize){
        memory = new ArrayList<>();
        this.maxSize = maxSize;
    }

    /**
     * The constructor of the memory
     * The size is infinite
     */
    public Memory() {
        memory = new ArrayList<>();
        this.maxSize = -1;
    }

    /**
     * The getter of the memory
     * @return the memory as an arraylist of variables
     */
    public ArrayList<Variable> getMemory() {
        return memory;
    }


    /**
     * Add a variable to the memory
     * @param name the name of the variable
     * @param number the value of the variable
     * @param expression the expression of the variable
     */
    public void add(String name, MyNumber number, Expression expression){
        if (this.memory.size() == maxSize) {
            throw new OutOfMemoryError("Memory is full");
        }
        // check if the variable already exists
        for(int i = 0; i < memory.size(); i++){
            if(memory.get(i).getName().equals(name)){
                // overwrite the variable
                memory.set(i,new Variable(name, number, expression));
            }
        }
        memory.add(new Variable(name, number, expression));
    }

    /**
     * Add a variable to the memory with an ID generated automatically as name
     * used for the log
     * @param number  the value of the variable
     * @param expression the expression of the variable
     */
    public void add(MyNumber number, Expression expression){
        if (memory.size() == maxSize) {
            throw new OutOfMemoryError("Memory is full");
        }
        String uniqueID = UUID.randomUUID().toString();
        this.memory.add(new Variable(uniqueID, number, expression));
    }

    /**
     * Get a variable from the memory
     * @param name the name of the variable
     * @return the variable
     */
    public Variable get(String name){
        for(Variable r: memory) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Variable does not exist");
    }



    /**
     * Remove a variable from the memory
     * @param name the name of the variable
     */
    public void remove(String name){
        for(Variable r: memory) {
            if (r.getName().equals(name)) {
                memory.remove(r);
                return;
            }
        }
        throw new IllegalArgumentException("Variable does not exist");
    }

    /**
     * Get the maximum size of the memory
     * @return the size of the memory
     */
    public int getMaxSize() {
        return this.maxSize;
    }

    /**
     * Redefine the maxSize of the memory
     * @param maxSize the new maxSize of the memory
     */
    public void setMaxSize(int maxSize) {
        if (maxSize < 0) {
            this.maxSize = maxSize;
            return;
        }
        if (maxSize < memory.size()) {
            throw new IllegalArgumentException("submitted maxSize is too small than the number of variables present in the memory");
        }
        this.maxSize = maxSize;
    }

    /**
     * Clear the memory
     */
    public void clear(){
        memory.clear();
    }

    /**
     * display last n variables entered into memory
     * @param n number of lines to display
     */
    public void displayLastData(int n) {
        if (n > memory.size()) {
            display();
        }
        for(int a = memory.size()-n; a!= memory.size(); a++) {
            Variable r = this.getMemory().get(a);
            System.out.println(r.toStringDetails());
        }
    }

    /**
     * display the whole memory
     */
    public void display() {
        for (Variable r : memory) {
            System.out.println(r.toStringDetails());
        }
    }

    /**
     * Save the log
     */
    public void saveLog() {
        save(logFile);
    }

    /**
     * Save the memory
     */
    public void saveMemory() {
        save(MemoryFile);
    }

    /**
     * Save the memory or the log
     * @param memoryFile the name of the file
     */
    public void save(String memoryFile) {
        try {
            FileWriter fileWriter = new FileWriter(path + memoryFile);
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
            for (Variable r : memory) {
                bufferedWriter.write(r.getTimeStamp() + " %%% " + r.getName() + " %%% " + r.toString() + " %%% " + r.getExpression());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();}
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * convert a string expression to an object expression
     */
    public Expression convertStringToExpression(String expressionString) {
        // TODO : upgrade this part to handle recursive expressions
        return null;
    }


    /**
     *  Load the file in the memory
     */
    public void loadFile(File file)  {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(" %%% ");
                String timeStamp = data[0];
                String name = data[1];
                MyNumber number =  new MyNumber(BigDecimal.valueOf(Double.parseDouble(data[2])));
                Expression expression = convertStringToExpression(data[3]);
                memory.add(new Variable(name, number, expression, timeStamp));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading log or memory file");
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Load the memory
     */
    public void loadMemory() {
        File memory = new File(path + MemoryFile);
        loadFile(memory);
    }

    /**
     *  Load the log
     */
    public void loadLog() {
        File log = new File(path + logFile);
        loadFile(log);
    }
}
