import java.util.Scanner;

/**
 * The Tool class is an abstract subclass of ShopItem that represents
 * tools available for hire. It extends ShopItem by adding attributes
 * that are specific to each tool (e.g. number of times borrowed, if the tool is
 * currently on loan, the weight of the tool.) 
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Tool extends ShopItem
{
    // instance variables - replace the example below with your own
    
    //stores how many times the tool has been borrowed
    private int timesBorrowed;
    //stores whether the tool is on loan or not by true ("yes") or false ("no")
    private boolean onLoan;
    //stores the weight of the tool
    private int weight;
    
    //private boolean rechargable;
    //private String power;
    //private boolean sharpenable;

    /**
     * Constructor for objects of class Tool, initialises tool attributes
     * with default values.
     */
    public Tool()
    {
        // initialise instance variables
        super();
        
        timesBorrowed = 4;
        onLoan = false;
        weight = 10;
    }
    
    /**
     * Constructs a Tool object with specified values.
     */
    public Tool(int timesBorrowed, boolean onLoan, int weight  )
    {
        // initialise instance variables
        this.timesBorrowed = timesBorrowed;
        this.onLoan = onLoan;
        this.weight = weight;
        
        //this.rechargable = rechargable;
        //this.power = power;
        //this.sharpenable = sharpenable;
    }
    
    /**
     * Returns how many times the tool has been borrowed.
     */
    public int getTimesBorrowed()
    {
        return timesBorrowed;
    }
    
    /**
     * Returns whether the tool is currently on loan or not.
     */
    
    public boolean isOnLoan()
    {
        return onLoan;
    }
    
    /**
     * Returns the weight of the tool.
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Prints the full details of the tool, the method overrides the parent 
     * class method to include additional information that is specific to that tool.
     */
    
    @Override
    
    public void printDetails()
    {
        super.printDetails();
        
        System.out.println( "; timesBorrowed: " + timesBorrowed + 
        "; onLoan: " + (onLoan ? "yes" : "no") + "; weight: " + weight
        
        //"; rechargable: " + (rechargable ? "yes" : "no") +
        //"; power: " + power + "; sharpenable: " + (sharpenable ? "yes" : "no")
        
        );
    }
    
    /**
     * Reads the tool data from a scanner and initialises the object's fields,
     * this method first calls the superclass version in order to read the common
     * shopItem data, and then it reads all the additional tool-specific attributes.
     */
    @Override
    
    public void readData (Scanner scanner)
    {
        super.readData(scanner);
        
        timesBorrowed = Integer.parseInt(scanner.next().trim());
        onLoan = Boolean.parseBoolean(scanner.next().trim());
        weight = Integer.parseInt(scanner.next().trim()); 
    }
    
}
