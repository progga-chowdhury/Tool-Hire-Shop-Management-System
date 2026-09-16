[Tool Hire Shop Management System]

A java application for managing a tool/equipment hire shop, 
including inventory, customers, and reservations, built as 
a first year university coursework project.

[Purpose]

The system models a shop that hires out tools and equipment
(hand tools, electric tools, workwear, perishables) to customers.
It supports loading data from files, creating reservations
while checking for date conflicts, and saving data back out between sessions.

[Features]

- Inventory management for multiple item types such as: HandTool,
ElectricTool, Workwear, Perishable, modelleed through an abstract 
ShopItem base class extended by Tool and Accessory.

- Customer records, each assigned a unique auto-generated ID.
- Reservations that check equipment availability across a date range
and prevent double-booking, using a diary/date-tracking module to
track bookings per day.
- File I/O: tool, customer, and reservation data can be loaded
from text files which are selected via a file picker dialog and 
written back out to persist between sessions.
- Fast lookups by IDs using HashMap, with HashSet used to 
guarantee unique generated IDs.

[Class Structure]

ShopItem (abstract)
    Tool (abstract)
        HandTool
        ElectricTool 
    Accessory(abstract)
        Workwear
        Perishable

Shop - centeral controller, manages items, customers, reservtions, 
file I/O.
Customer - customer details (Unique ID, title, first name, surname, other initials).
ShopItemReservation - contains a single reservation (reservationNo, itemID, customerID,
startDate, noOfDays.)
Diary- tracks reservations by day, used for conflict checking.
DateUtil - date parsing/formatting helpers.
Test - demo/test class showing the system in use (See the Test.java)

[How to Run]

This project was originally developed in BlueJ, but this folder is set up to run directly
in VS Code or any Java capable IDE.

1. Open this folder in VS Code with the Java extension pack installed.
2. Open Test.java and run main() or just simply click the run button.
3. This will demonstrate the full workflow: loading tool data, loading customer data,
auto-generating sample reservations, testing a reservation conflict, writing data back to 
file, and deleting a reservation.

When the methods readToolData() or readCustomerData() run, a file picker 
window will open. Please select the correctly formatted data file. Sample data files
that you will need to test this will be provided in this folder (items_all.txt, customer_data.txt).
These have been tested and confirmed to load correctly, generate unique customer IDs, create reservations
and correctly detect any booking conflicts.

[Expected File Formats]

1. - All items data file - 
Items are grouped under a bracketed type header, then comma separated fields per line:

[ElectricTool]
- Item fields

[HandTool]
- Item fields

[Perishable]
- Item fields

[Workwear]
- Item fields


2. - [Customer data file] -
- AB-005258, Smith, Sara, C, Ms


[Known Limitations]

- No update/edit functionality. All items, customers, and 
reservations can be created, read, and deleted, but not modified once stored.

[Author]
Progga Chowdhury
