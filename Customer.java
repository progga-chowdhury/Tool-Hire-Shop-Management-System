import java.util.Scanner;
import java.io.PrintWriter;

/**
 * The customer class represents a customer in the tool hire system, it stores personal
 * details like the unique customer id, and customer name details (e.g. title,
 * first name, surname, initials.) There are also methods provided to read and write
 * customer data as well as display the details of the customer.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer
{
    // instance variables - replace the example below with your own
    
    //the custoers unique ID
    private String customerID;
    //the customers last name
    private String surname;
    //the customers first name
    private String firstName;
    //other customer initials
    private String otherInitials;
    //the customers title
    private String title;

    /**
     * Constructor for objects of class Customer, creates a Customer object
     * with specified details.
     */
    public Customer(String surname,String firstName, String otherInitials, String title    )
    {
        this.customerID = "unknown";
        this.surname = surname;
        this.firstName = firstName;
        this.otherInitials = otherInitials;
        this.title = title;
    }
    
    /**
     * Default consturctor for creating an empty customer object which will be
     * populated later with used data.
     */
    public Customer()
    {
        
    }
    
    /**
     * Sets the customer ID.
     */
    public void setCustomerID(String id)
    {
        customerID = id;
    }
    
    /**
     * Returns the customer ID.
     */
    public String getCustomerID()
    {
        return customerID;
    }
    

    /**
     * Reads customer data from a scanner and the initialises the object's fields. 
     * The data is expected in a comma separated format and the
     * values are trimmed to remove extra spacing.
     */
    public void readData (Scanner scanner)
    {
       customerID = scanner.next().trim();
       surname = scanner.next().trim();
       firstName = scanner.next().trim();
       otherInitials = scanner.next().trim();
       title = scanner.next().trim();
    }
    
    /**
     * Prints the customer's details in a readable format.
     */
    public void printDetails()
    {
        System.out.println( "Customer ID: " + customerID + "; Name: " + title + "; First Name: " + firstName +
        "; Other Initials: " + otherInitials + "; Surname: " + surname);
    }
    
    /**
     * Writes the customer data to a file in a comma separated format.
     */
    public void writeData(PrintWriter writer)
    {
        writer.println( customerID + ", " + surname + ", " + firstName +
         ", " + otherInitials + ", " + title);
    }
    
}