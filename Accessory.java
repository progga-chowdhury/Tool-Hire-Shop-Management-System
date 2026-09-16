import java.util.Scanner;

/**
 * The Accessory class is an abstract subclass of ShopItem that represents additional
 * items available in the shop. It extends ShopItem by adding attributes specific
 * to accessories (e.g. whether the item is recyclable.) 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Accessory extends ShopItem
{
    // instance variables - replace the example below with your own
    
    //stores for whether the item is recyclable or not
    private boolean isRecyclable;

    /**
     * Constructor for objects of class Accessory, initialises the object and
     * calls the superclass constructor.
     */
    public Accessory()
    {
        // initialise instance variables
        super();
    }

    /**
     * Reads accessory data from a Scanner and initialises the object's fields.
     * This method first calls the superclass method to read common ShopItem data,
     * then reads the recyclable attribute.
     */
    
    @Override
    
    public void readData(Scanner scanner2)
    {
       this.isRecyclable = Boolean.parseBoolean(scanner2.next().trim());
       super.readData(scanner2);
    }
    
    /**
     * Prints full details of the accessory, including whether it is recyclable.
     */
    @Override
    
    public void printDetails()
    {
        super.printDetails();
        
        System.out.println("is recyclable: " + (isRecyclable ? "yes" : "no"));
    }
    
}