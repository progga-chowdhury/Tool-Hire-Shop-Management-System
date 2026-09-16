import java.util.Scanner;

/**
 * The Perishable class represents a type of accessory that has a limited
 * expiry date or requires careful handling. It extends the Accessory class by adding the attributes
 * of whether the item is an irritant, the use-by-date and the volume of the item.
 * This class inherits common prperties from both ShopItem and Accessory.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Perishable extends Accessory
{
    // instance variables - replace the example below with your own
    
    //stores whether the item is an irritant by true ("yes") or false ("no")
    private boolean isIrritant;
    //stores the use by date as a String
    private String useByDate;
    //stores the volume of the item
    private int volume;

    /**
     * Constructor for objects of class Perishable, initialises the object     
     * and calls the superclass constructor.
     */
    public Perishable()
    {
        // initialise instance variables
       super();
    }

    /**
     * Reads perishable item from a Scanner and initialises the object's fields,
     * this method first calls the superclass method to read the common ShopItem
     * and Accessory data, and then reads perishable attributes.
     */
    @Override
    public void readData(Scanner lineScanner){
       super.readData(lineScanner);
        this.isIrritant = Boolean.parseBoolean(lineScanner.next().trim());
        this.useByDate = lineScanner.next().trim();
        this.volume = Integer.parseInt(lineScanner.next().trim());
    }
    

    /**
     * Prints full details of the perishable item, including specific attributes.
     */
    
    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("is irritant: " + isIrritant + ", use by date: " + useByDate + ", volume: " + volume );
    }
}