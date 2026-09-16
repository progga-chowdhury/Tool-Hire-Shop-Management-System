import java.io.*; 
import java.util.List;

/**
 *  This test clas sis used for the shop system, it demonstrates loading tool data,
 *  loading customer data, generating reservations automatically, testing reservation conflicts
 *  and writing data back into files.
 */

public class Test
{
   public static void main(String[] args) throws IOException
   {
       //Create a new Shop Instace which manages tools, customers, and reservations
       Shop myShop = new Shop();
       
       //Load Tool Data
       System.out.println("Tool Items:");
       myShop.readToolData(); //Reads tools from a file selected by the user
       
       System.out.println("\nAll Items:");
       myShop.printAllItems(); //prints all the tool items that are loaded
       
       //Load customer data
       System.out.println("\nCustomer data:");
       myShop.readCustomerData();// reada customers from a file selected by the user
       
       System.out.println("\n All customers:");
       myShop.printAllCustomers(); // prints all the customers loaded
       
       //Auto-generate reservations
       System.out.println("\nMaking reservations:");
       //get the lists of customers and items from Shop using getters
       List<Customer> customers = myShop.getCustomerList();
       List<ShopItem> items = myShop.getItemList();
       //determine the number of reservations we can safely make
       int reservationsNeededToMake = Math.min(customers.size(), items.size());
       
       //loop to create reservations automatically
       for (int i = 0; i < reservationsNeededToMake; i++)
       {
           Customer customer = customers.get(i);
           ShopItem item = items.get(i);
           
           String date = "21-03-2026";
           int days = i + 1;
           
           // make the reservation using the customer ID and item code
    
           myShop.makeItemReservation(customer.getCustomerID(), item.getItemCode(), date, days);
           
       }
       
       //print all the reservations
       System.out.println("\nAll Reservations:");
       myShop.printItemReservations();
       
       //test reservation conflict
       System.out.println("\nTesting Reservation Conflict:");
       
       //make a reservation that conflicts with the first one that we made
       //this tests if the shop correctly prevents any double booking
       if (!customers.isEmpty() && !items.isEmpty())
       {
           Customer firstCustomer = customers.get(0);
           ShopItem firstItem = items.get(0);
           
           myShop.makeItemReservation(firstCustomer.getCustomerID(), 
           firstItem.getItemCode(), "21-03-2026", 2);
       }
       
       //save/write data to files
       System.out.println("\nWriting data to files : ");
       myShop.writeCustomerData();//save/write customers to "customer_output.txt"
       myShop.writeItemReservationData();// save reservations to "reservation_output.text"
       
       //testing to delete existing reservations
       System.out.println("\nDeleting reservation 000001");
       myShop.deleteItemReservation("000001"); //input an already existing reservation
       myShop.printItemReservations();
        
       //finishes the program
       System.out.println("\nProgram has finished.");
      
       
   }
    
    
}
