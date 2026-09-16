import java.util.Scanner;

/**
 * The ShopItem class is an abstract class represting a generic item that
 * is available within the shop. It stores common properties shared by all
 * the item types (e.g. item name, item code, cost). 
 * 
 * Subclasses like Tool and Workwear extend this class to add more
 * specific attributes and behaviour.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class ShopItem
{
    // instance variables - replace the example below with your own
    
    //name of the item
    private String itemName;
    //unique code item
    private String itemCode;
    //cost of the item
    private int cost;

    /**
     * Constructor for objects of class ShopItem which will create a ShopItem object,
     * the subclasses will extend this constructor.
     */
    public ShopItem()
    {
        // initialise instance variables
        super();
    }
    

    /**
     * Reads the item data from a Scanner and initialises the object's
     * fields. The data is expected in a comma separated format like all other data
     * types. Each value is then trimmeed to remove extra spaces, and cost is converted
     * from a String to an Integer.
     */
    public void readData (Scanner scanner2)
    {
        this.itemName = scanner2.next().trim();
        this.itemCode = scanner2.next().trim();
        this.cost = Integer.parseInt(scanner2.next().trim());
    }
    
    /**
     * Prints basic details of the item
     */
    public void printDetails()
    {
        System.out.println("; Item name: " + itemName + "; code " +
        itemCode + "; cost: " + cost);
    }
    
    /**
     * Returns the item name.
     */
   
    public String getItemName()
    {
        return itemName;
    }
    
    /**
     * Returns the item code.
     */
    public String getItemCode()
    {
        return itemCode;
    }
    
    /**
     * Returns the item cost.
     */
    public int getCost()
    {
        return cost;
    }
    
}