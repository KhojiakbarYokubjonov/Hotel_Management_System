/*
 * Name: Ali Sartaz Khan, Khojiakbar Yokubjonov, Taoseef Aziz, Amimul Ehsan Zoha
 * Course: CSc 460
 * Assignment: Project 4
 * File: Prog04.java
 * Instructor: Lester McCann 
 * TAs: Tanner and Aayush
 * Due Date: 05/02/2023
 * 
 * Description: Contains the user interface for update/deletion/insertion of queries in the 
 * tables.
 * 
 * Operational Requirements: Java JDK 16. All tables must already be created before running the 
 * program.
 * 
 * Unimplemented Features & Bugs: When updating a record in the Schedule table, there are times when
 * the JDBC doesn't update the record properly on oracle. However, when i copy the same querystring, that im
 * passing into JDBC to execute, and paste it into sqlpl on lectura, the record seems to update fine. Not 
 * sure why that is the case.
 * 
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// imports for csv file reading
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//imports for csv file reading
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//imports for date parsing


/*+----------------------------------------------------------------------
||
||  Class  
||
||         Author: 	Ali Sartaz Khan, Khojiakbar Yokubjonov, Taoseef Aziz, Amimul Ehsan Zoha
||
||        Purpose:  Have the User Interface for Insertion, Update, and Deletion
||
||  Inherits From:  None.
||
||     Interfaces:  None.
||
|+-----------------------------------------------------------------------
||
||      Constants:  None.
					
||
|+-----------------------------------------------------------------------
||
||   Constructors:  
||
||  Class Methods:  None.
||
||  Inst. Methods:  
    private static void queries()
    private static void queryE()
    private static void queryZ()
    private static void queryA()
    private static void queryB()
    private static void queryC()
    private static void queryD()
    private static void queryE()
    private static void recordUpdate()
    private static void recordDeletion()
    private static void recordInsertion()
    private static String columnsToUpdate()
    private static String wrapInQuotesIfNeeded(String value)
    private static String whereCondition(ArrayList<String> primaryKeyNames, String[] primaryKeyVals)
    private static boolean isValidInput(String[] data, Record record)
    public static Record setAttributes(String entity)
    public static void printOptions()
    private static void createTables()
    private static void grantTables()
    private static void dropTables()
||
++-----------------------------------------------------------------------*/
public class Prog04 {
    // add the credentials here
    private static String username = "";
    private static String passkey = "";
    public static void main(String args[]) throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);
        printOptions();
        String input = scanner.nextLine();

        while (!(input.equals("exit"))){
            if (input.equals("1"))
                recordInsertion();
            else if (input.equals("2"))
                recordDeletion();
            else if (input.equals("3"))
                recordUpdate();
            else if (input.equals("4"))
                queries();

            // code to populate the tables with data from the csv files
            else if (input.equals("100")) {
                dropTables();
                createTables();
                populateTablesWithCSV();
                grantTables();
            }            
            else 
                System.out.println("Invalid Input.");
            printOptions();
            input = scanner.nextLine();
        }
        System.out.println("End of Program.");
    }
    /*---------------------------------------------------------------------
    | Method populateTablesWithCSV
    |
    | Purpose: Populate the database tables with data from CSV files
    |
    | Pre-condition: The CSV files must be located in the current working directory
    | and have the correct format.
    |
    | Post-condition: Data is inserted into the database tables.
    |
    | Parameters: None.
    |
    | Returns: None.
    -------------------------------------------------------------------*/
    private static void populateTablesWithCSV() {
        // first we read in the data from all the 9 CSV file (tables) and return an arraylist of arrays of string values for each
        
        List<String[]> RoomTableData = readCSV("Data For The Project - Room.csv");
        populateRoomTable(RoomTableData);
        List<String[]> AmenityUsageTableData = readCSV("Data For The Project - AmenityUsage.csv");
        populateAmenityUsageTable(AmenityUsageTableData);

        List<String[]> AmenityTableData = readCSV("Data For The Project - Amenity.csv");
        populateAmenityTable(AmenityTableData);

        List<String[]> SubscriptionTableData = readCSV("Data For The Project - Subscription.csv");
        populateSubscriptionTable(SubscriptionTableData);

        List<String[]> EmployeeTableData = readCSV("Data For The Project - Employee.csv");
        populateEmployeeTable(EmployeeTableData);

        List<String[]> GuestTableData = readCSV("Data For The Project - Guest.csv");
        populateGuestTable(GuestTableData);

        List<String[]> BookingTableData = readCSV("Data For The Project - Booking.csv");
        populateBookingTable(BookingTableData);

        List<String[]> ManagerTableData = readCSV("Data For The Project - Manager.csv");
        populateManagerTable(ManagerTableData);

        List<String[]> ScheduleTableData = readCSV("Data For The Project - Schedule.csv");
        populateScheduleTable(ScheduleTableData);

        List<String[]> AmenityRatingTableData = readCSV("Data For The Project - AmenityRating.csv");
        populateAmenityRatingTable(AmenityRatingTableData);

        
    } //populateTablesWithCSV()

    /*---------------------------------------------------------------------
    | Method populateAmenityRatingTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: amenityRatingTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | amenityRatingTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/


    private static void populateAmenityRatingTable(List<String[]> amenityRatingTableData) {


        String queryString =  "INSERT INTO atak_AmenityRating VALUES(";
        for (String[] row: amenityRatingTableData){
            queryString += row[0];
            queryString += ',';
            queryString += row[1];
            queryString += ',';
            queryString += row[2];
            queryString += ',';
            queryString += row[3];
            queryString += ',';
            queryString += "TO_DATE(\'";
            queryString += row[4];
            queryString += "\',\'MM/DD/YY\'))";

            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString =  "INSERT INTO atak_AmenityRating VALUES(";
        }
    }

    /*---------------------------------------------------------------------
    | Method populateScheduleTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: scheduleTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | scheduleTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/
    private static void populateScheduleTable(List<String[]> scheduleTableData) {

        String queryString = "INSERT INTO atak_Schedule VALUES(";
        for (String[] values: scheduleTableData){
            String inputDate = values[2];
            
            // Convert input string to LocalDate object using first format
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("M/d/yy");
            LocalDate date = LocalDate.parse(inputDate, inputFormat);
            
            // Convert LocalDate object to string using second format
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String outputDate = date.format(outputFormat);
            
            queryString += values[0];
            queryString += ",";
            queryString += values[1];
            queryString += ",TO_DATE('" + values[2] + "','MM/DD/YY'), ";
            queryString += "TIMESTAMP '" + outputDate + " " + values[3];
            queryString += "',TIMESTAMP '" + outputDate + " " + values[4] + "')";
            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString = "INSERT INTO atak_Schedule VALUES(";
        }
    }
    /*---------------------------------------------------------------------
    | Method populateManagerTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: managerTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | managerTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/
    private static void populateManagerTable(List<String[]> managerTableData) {
        String queryString = "INSERT INTO atak_Manager VALUES(";

        for (String[] row: managerTableData){
            queryString += row[0];
            queryString += ',';
            queryString += row[1];
            queryString += ',';
            queryString += row[2];
            queryString += ',';
            queryString += '\'';
            queryString += row[3];
            queryString += '\'';
            queryString += ',';
            queryString += '\'';
            queryString += row[4];
            queryString += '\'';
            queryString += ')';
            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString = "INSERT INTO atak_Manager VALUES(";
        }
    }
    /*---------------------------------------------------------------------
    | Method populateBookingTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: bookingTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | bookingTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/
    private static void populateBookingTable(List<String[]> bookingTableData) {
        String queryString = "INSERT INTO atak_Booking VALUES(";
        for (String[] row: bookingTableData){
            queryString += row[0];
            queryString += ',';
            queryString += row[1];
            queryString += ',';
            queryString += row[2];
            queryString += ',';
            queryString += "TO_DATE(\'";
            queryString += row[3];
            queryString+= "\',\'MM/DD/YY\'),TO_DATE(\'";
            queryString += row[4];
            queryString += "\',\'MM/DD/YY\'))";
            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString = "INSERT INTO atak_Booking VALUES(";
        }
    }

    /*---------------------------------------------------------------------
    | Method populateGuestTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: guestTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | guestTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/


    private static void populateGuestTable(List<String[]> guestTableData) {
        String queryString = "INSERT INTO atak_Guest VALUES(";
        for (String[] row: guestTableData){
            queryString += row[0];
            queryString += ',';
            queryString += '\'';
            queryString += row[1];
            queryString += '\'';
            queryString += ',';
            queryString += row[2];
            queryString += ',';
            queryString += row[3];
            queryString += ',';
            queryString += '\'';
            queryString += row[4];
            queryString += '\'';
            queryString += ')';
            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString = "INSERT INTO atak_Guest VALUES(";
        }
    }
    /*---------------------------------------------------------------------
    | Method populateEmployeeTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: employeeTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | employeeTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/

    private static void populateEmployeeTable(List<String[]> employeeTableData) {
        //INSERT INTO atak_Employee VALUES(1,1,50000,'Jimmi','cashier');
        String queryString = "INSERT INTO atak_Employee VALUES(";
        for (String[] row: employeeTableData){
            queryString += row[0];
            queryString += ',';
            queryString += row[1];
            queryString += ',';
            queryString += row[2];
            queryString += ',';
            queryString += '\'';
            queryString += row[3];
            queryString += '\'';
            queryString += ',';
            queryString += '\'';
            queryString += row[4];
            queryString += '\'';
            queryString += ')';
            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString = "INSERT INTO atak_Employee VALUES(";
        }
    }

    /*---------------------------------------------------------------------
    | Method populateSubscriptionTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: subscriptionTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | subscriptionTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/
    private static void populateSubscriptionTable(List<String[]> subscriptionTableData) {

        String queryString = "INSERT INTO atak_Subscription VALUES(";
        for (String[] row: subscriptionTableData){
            queryString += row[0];
            queryString += ',';
            queryString += '\'';
            queryString += row[1];
            queryString += '\'';
            queryString += ',';
            queryString += row[2];
            queryString += ')';
            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString = "INSERT INTO atak_Subscription VALUES(";
        }
    }

    /*---------------------------------------------------------------------
    | Method populateAmenityTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: amenityTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | amenityTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/
    private static void populateAmenityTable(List<String[]> amenityTableData) {
        //  INSERT INTO atak_Amenity VALUES(1, 'pool', 100);

        String queryString = "INSERT INTO atak_Amenity VALUES(";

        for (String[] row: amenityTableData){
            queryString += row[0];
            queryString += ',';
            queryString += '\'';
            queryString += row[1];
            queryString += '\'';
            queryString += ',';
            queryString += row[2];
            queryString += ')';
            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString = "INSERT INTO atak_Amenity VALUES(";
        }
    }

    /*---------------------------------------------------------------------
    | Method populateAmenityUsageTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: amenityUsageTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | amenityUsageTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/
    private static void populateAmenityUsageTable(List<String[]> amenityUsageTableData) {
        //INSERT INTO atak_AmenityUsage VALUES(1,2,2);
        String queryString = "INSERT INTO atak_AmenityUsage VALUES(";
        for (String[] row: amenityUsageTableData){
            queryString += row[0];
            queryString += ',';
            queryString += row[1];
            queryString += ',';
            queryString += row[2];
            queryString += ')';
            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString = "INSERT INTO atak_AmenityUsage VALUES(";    
        }
    }
    /*---------------------------------------------------------------------
    | Method populateRoomTable
    |
    | Purpose: Populate a database table with data from a List of arrays of Strings
    |
    | Pre-condition: roomTableData must be a valid List containing arrays of Strings
    |
    | Post-condition: Data is inserted into the database table.
    |
    | Parameters:
    | roomTableData (List<String[]>) : List of arrays of Strings containing data to be inserted into the database table
    |
    | Returns: None.
    -------------------------------------------------------------------*/
    private static void populateRoomTable(List<String[]> roomTableData) {
        //    INSERT INTO atak_Room VALUES(1,300);

        String queryString = "INSERT INTO atak_Room VALUES(";
        

        for (String[] row: roomTableData){
            queryString += row[0];
            queryString += ',';
            queryString += row[1];
            queryString += ')';
            try {
                JDBC jdbc = new JDBC(queryString, username, passkey);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            queryString = "INSERT INTO atak_Room VALUES(";
        }

    }

    /*---------------------------------------------------------------------
    | Method readCSV
    |
    | Purpose: Read a CSV file and return its contents as a List of arrays of Strings
    |
    | Pre-condition: filename must be a valid CSV file containing data
    |
    | Post-condition: None.
    |
    | Parameters:
    | filename (String) : the name of the CSV file to be read
    |
    | Returns: List<String[]> : the contents of the CSV file as a List of arrays of Strings
    -------------------------------------------------------------------*/

    private static List<String[]> readCSV(String filename) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // skip metadata row
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                for (int i = 0; i < row.length; i++) {
                    row[i] = row[i].trim();
                }
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    } //readCSV()
    /*---------------------------------------------------------------------
    | Method printArrayListOfArrays
    |
    | Purpose: Display the contents of an ArrayList of arrays of Strings
    |
    | Pre-condition: arrayListOfArrays must be initialized and contain arrays of Strings
    |
    | Post-condition:Prints out the the contents of an ArrayList of arrays of Strings.
    |
    | Parameters:
    | arrayListOfArrays (List<String[]>) : ArrayList of arrays of Strings to be displayed
    |
    | Returns: None.
    -------------------------------------------------------------------*/
    public static void printArrayListOfArrays(List<String[]> arrayListOfArrays) {
        for (String[] array : arrayListOfArrays) {
            System.out.print("[ ");
            for (String element : array) {
                System.out.print(element + " ");
            }
            System.out.println("]");
        }
    }

     /*---------------------------------------------------------------------
     |  Method queries
     |
     |  Purpose: List all the queries and let the user select which query they want to execute
     |
     |  Pre-condition: None
     |
     |  Post-condition: None.
 
     |  Parameters: None
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
    private static void queries() throws NumberFormatException, ClassNotFoundException, SQLException {
        System.out.println("a. Print a current bill (total $) for a customer for their stay and all unpaid amenities.\n" + 
        "b. Given a certain date, output the customers that are currently staying at the hotel along with their room\n" +
        "   numbers. Order by room numbers and group by category of customer.\n" +
        "c. Print the schedule of staff given a week (input the start date of the week by the user). A schedule\n" +
        "   contains the list of staff members working that week and a staff member\'s working hours (start and stop times).\n" +
        "d. Print the average ratings of different amenities recorded within the range of two dates(input by the user)\n" +
        "   and sort them in descending order.\n" +
        "e. Our own query: Given an employe ID, print the schedule of the meployee's manager.\n");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("a")){
            System.out.println("Enter a guest number (as an integer)");
            input = scanner.nextLine();
            queryA(Integer.valueOf(input));
         }
         else if (input.equals("b")){
            System.out.println("Enter a date (YYYY-MM-DD)");
            input = scanner.nextLine();
            queryB(input);
         }
         else if (input.equals("c")){
            System.out.println("Enter a starting date of the week (YYYY-MM-DD)");
            input = scanner.nextLine();
            queryC(input);
         }
         else if (input.equals("d")){
            System.out.println("Enter a starting date (YYYY-MM-DD)");
            String start = scanner.nextLine();
            System.out.println("Enter an end date (YYYY-MM-DD)");
            String end = scanner.nextLine();
            queryD(start, end);
         }
         else if (input.equals("e")){
            System.out.println("Enter an employee number (as an integer)");
            input = scanner.nextLine();
            queryE(Integer.valueOf(input));
         }
         else {
            System.out.println("Invalid Query Number.");
            return;
         }
     }
 
 
     /*---------------------------------------------------------------------
     |  Method queryE
     |
     |  Purpose: Excutes query E in the query prompt
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void queryE(int employeeID) throws ClassNotFoundException, SQLException {
         System.out.println("Query E");
        String sql = "SELECT M.NAME, S.STARTTIME, S.ENDTIME " +
                "FROM TAOSEEFAZIZ.ATAK_MANAGER M " +
                "JOIN TAOSEEFAZIZ.ATAK_EMPLOYEE E ON E.MANAGERNO = M.MANAGERNO " +
                "JOIN TAOSEEFAZIZ.ATAK_SCHEDULE S ON E.EMPLOYEENO = S.EMPLOYEENO " +
                "WHERE E.EMPLOYEENO = '" + employeeID + "'";
        JDBC jdbc = new JDBC(sql, username, passkey);
     }
     
    
 
     /*---------------------------------------------------------------------
     |  Method queryD
     |
     |  Purpose: Excutes query D in the query prompt
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void queryD(String startDate, String endDate) throws ClassNotFoundException, SQLException {
        System.out.println("Query D");
        String sql = "SELECT a.name AS AMENITY, AVG(rating.score) AS RATING\n" +
            "FROM TAOSEEFAZIZ.ATAK_Amenity a, TAOSEEFAZIZ.ATAK_AmenityRating rating\n" +
            "WHERE a.amenityNo = rating.amenityNo\n" +
            "AND rating.DATERATED >= TO_DATE('" + startDate + "', 'YYYY-MM-DD')\n" +
            "AND rating.DATERATED <= TO_DATE('" + endDate + "', 'YYYY-MM-DD')\n" +
            "GROUP BY a.name\n" +
            "ORDER BY AVG(rating.score) DESC";
        JDBC jdbc = new JDBC(sql, username, passkey);

     }
 
     /*---------------------------------------------------------------------
     |  Method queryC
     |
     |  Purpose: Excutes query C in the query prompt
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void queryC(String startDate) throws ClassNotFoundException, SQLException {
         System.out.println("Query C");
         String sql = "SELECT E.NAME, S.STARTTIME, S.ENDTIME\n" +
             "FROM TAOSEEFAZIZ.ATAK_EMPLOYEE E\n" +
             "JOIN TAOSEEFAZIZ.ATAK_SCHEDULE S ON S.EMPLOYEENO = E.EMPLOYEENO\n" +
             "WHERE S.STARTTIME >= TO_DATE('" + startDate +"', 'YYYY-MM-DD')\n" +
             "AND S.ENDTIME <= (TO_DATE('" + startDate +"', 'YYYY-MM-DD') + INTERVAL '8' DAY)";
        JDBC jdbc = new JDBC(sql, username, passkey);

     }
 
     /*---------------------------------------------------------------------
     |  Method queryB
     |
     |  Purpose: Excutes query B in the query prompt
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void queryB(String date) throws ClassNotFoundException, SQLException {
         System.out.println("Query B");
         String sql = "SELECT G.NAME, R.ROOMNO\n" +
             "FROM TAOSEEFAZIZ.ATAK_BOOKING B\n" +
             "JOIN TAOSEEFAZIZ.ATAK_GUEST G ON G.GUESTNO = B.GUESTNO\n" +
             "JOIN TAOSEEFAZIZ.ATAK_ROOM R ON R.ROOMNO = B.ROOMNO\n" +
             "WHERE B.dateFrom <= TO_DATE('" + date + "', 'YYYY-MM-DD')\n" +
             "AND B.dateTo >= TO_DATE('" + date +"', 'YYYY-MM-DD')\n" +
             "GROUP BY G.SUBNO, G.NAME, R.ROOMNO\n" +
             "ORDER BY R.ROOMNO";
        JDBC jdbc = new JDBC(sql, username, passkey);

         
     }
 
     /*---------------------------------------------------------------------
     |  Method queryA
     |
     |  Purpose: Excutes query A in the query prompt
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void queryA(int guestNo) throws ClassNotFoundException, SQLException {
        System.out.println("Query A");
        String sql = "SELECT B.GUESTNO, B.BOOKINGNO, S.DISCOUNT as DISCOUNT, R.PRICE * EXTRACT(DAY FROM NUMTODSINTERVAL(DATETO - DATEFROM, 'DAY')) AS ROOM_PRICE, " +
        "SUM(A.PRICE * AU.QUANTITY) AS AMENITIES, ((100 - S.DISCOUNT)/100 * (R.PRICE * EXTRACT(DAY FROM NUMTODSINTERVAL(DATETO - DATEFROM, 'DAY')) + SUM(A.PRICE * AU.QUANTITY))) AS TOTAL_BILL " +
        "FROM TAOSEEFAZIZ.ATAK_BOOKING B " +
        "JOIN TAOSEEFAZIZ.ATAK_GUEST G ON G.GUESTNO = B.BOOKINGNO " +
        "JOIN TAOSEEFAZIZ.ATAK_SUBSCRIPTION S ON S.SUBNO = G.SUBNO " +
        "JOIN TAOSEEFAZIZ.ATAK_ROOM R ON B.ROOMNO = R.ROOMNO " +
        "LEFT JOIN TAOSEEFAZIZ.ATAK_AMENITYUSAGE AU ON B.BOOKINGNO = AU.BOOKINGNO " +
        "LEFT JOIN TAOSEEFAZIZ.ATAK_AMENITY A ON AU.AMENITYNO = A.AMENITYNO " +
        "WHERE B.GUESTNO = '" + guestNo + "'" +
        "GROUP BY B.GUESTNO, B.BOOKINGNO, S.DISCOUNT, R.PRICE * EXTRACT(DAY FROM NUMTODSINTERVAL(DATETO - DATEFROM, 'DAY'))";

        JDBC jdbc = new JDBC(sql, username, passkey);        

     }
 
     /*---------------------------------------------------------------------
     |  Method updateRecord
     |
     |  Purpose: Update a particular record of a table using the PK value(s)
     |
     |  Pre-condition:  For columns to update, if user wants to update a field that involves time,
                        they need to put the date and the time in the format "YYYY-MM-DD HH:MM:SS"
                        To update any date fields, they need to input data in this format "MM/DD/YY"
     |
     |  Post-condition: None.
 
     |  Parameters: None
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void recordUpdate() throws ClassNotFoundException, SQLException {
         // UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition
         System.out.println("Updating record");
         Scanner scanner = new Scanner(System.in);
         System.out.println("Choose a Table:");
         String table_name = scanner.nextLine();
         selectAllFromTable(table_name);

         Record record = setAttributes(table_name);
         if (record == null){System.out.println("Invalid Entity."); return;}
         record.displayAttributes();
         record.displayPrimaryKeys();
         System.out.println("List comma-separated PK value(s) for the record you want to update:");
         String[] data = (scanner.nextLine()).split(",");
         if (isValidInput(data, record)){
             String condition = whereCondition(record.getPrimaryKeys(), data);
             String columns = columnsToUpdate();
             if (columns == null){System.out.println("No Update Attributes set"); return;}
             String query = "UPDATE taoseefaziz.ATAK_"+ table_name + " SET " + columns + condition;
            //  JDBC jdbc = new JDBC(query, username, passkey);
             System.out.println("Query: " + query);
             System.out.println();
             JDBC jdbc = new JDBC(query, username, passkey);
             selectAllFromTable(table_name);
         }
     }

     private static void selectAllFromTable(String table_name) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM taoseefaziz.ATAK_"+ table_name;
        System.out.println("Query: " + query);
        JDBC jdbc = new JDBC(query, username, passkey);
        System.out.println();

     }
 
     /*---------------------------------------------------------------------
     |  Method columnsToUpdate
     |
     |  Purpose: Return a string containing all the columns and their values that need updating
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters:
     |      
     |  Returns:  Comma separated String of column names and values
     *-------------------------------------------------------------------*/
     private static String columnsToUpdate() {
         String columns = "";
         System.out.println("List comma-separated attr names and values to update (Col1,Val1,Col2,Val2...):");
         Scanner scanner = new Scanner(System.in);
         String[] data = (scanner.nextLine()).split(",");
         if (data.length != 0 && data.length % 2 != 1){
             for (int i = 0; i < data.length; i+=2){
                 String col = data[i];
                 String val;
                 if (col.toLowerCase().contains("date")){
                     val = "TO_DATE('" + data[i+1] +"', 'MM/DD/YY')";
                 }
                 else if (col.toLowerCase().contains("time")){
                     val = "TIMESTAMP '" + data[i+1] + "'";
                 }
                 else {
                     val = wrapInQuotesIfNeeded(data[i+1]);
                 }
                 if (i == 0)
                     columns += col + "=" + val;
                 else
                     columns += ", " + col + "=" + val;
             }
             return columns;
         }
         return null;
     }
 
     /*---------------------------------------------------------------------
     |  Method wrapInQuotesIfNeeded
     |
     |  Purpose: Return a string that has quotes around if it's an integer
     |
     |  Pre-condition:  None
     |
     |  Post-condition: None.
 
     |  Parameters: 
             value -- string val
     |      
     |  Returns:  String with either quotes or no quotes around it.
     *-------------------------------------------------------------------*/
     private static String wrapInQuotesIfNeeded(String value) {
         try {
             int val = Integer.parseInt(value);
             
             
         } catch (NumberFormatException e) {
             if (!(value.contains("TIMESTAMP") || value.contains("DATE")))
                 return "'" + value + "'";
         }
         return value;
     }
 
     /*---------------------------------------------------------------------
     |  Method recordDeletion
     |
     |  Purpose: Deletes a record using PK values
     |
     |  Pre-condition: None. 
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void recordDeletion() throws ClassNotFoundException, SQLException {
         System.out.println("Deleting record");
         Scanner scanner = new Scanner(System.in);
         System.out.println("Choose a Table:");
         String table_name = scanner.nextLine();
         selectAllFromTable(table_name);
         Record record = setAttributes(table_name);
         if (record == null){System.out.println("Invalid Entity."); return;}
         record.displayPrimaryKeys();
         System.out.println("List comma-separated PK value(s) for the record you want to delete:");
         String[] data = (scanner.nextLine()).split(",");
         if (isValidInput(data, record)){
             String condition = whereCondition(record.getPrimaryKeys(), data);
             String query = "DELETE FROM taoseefaziz.ATAK_"+ table_name + condition;
             // JDBC jdbc = new JDBC(query, username, passkey);
             System.out.println("Query: " + query);
             System.out.println();
             JDBC jdbc = new JDBC(query, username, passkey);
             selectAllFromTable(table_name);
         }
 
 
     }
 
 
     /*---------------------------------------------------------------------
     |  Method whereCondition
     |
     |  Purpose: Returns the string for the WHERE clause in SQL queries
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters:
             primaryKeyNames -- Arraylist of all PK names
             primaryKeyVals -- Arraylist of all PK values
 
     |  Returns:  String containing WHERE clause
     *-------------------------------------------------------------------*/
     private static String whereCondition(ArrayList<String> primaryKeyNames, String[] primaryKeyVals) {
         String condition = " WHERE ";
         condition += primaryKeyNames.get(0) + "=" + primaryKeyVals[0];
         for (int i=1; i<primaryKeyNames.size(); i++){
             condition += " AND ";
             condition += primaryKeyNames.get(i) + "=" + primaryKeyVals[i];
         }
         System.out.println(condition);
         return condition;
     }
 
 
     /*---------------------------------------------------------------------
     |  Method isValidInput
     |
     |  Purpose: Check if input by the user is valid or not
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters:
     |      data -- data inputted by the user
     |      record -- data Record
     |  Returns:  true or false.
     *-------------------------------------------------------------------*/
     private static boolean isValidInput(String[] data, Record record) {
         if (data.length != record.getPrimaryKeys().size()){
             System.out.println("Invalid number of PK values.");
             return false;
         }
         
         for (String elem: data){
             // check if elem is an integer
             try{Integer.parseInt(elem);}
             catch(NumberFormatException e){
                 System.out.println("Invalid PK value.");
                 return false;
             }
         }
 
         return true;
     }
 
     /*---------------------------------------------------------------------
     |  Method recordInsertion
     |
     | Purpose: Inserts a record using PK values
     |
     |  Pre-condition: For records to insert, if user wants to insert a field that involves time,
                        they need to put the time in the format "HH:MM:SS"
                        To update any date fields, they need to input data in this format "MM/DD/YY" 
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void recordInsertion() throws ClassNotFoundException, SQLException {
         //INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);
 
         System.out.println("Inserting record");
         Scanner scanner = new Scanner(System.in);
         System.out.println("Choose a Table:");
         String table_name = scanner.nextLine();
         selectAllFromTable(table_name);

         Record record = setAttributes(table_name);
         if (record == null){System.out.println("Invalid Entity."); return;}
         record.displayAttributes();
         System.out.println("List comma-separated attribute values:");
         String[] data = (scanner.nextLine()).split(",");
         if (data.length != record.getAttributes().size()){
             System.out.println("Incorrect number of Attributes.");
             return;
         }
         if (table_name.contains("AmenityRating")){
             data[4] = "TO_DATE('" + data[4] +"', 'MM/DD/YY')";
         }
         else if (table_name.contains("Booking")){
             data[3] = "TO_DATE('" + data[3] +"', 'MM/DD/YY')";
             data[4] = "TO_DATE('" + data[4] +"', 'MM/DD/YY')";
         }
         else if (table_name.contains("Schedule")){
             String[] lst = data[2].split("/");
             String m = lst[0], d = lst[1], y = lst[2];
             data[2] = "TO_DATE('" + data[2] +"', 'MM/DD/YY')";
             data[3] = "TIMESTAMP '20" + y + "-" + m + "-" + d + " " + data[3]  + "'";
             data[4] = "TIMESTAMP '20" + y + "-" + m + "-" + d + " " + data[4]  + "'";
         }
         record.setRecordData(data);
         record.displayDataRecord();
 
         String values = wrapInQuotesIfNeeded(record.getRecordData().get(0));
         for (int i=1; i<record.getRecordData().size(); i++) {
             values += "," + wrapInQuotesIfNeeded(record.getRecordData().get(i));
         }
         String query = "INSERT INTO taoseefaziz.ATAK_" + table_name + " (" + record.getAttributesString() + ") VALUES (" + values + ")";
         System.out.println("Query: " + query);
         System.out.println();
         JDBC jdbc = new JDBC(query, username, passkey);
         selectAllFromTable(table_name);
 
     }
 
     /*---------------------------------------------------------------------
     |  Method setAttributes
     |
     |  Purpose: Sets attributes to Record obj based on entity name
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters:
             entity -- name of table
     |      
     |  Returns:  Record object
     *-------------------------------------------------------------------*/
     public static Record setAttributes(String entity) {
         Record record = new Record();
         if (entity.contains("AmenityRating")) {
             record.setAttributes( new String[] {"ratingNo", "amenityNo", "bookingNo", "score", "dateRated"});
             record.setPrimaryKeys(new String[] {"ratingNo"});
         } else if (entity.contains("AmenityUsage")) {
             record.setAttributes( new String[] {"bookingNo", "amenityNo", "quantity"});
             record.setPrimaryKeys(new String[] {"bookingNo", "amenityNo"});
         } else if (entity.contains("Amenity")) {
             record.setAttributes( new String[] {"amenityNo", "name", "price"});
             record.setPrimaryKeys(new String[] {"amenityNo"});
         } else if (entity.contains("Room")) {
             record.setAttributes( new String[] {"roomNo", "price"});
             record.setPrimaryKeys(new String[] {"roomNo"});
         } else if (entity.contains("Booking")) {
             record.setAttributes( new String[] {"bookingNo", "guestNo", "roomNo", "dateFrom", "dateTo"});
             record.setPrimaryKeys(new String[] {"bookingNo"});
         } else if (entity.contains("Employee")) {
             record.setAttributes( new String[] {"employeeNo", "managerNo", "salary", "name", "position"});
             record.setPrimaryKeys(new String[] {"employeeNo"});
         } else if (entity.contains("Guest")) {
             record.setAttributes( new String[] {"guestNo", "name", "contact", "subNo", "paymentInfo"});
             record.setPrimaryKeys(new String[] {"guestNo"});
         } else if (entity.contains("Subscription")) {
             record.setAttributes( new String[] {"subNo", "subName", "discount"});
             record.setPrimaryKeys(new String[] {"subNo"});
         } else if (entity.contains("Schedule")) {
             record.setAttributes( new String[] {"scheduleNo", "employeeNo", "dateScheduled", "startTime", "endTime"});
             record.setPrimaryKeys(new String[] {"scheduleNo"});
         } else if (entity.contains("Manager")) {
             record.setAttributes( new String[] {"managerNo", "employeeNo", "salary", "name", "position"});
             record.setPrimaryKeys(new String[] {"managerNo"});
         } else {
             return null;
         }
     
         return record;
     }
     
 
     /*---------------------------------------------------------------------
     |  Method printOptions
     |
     |  Purpose: Print all the options of the users to select
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     public static void printOptions(){
         System.out.println("Select one of the following options: or 'exit'");
         System.out.println("1) Record Insertion");
         System.out.println("2) Record deletion");
         System.out.println("3) Record Update");
         System.out.println("4) Queries\n");
     }
 
 
 
     /*---------------------------------------------------------------------
     |  Method createTables
     |
     |  Purpose: Create all the tables using sql queries in JDBC
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void createTables(){
         
         String atak_Room = "CREATE TABLE atak_Room(roomNo INTEGER, price REAL, PRIMARY KEY (roomNo))";
         String atak_AmenityRating = "CREATE TABLE atak_AmenityRating(ratingNo INTEGER, amenityNo INTEGER, bookingNo INTEGER, score INTEGER, dateRated DATE, PRIMARY KEY (ratingNo))";
         String atak_Booking = "CREATE TABLE atak_Booking(bookingNo INTEGER, guestNo INTEGER, roomNo INTEGER, dateFrom DATE, dateTo DATE, PRIMARY KEY (bookingNo))";
         String atak_Guest = "CREATE TABLE atak_Guest(guestNo INTEGER, name VARCHAR(30), contact INTEGER, subNo INTEGER, paymentInfo VARCHAR(30), PRIMARY KEY (guestNo))";
         String atak_Amenity = "CREATE TABLE atak_Amenity(amenityNo INTEGER, name VARCHAR(30), price REAL, PRIMARY KEY (amenityNo))";
         String atak_AmenityUsage = "CREATE TABLE atak_AmenityUsage(bookingNo INTEGER, amenityNo INTEGER, quantity INTEGER, PRIMARY KEY (bookingNo,amenityNo))";
         String atak_Subscription = "CREATE TABLE atak_Subscription(subNo INTEGER, subName VARCHAR(30), discount INTEGER, PRIMARY KEY (subNo))";
         String atak_Employee = "CREATE TABLE atak_Employee(employeeNo INTEGER, managerNo INTEGER, salary REAL, name VARCHAR(30), position VARCHAR(30), PRIMARY KEY (employeeNo))";
         String atak_Schedule = "CREATE TABLE atak_Schedule(scheduleNo INTEGER, employeeNo INTEGER, dateScheduled DATE, startTime TIMESTAMP, endTime TIMESTAMP, PRIMARY KEY (scheduleNo))";
         String atak_Manager = "CREATE TABLE atak_Manager(managerNo INTEGER, employeeNo INTEGER, salary REAL, name VARCHAR(30), position VARCHAR(30), PRIMARY KEY (managerNo))";
 
         try {
             JDBC jdbc = new JDBC(atak_Subscription, username, passkey);
             jdbc = new JDBC(atak_Room, username, passkey);
             jdbc = new JDBC(atak_AmenityRating, username, passkey);
             jdbc = new JDBC(atak_Booking, username, passkey);
             jdbc = new JDBC(atak_Guest, username, passkey);
             jdbc = new JDBC(atak_Amenity, username, passkey);
             jdbc = new JDBC(atak_AmenityUsage, username, passkey);
             jdbc = new JDBC(atak_Employee, username, passkey);
             jdbc = new JDBC(atak_Schedule, username, passkey);
             jdbc = new JDBC(atak_Manager, username, passkey);
 
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
 
     }//createTables()
     
 
     /*---------------------------------------------------------------------
     |  Method grantTables
     |
     |  Purpose: Grant permissions to the tables so that others can use them
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void grantTables() {
         String atak_Room = "GRANT SELECT ON atak_Room TO PUBLIC";
         String atak_AmenityRating = "GRANT SELECT ON atak_AmenityRating TO PUBLIC";
         String atak_Booking = "GRANT SELECT ON atak_Booking TO PUBLIC";
         String atak_Guest = "GRANT SELECT ON atak_Guest TO PUBLIC";
         String atak_Amenity = "GRANT SELECT ON atak_Amenity TO PUBLIC";
         String atak_AmenityUsage = "GRANT SELECT ON atak_AmenityUsage TO PUBLIC";
         String atak_Subscription = "GRANT SELECT ON atak_Subscription TO PUBLIC";
         String atak_Employee = "GRANT SELECT ON atak_Employee TO PUBLIC";
         String atak_Schedule = "GRANT SELECT ON atak_Schedule TO PUBLIC";
         String atak_Manager = "GRANT SELECT ON atak_Manager TO PUBLIC";
         
         try {
             JDBC jdbc = new JDBC(atak_Subscription, username, passkey);
             jdbc = new JDBC(atak_Room, username, passkey);
             jdbc = new JDBC(atak_AmenityRating, username, passkey);
             jdbc = new JDBC(atak_Booking, username, passkey);
             jdbc = new JDBC(atak_Guest, username, passkey);
             jdbc = new JDBC(atak_Amenity, username, passkey);
             jdbc = new JDBC(atak_AmenityUsage, username, passkey);
             jdbc = new JDBC(atak_Employee, username, passkey);
             jdbc = new JDBC(atak_Schedule, username, passkey);
             jdbc = new JDBC(atak_Manager, username, passkey);
 
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
 
     }//grantTables()
     
 
     /*---------------------------------------------------------------------
     |  Method dropTables
     |
     |  Purpose: Drop the tables using sql queries in JDBC
     |
     |  Pre-condition:  None.
     |
     |  Post-condition: None.
 
     |  Parameters: None.
     |      
     |  Returns:  None.
     *-------------------------------------------------------------------*/
     private static void dropTables(){
         
         String atak_Room = "DROP TABLE atak_Room";
         String atak_AmenityRating = "DROP TABLE atak_AmenityRating";
         String atak_Booking = "DROP TABLE atak_Booking";
         String atak_Guest = "DROP TABLE atak_Guest";
         String atak_Amenity = "DROP TABLE atak_Amenity";
         String atak_AmenityUsage = "DROP TABLE atak_AmenityUsage";
         String atak_Subscription = "DROP TABLE atak_Subscription";
         String atak_Employee = "DROP TABLE atak_Employee";
         String atak_Schedule = "DROP TABLE atak_Schedule";
         String atak_Manager = "DROP TABLE atak_Manager";
         
         try {
             JDBC jdbc = new JDBC(atak_Schedule, username, passkey);
             jdbc = new JDBC(atak_Room, username, passkey);
             jdbc = new JDBC(atak_AmenityRating, username, passkey);
             jdbc = new JDBC(atak_Booking, username, passkey);
             jdbc = new JDBC(atak_Guest, username, passkey);
             jdbc = new JDBC(atak_Amenity, username, passkey);
             jdbc = new JDBC(atak_AmenityUsage, username, passkey);
             jdbc = new JDBC(atak_Subscription, username, passkey);
             jdbc = new JDBC(atak_Employee, username, passkey);
             jdbc = new JDBC(atak_Manager, username, passkey);
 
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         }
 
     }//dropTables()
 
 }
 
 
 

