import java.util.Scanner;
/**
 * The HandTool class represents a type of Tool that is manually operated, 
 * it extends the Tool class by adding attributes specific to hand tools
 * (e.g. whether the tool can be sharpened.) This class also inherits common
 * properties and behaviour from Tool.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HandTool extends Tool
{
    // instance variables - replace the example below with your own
    
    //stores whether the handtool is sharpenable or not
    private boolean sharpenable;

    /**
     * Constructor for objects of class HandTool, creates  a HandTool object
     * with specified sharpenable property.
     */
    public HandTool(boolean sharpenable)
    {
        // initialise instance variables
        super();
        this.sharpenable = sharpenable;
    }
    
    /**
     * Defaut constructor for HandTool, initialises the object with
     * default sharpenable value.
     */
    public HandTool()
    {
       // initialise instance variables
        super();
        this.sharpenable = true;
    }

    /**
     * Reads hand tool data from a scanner and initialises the object's
     * fields. The method first calls the superclass method to read the common
     * Tool and ShopItem data, and then it reads the additional sharpenable
     * attribute.
     */
    @Override
    public void readData (Scanner scanner) 
    {
        super.readData(scanner);
        this.sharpenable = Boolean.parseBoolean(scanner.next().trim());
       
    }
    
    /**
     * Prints full details of the hand tool, including whether it is sharpenable.
     */  
    
    @Override
    
    public void printDetails(){
        super.printDetails();
        System.out.println("; sharpenable: " + sharpenable);
    }
    
    
}