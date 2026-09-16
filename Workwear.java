import java.util.Scanner;

/**
 * The Workwear class represents a type of Accessory that includes protective 
 * or work related clothing. It extends the Accessory class by adding attributes such
 * as the manufacturing standard, the colour and size. This class inherits common
 * properties from both ShopItem and Accessory.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Workwear extends Accessory
{
    // instance variables - replace the example below with your own
    
    //stores the manufacturing stadard 
    private String manufacturingStandard;
    //stores the colour of the workwear
    private String colour;
    //stores the sixe of workwear
    private String size;

    /**
     * Constructor for objects of class Workwear, initialises the object     
     * and calls the superclass constructor.
     */
    public Workwear()
    {
        // initialise instance variables
        super();
    }

    /**
     * Reads workwear data from a Scanner and initialises the object's fields,
     * this method first calls the superclass method to read the common ShopItem
     * and Accessory data, and then reads workwear attributes.
     */
     @Override
    public void readData(Scanner lineScanner){
        super.readData(lineScanner);
        this.manufacturingStandard = lineScanner.next().trim();
        this.colour = lineScanner.next().trim();
        this.size = lineScanner.next().trim();
    }
    

    /**
     * Prints full details of the workwear item, including its specific 
     * attributes.
     */
    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("manufacturing standard: " + manufacturingStandard + ", colour: " + colour + ", size: " + size);
    }
    
}