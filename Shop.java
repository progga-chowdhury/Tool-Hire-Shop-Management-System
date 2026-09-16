import java.util.ArrayList;
import java.io.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Random;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

/**
 * This shop class represents the main system of the entire tool hire application, 
 * it's responsible for managing shop items (including tools and accessories), customers
 * and item reservations. The class also uses both lists and maps, the lists provide ordered storage
 * and iteration whereas maps allow quick retrieval of info using unique identifiers.
 * 
 * This class supports different functionalities, such as reading and writing data from files,
 * generating unique customer ID and reservation numbers, creating and justifying reservations, and 
 * checking the availability using a diary system. Overall, the shop class acts as the central
 * controller of the entire system.
 *
 * @author (Progga Chowdhury)
 * @version (a version number or a date)
 */
public class Shop
{
    // private ArrayList<ShopItem> itemList;
    // private ArrayList<Customer> customerList;
    
    //Random generator to create unique Customer Ids
    private Random randomGenerator;
    //Stores all previously generated IDs to ensure uniqueness
    private HashSet<String> usedIDs;
    //Lists used to store items and customers
    private List<ShopItem> itemList;
    private List<Customer> customerList;
    //maps using unique identifiers for a faster lookup
    private Map<String, Customer> customerMap;
    private Map<String, ShopItem> itemsMap;
    //Stores reservations using the reservation numbers as a key
    private Map<String, ShopItemReservation> itemReservationMap;
    //Keeps track of thr last reservation number that was generated
    private int lastReservationNo;
    //diary object used to manage all the reservation dates anc conflicts
    private Diary diary;
    
    

    /**
     * Constructor for objects of class Shop, it initialises all the 
     * data structures used in the system, the lists store iteams and customers for 
     * easy iteration, maps allow fast look up for unique id numbers,
     * and hashsets ensure that the generated custmer ids are all unique.
     */
    public Shop()
    {
        itemList = new ArrayList<>(); //starts with an empty item list
        customerList = new ArrayList<>(); //starts with an empty customer list
        randomGenerator = new Random(); //used to generate random ids
        usedIDs = new HashSet<>(); //used to track currently existing ids
        
        customerMap = new HashMap<>();
        itemsMap = new HashMap<>();
        lastReservationNo = 0;
        
        itemReservationMap = new HashMap<>();
        
        
        
        this.diary = new Diary();
        
    }

    /**
     * Generates a unique reservation number, (eg. 000001)
     */
    public String generateReservationNo()
    {
        lastReservationNo++;
        
        return String.format("%06d", lastReservationNo);
    }
    
    
    /**
     * Stores a reservation in the map and inside the diary
     */
    public void storeItemReservation(ShopItemReservation reservation){
        
        itemReservationMap.put(reservation.getReservationNo(), reservation);
        diary.addReservation(reservation);
    }
    
    /**
     * Stores a shop item in both the map and the list 
     */
    public void storeItem(ShopItem item){
        
        itemList.add(item);
        itemsMap.put(item.getItemCode(), item);
    }
    
    /**
     * Stores a customer and assigns an ID if it is needed
     */
    public void storeCustomer (Customer customer)
    {
        // if the ID is uknown, then the method generate one
        if(customer.getCustomerID().equals("unknown"))
        {
            String newID = generateCustomerID("AB-", 6);
            customer.setCustomerID(newID);
        }
        
        customerList.add(customer);
        customerMap.put(customer.getCustomerID(),customer);
    }
    
    /**
     * Returns a customer by using their ID
     */
    public Customer getCustomer(String id)
    {
        return customerMap.get(id);
    }
    
    
    /**
     * Returns an item using its code
     */
    public ShopItem getItem(String code)
    {
        return itemsMap.get(code);
    }
    
    /**
     * Prints all shop items
     */
    public void printAllItems()
    {
        for (ShopItem item : itemsMap.values())
        {
            item.printDetails();
        }
    }
    
