import java.util.Date;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The shopItemReservation clas represents a reservation made by a customer 
 * for a specific shop item they wish to take. It stores key information like the
 * reservation number which is unique, the customer ID, the item ID, the start
 * date of the reservation and the duration in days. There are also methods
 * to read and write reservation data and display their details.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class ShopItemReservation
{
    // instance variables - replace the example below with your own
    
    ////stores the reservation number
    private String reservationNo;
    //stores the item ID
    private String itemID;
    //stores the customer ID
    private String customerID; 
    //stores the start date of the reservation
    private Date startDate;
    //stores how many days the item is reserved 
    private int noOfDays;
    

    /**
     * Constructor for objects of class ShopItemReservation, creates an empty
     * reservation object which will be populated later with used data.
     */
    public ShopItemReservation()
    {
        
    }
    
    
    /**
     * Another constructor to create a reservation with all the required details, the start date
     * is provited as a String and then donverted into a date object for easier comparison
     * and manipulation
     */
    public ShopItemReservation( String reservationNo, String itemID, String customerID, String startDate, int noOfDays)
    {
      this.reservationNo = reservationNo;
      this.itemID = itemID;
      this.customerID = customerID;
      this.startDate = DateUtil.convertStringToDate(startDate);
      this.noOfDays = noOfDays;
    }

    /**
     * Returns the reservation number.
     */
    public String getReservationNo()
    {
        // put your code here
        return reservationNo;
    }
    
     /**
     * Returns the customer ID.
     */
    public String getCustomerID()
    {
        // put your code here
        return customerID;
    }
    
    /**
     * Returns the item ID.
     */
    public String getItemID()
    {
        return itemID;
    }
    
      /**
     * Returns the number of days for the reservation.
     */
    public int getNoOfDays()
    {
        // put your code here
        return noOfDays;
    }
    
    /**
     * Returns the start date of the reservation.
     */
    public Date getStartDate()
    {
        return startDate;
    }
    
    /**
     * Returns a short string to represent the reservation that is made
     */
    @Override
    public String toString()
    {
        return "Reservation No: " + reservationNo +
        ", Customer ID:" + customerID + ", Item ID:" + itemID;
    }
    
    /**
     * Prints the full details of the reservation, and the start date is
     * formatted into a long string.
     */
    public void printDetails()
    {
        System.out.println("Reservation No: " + reservationNo);
        System.out.println("Item ID: " + itemID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Start Date: " + DateUtil.convertDateToLongString(startDate));
        System.out.println("Number of days: " + noOfDays);
    }
    
    /**
     * Writes the reservationdata to a file in a comma separated format, the
     * date is converted into a short string format for better
     * consistency.
     */
    public void writeData (PrintWriter writer)
    {
      writer.println(reservationNo + "," + itemID + "," + 
      customerID + "," + DateUtil.convertDateToShortString(startDate) + "," + noOfDays);
    }
    
    /**
     * Reads the reservation data from a Scanner and populates the object, the data
     * is expected to be in a comma separated format, and it is the
     * read as a String and converted into a Date object.
     */
    public void readData(Scanner scanner)
    {
      reservationNo = scanner.next();
      itemID = scanner.next();
      customerID = scanner.next();
      
      String dateString = scanner.next();
      startDate = DateUtil.convertStringToDate(dateString);
      
      noOfDays = scanner.nextInt();
    }
}