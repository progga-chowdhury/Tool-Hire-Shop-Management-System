import java.util.Scanner;
/**
 * The ElectricTool class represents a specific type of tool that needs
 * electrical power, it extends the Tool class by adding attributes that are
 *  specific to electric tools (e.g. whether the tool is rechargable, the power.)
 *  This class also inherits common tool properties and behaviour from the Tool class.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ElectricTool extends Tool
{
    // instance variables - replace the example below with your own
    
    //stores whether the electric tool is recharaghable or not
    private boolean rechargable;
    // stores the power of the elctric item
    private String power;

    /**
     * Constructor for objects of class ElectricTool, creates an Electric
     * Tool with specified attributes.
     */
    public ElectricTool(boolean rechargable, String power)
    {
        // initialise instance variables
        super();
        this.rechargable = rechargable;
        this.power = power;
    }
    
    /**
     * Defaut constructor for ElectricTool, initialises the object with
     * default values.
     */
    public ElectricTool()
    {
        // initialise instance variables
        super();
        this.rechargable = true;
        this.power = "80W";
    }
    

    /**
     * Reads electric tool data from a scanner and initialises the object's
     * fields. The method first calls the superclass method to read the common
     * Tool and ShopItem data, and then it reads the additional electric tool
     * attributes.
     */
    @Override
    public void readData (Scanner scanner) 
    {
        super.readData(scanner);
        this.rechargable = Boolean.parseBoolean(scanner.next().trim());
        this.power = scanner.next().trim();
       
    }
    
    /**
     * Prints full details of the electric tool.
     */
    @Override
    
    public void printDetails(){
        super.printDetails();
        System.out.println("; rechargable " + rechargable + "; power: " + power);
    }
    
    
}