    /**
     * Prints all customers
     */
    public void printAllCustomers()
    {
        for (Customer customer : customerMap.values())
        {
            customer.printDetails();
        }
    }
    
    /**
     * returns the list of all customers in shop, allows external classes like the
     * test class to access customer info without modifying customerList. Used
     * to auto generate reservations in test class.
     */
    public List<Customer>getCustomerList()
    {
        return customerList;
    }
    
     /**
     * returns the list of all shop items like tool, accessories, etc. Allows external classes like the
     * test class to access the items and then used
     * to auto generate reservations in test class, printing item details.
     */
    
    public List<ShopItem>getItemList()
    {
        return itemList;
    }
    
    /**
     * returns the map of all item reservations in shop, the key is the reservation
     * number which is a String, and the value is the corresponding ShopItemReservation
     * object. Used for accessing or deleting methods e.g. in the test class.
     */
    public Map<String, ShopItemReservation> getItemReservationMap()
    {
        return itemReservationMap;
    }
    
    
    /**
     * Reads the tool data from a selected file and creates corresponding objects.
     * 
     * The method uses a filedialog to allow the user to select a file, then it 
     * processes the file line by line. Empty lines and comment lines that start with
     * "//" are all ignored. Section headers like [Electric] and [Hand]
     * are used to determine the type of tool that is being read.
     * 
     * Based on the type of object that is detected, the appropriate subclass
     * of ShopItem is then iniantiated, new tools e.g. ElectricTool/HandTool
     * are the created. Each line of data is split using a comma delimiter, and the
     * readData() method is the called to populate all the objects fields.
     * 
     * Each created object is then stored in both the map and the list using
     * storeItem().
     * 
     * 
     */
    public void readToolData()
    {
        FileDialog fileBox = new FileDialog((Frame) null, "Select tool data file", FileDialog.LOAD);
        fileBox.setVisible(true);
        //fileBox.setDirectory("C:\\Users\\progg\\Downloads");
        
        String fileName = fileBox.getFile();
        String directory = fileBox.getDirectory();
        
        if (fileName == null)
        {
            System.out.println("No file selected.");
        }
        
        System.out.println("Selected file: " + fileName);
        
        try
        {
            File dataFile = new File(directory,fileName);
            Scanner scanner = new Scanner(dataFile);
            
            String typeOfData = "";
            
            while (scanner.hasNextLine())
            {
                String lineOfText = scanner.nextLine().trim();
                
                if (lineOfText.isEmpty() || lineOfText.startsWith("//"))
                {
                    continue;
                }
                
                if (lineOfText.startsWith("["))
                {
                    if (lineOfText.toLowerCase().contains("electric"))
                    {
                        typeOfData = "electric";
                    }
                    
                    else if (lineOfText.toLowerCase().contains("hand"))
                    {
                        typeOfData = "hand"; 
                    }
                    
                    else if (lineOfText.toLowerCase().contains("perishable"))
                    {
                        typeOfData = "perishable"; 
                    }
                    
                    else if (lineOfText.toLowerCase().contains("workwear"))
                    {
                        typeOfData = "workwear"; 
                    }
                    
                    continue;
                }
                
                
                Scanner scanner2 = new Scanner(lineOfText);
                scanner2.useDelimiter(",\\s*");
            
                ShopItem item = null;
                
                if (typeOfData.equals("electric"))
                {
                    item = new ElectricTool();
                }
                
                else if (typeOfData.equals("hand"))
                {
                    item = new HandTool();
                }
                
                else if (typeOfData.equals("perishable"))
                {
                    item = new Perishable();
                }
                
                else if (typeOfData.equals("workwear"))
                {
                    item = new Workwear();
                }
                
                if (item != null)
                {
                    item.readData(scanner2);
                    storeItem(item);
                }
                
                scanner2.close();
            }
            
            scanner.close();
            
        }
        
        catch (FileNotFoundException e)
        {
            System.out.println("File is not found: " + fileName);
        }
    }
    
    /**
     * Reads customer data from a selected file and stores it in the system, the 
     * method uses a FileDialog to allow the user to select a file, it then reads
     * the file line by line, empty lines and comment lines starting with "//" are
     * all ignored, and each valid line represents one customers data.
     * 
     * Each line is split by the use of a comma delimiter and then is
     * passed to a scanner. A customer object is then created and populated using
     * the readData() method. The customer is then stored be using the method
     * storeCustomer() which will also ensure a unique id is assigned if required.
     * 
     */
    public void readCustomerData()
    
    {
        FileDialog fileBox = new FileDialog((Frame) null, "Select customer data file", FileDialog.LOAD);
        fileBox.setVisible(true);
        //fileBox.setDirectory("C:\\Users\\progg\\Downloads");
        
        String fileName = fileBox.getFile();
        String directory = fileBox.getDirectory();
        
        if (fileName == null)
        {
            System.out.println("No file selected.");
            return;
        }
        
        try
        {
          File dataFile = new File (directory, fileName);
          Scanner scanner = new Scanner(dataFile);
        
        while (scanner.hasNextLine())
            {
                String line = scanner.nextLine().trim();
                
                if (line.isEmpty() || line.startsWith("//"))
                {
                    continue;
                }
                
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("\\s*, \\s*");
                
                Customer customer = new Customer();
                customer.readData(lineScanner);
                
                storeCustomer(customer);
                
                lineScanner.close();
                
        }
        
        scanner.close();
       }
       
       catch (FileNotFoundException e)
       {
           System.out.println("File not found." + fileName);
       }
    }
    
    /**
     * Writes all customers to file
     */
    public void writeCustomerData() throws IOException
    {
        PrintWriter writer = new PrintWriter("customer_output.txt");
        
        for (Customer customer : customerMap.values())
        {
            customer.writeData(writer);
        }
        
        writer.close();
    }
    
    //
    /**
     * Generates a unique customer ID
     */
    public String generateCustomerID(String prefix, int digits)
    {
        String newID;
        
        do
        {
            int randomNum = randomGenerator.nextInt((int)Math.pow(10, digits));
            String formattedNumber = String.format("%0" + digits + "d", randomNum);
            newID = prefix + formattedNumber;
        } while (usedIDs.contains(newID));
        
        usedIDs.add(newID);
        
        return newID;
    }
    
    /**
     * returns a reservation by the ID number
     */
    public ShopItemReservation getItemReservation( String reservationNo)
    {
        return itemReservationMap.get(reservationNo);
    }
    
    /**
     * Generates a reservation if it is a valid one, the method first validates
     * the input by checking is the customer exists within the system, if the item 
     * exists in the system and if the number of days is greater than 0.
     * 
     * The start date is then converted from a string into a Date object, 
     * the method loops through each day or the requested reservation period (eg 10 days),
     * for each date, it checks the diary for already existing reservations. 
     * 
     * If the Item is already reserved on any of the requested dates, the reservation
     * is rejected in order to prevent double booking. If all the checks pass, a unique
     * reservation number is then generated, and a new ShopItemReservation object
     * is created, the reservation is the stored in the map and diary.
     */
    public boolean makeItemReservation(String customerID, String itemID, String startDate, int noOfDays)
    {
        Customer customer = getCustomer(customerID);
        ShopItem item = getItem(itemID);
    
        if(customer == null)
        {
            System.out.println("Customer not found.");
            return false;
        }
    
        if(item == null)
        {
            System.out.println("Item not found.");
            return false;
        }
        
        if(noOfDays <= 0)
        {
            System.out.println("Number of days must be greater than 0.");
            return false;
        }
        
        Date start = DateUtil.convertStringToDate(startDate);
        
        //checks inside the diary for any clashes
        for (int i = 0; i < noOfDays; i++)
        {
            Date checkDate = DateUtil.incrementDate(start, i);
            ShopItemReservation[] reservationsOnDay = diary.getReservations(checkDate);
            
            if (reservationsOnDay != null)
            {
                for (ShopItemReservation reservation : reservationsOnDay)
                {
                    if (reservation.getItemID().equals(itemID))
                    {
                        System.out.println("Sorry, item " + itemID + " is already reserved on "
                        + DateUtil.convertDateToShortString(checkDate) + ". Reservation not made.");
                        
                        return false;
                    }
                }
            }
        }
    
        //creates the reservation
        String reservationNo = generateReservationNo();
    
        ShopItemReservation reservation = new ShopItemReservation(reservationNo, itemID, customerID, startDate, noOfDays);
    
        storeItemReservation(reservation);
        
        System.out.println("Reservation " + reservationNo + " has been made!");
    
        return true;  
    }
    
    /**
     * prints all the reservations
     */
    public void printItemReservations()
    {
        for(ShopItemReservation reservation : itemReservationMap.values())
        {
            reservation.printDetails();
            System.out.println();
        }
    }
    
    /**
     * Reads customer data from a selected file and stores it in the system, the 
     * method uses a FileDialog to allow the user to select a file, it then reads
     * the file line by line, empty lines and comment lines starting with "//" are
     * all ignored, and each valid line represents a record of a reservation.
     * 
     * Each line is split by the use of a comma delimiter and then is
     * passed to a scanner. A ShopItemReservation object is then created and populated using
     * the readData() method. The reservation is then stored be using the method
     * storeItemReservation() which will also adds it to the Diary for date tracking.
     */
    public void readItemReservationData()
    {
        FileDialog fileBox = new FileDialog((Frame)null, "Select reservation Data File", FileDialog.LOAD);
        fileBox.setVisible(true);
        
        String fileName = fileBox.getFile();
        String directory = fileBox.getDirectory();
        
        if (fileName == null)
        {
            System.out.println("No file selected.");
            return;
        }
        
        try
        {
            File dataFile = new File (directory, fileName);
            Scanner scanner = new Scanner (dataFile);
            
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine().trim();
                
                if (line.isEmpty() || line.startsWith("//"))
                {
                    continue;
                }
                
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("\\s*, \\s*");
                
                ShopItemReservation reservation = new ShopItemReservation();
                
                reservation.readData(lineScanner);
                storeItemReservation(reservation);
                
                lineScanner.close();
            }
            
            scanner.close();
        }
        
        catch (FileNotFoundException e)
        {
            System.out.println("File not found." + fileName);
        }
    }
        
    /**
     * Writes the reservation to a file
     */
    public void writeItemReservationData() throws IOException
    {
        PrintWriter writer = new PrintWriter("reservation_output.txt");
        
        for(ShopItemReservation reservation : itemReservationMap.values())
        {
            reservation.writeData(writer);
        }
        
        writer.close();
    }
    
    /**
     * prints diary entries between two dates, start and end date
     */
    public void printDiaryEntries(String startDateStr, String endDateStr)
    {
        Date startDate = DateUtil.convertStringToDate(startDateStr);
        Date endDate = DateUtil.convertStringToDate(endDateStr);
        diary.printEntries(startDate, endDate);
    }
    
    /**
     * Deletes a reservation from the system using its reservation number.
     * This method retrieves the reservation from the map, and if it exists, it is
     * removed from the diary and the reservation map to ensure consistency between
     * stored data and booking records. In case the reservation does not already exist,
     * an appropriate error message is then displayed.
     * 
     */
    
    public void deleteItemReservation(String reservationNo)
    {
        ShopItemReservation reservation = itemReservationMap.get(reservationNo);
        
        if (reservation == null)
        {
            System.out.println("Reservation " + reservationNo + " not found.");
        }
        
        diary.deleteReservation(reservation);
        itemReservationMap.remove(reservationNo);
        System.out.println("Reservation " + reservationNo + " deleted.");
    }
}
    