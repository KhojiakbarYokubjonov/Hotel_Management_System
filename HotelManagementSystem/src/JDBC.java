/*
 * Name: Ali Sartaz Khan
 * Course: CSc 460
 * Assignment: Project 3
 * File: JDBC.java
 * Instructor: Lester McCann 
 * TAs: Tanner and Aayush
 * Due Date: 03/30/2023
 * 
 * Description: Program contains the JDBC object which takes a query when created and then runs that
 * query and displays the results of that query onto the console.
 * 
 * Operational Requirements: Java JDK 16. Tables must be created beforehand. To access tables,
 * you must use quotations around the table names, such as askhan1."t_1979". The JDBC class has my 
 * username and password hardcoded, if you want to change them, you have to manually type that out 
 * in that class. Also, before running this program, you must export the proper classpath specified in
 * JDBC.java: export CLASSPATH=/usr/lib/oracle/19.8/client64/lib/ojdbc8.jar:${CLASSPATH}
 * 
 * Unimplemented Features & Bugs: N/A
 * 
 */
 import java.io.*;
 import java.sql.*;                 
 
/*+----------------------------------------------------------------------
||
||  Class JDBC 
||
||         Author: 	Ali Sartaz Khan
||
||        Purpose:  An object that connects to the JDBC and runs a query and displays the outputs 
                    of that query
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
||   Constructors:  public JDBC(String query)
||
||  Class Methods:  None.
||
||  Inst. Methods:  private void displayResults(ResultSet answer)
||
++-----------------------------------------------------------------------*/
 public class JDBC
 {

    /*---------------------------------------------------------------------
    |  Method JDBC
    |
    |  Purpose: Connect to the JDBC and run query and display results
    |
    |  Pre-condition:  Valid query string as argument
    |
    |  Post-condition: None.

    |  Parameters:
        query - valid string containing sql query
    |      
    |  Returns:  None.
    *-------------------------------------------------------------------*/
    public JDBC(String query, String username, String password) throws SQLException, ClassNotFoundException
    {
        final String oracleURL = "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";

        // load the (Oracle) JDBC driver by initializing its base
        // class, 'oracle.jdbc.OracleDriver'.
        Class.forName("oracle.jdbc.OracleDriver");


        // make and return a database connection to the user's
        // Oracle database
        Connection dbconn = null;
        dbconn = DriverManager.getConnection(oracleURL,username,password);

        // Send the query to the DBMS, and get and display the results
        Statement stmt = null;
        ResultSet answer = null;
        // System.out.println("\nThe results of the query [" + query 
        // + "] are:\n");
        
        stmt = dbconn.createStatement();

        if(query.split(" ")[0].equals("CREATE")) {
            System.out.println("CREATING TABLES!!");
            stmt.executeUpdate(query);
        }
        else if (query.split(" ")[0].equals("DROP")) {
            System.out.println("DROPPING TABLES!!");
            stmt.executeUpdate(query);
        }
        else if(query.split(" ")[0].equals("INSERT")){
            System.out.println("INSERTING RECORD INTO TABLE!");
            stmt.executeUpdate(query);
        }
        else if(query.split(" ")[0].equals("DELETE")){
            System.out.println("DELETING RECORD FROM TABLE!");
            stmt.executeUpdate(query);
        }
        else if(query.split(" ")[0].equals("UPDATE")){
            System.out.println("UPDATING RECORD INTO TABLE!");
            stmt.executeUpdate(query);
        }
        else if(query.split(" ")[0].equals("GRANT")){
            System.out.println("GRANTING!");
            stmt.executeUpdate(query);
        }
        else {
            System.out.println("RAN QUERY other than CREATE/INSERT/DELETE/UPDATE/DROP/GRANT!");
    
            answer = stmt.executeQuery(query);
    
            if (answer != null) {
                displayResults(answer);
                answer.close(); // close the ResultSet object before closing the Statement object
            }
        }
    
        System.out.println();
    
        //  Shut down the connection to the DBMS.
        stmt.close();
        dbconn.close();
    
    }

    /*---------------------------------------------------------------------
    |  Method displayResults
    |
    |  Purpose: Display the results of an executed result query.
    |
    |  Pre-condition:  Valid anwer result set
    |
    |  Post-condition: None.

    |  Parameters:
        answer - ResultSet for query that has been executed
    |      
    |  Returns:  None.
    *-------------------------------------------------------------------*/
    private void displayResults(ResultSet answer) throws SQLException{
        // Get the data about the query result to learn
        // the attribute names and use them as column headers

        ResultSetMetaData answermetadata = answer.getMetaData();

        // Print column headers
        for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
            String colName = answermetadata.getColumnName(i);
            int colWidth = Math.max(colName.length(), 25); // minimum width of 15 characters
            System.out.printf("%-" + colWidth + "s", colName);
        }
        System.out.println();

        // Print rows
        while (answer.next()) {
            for (int i = 1; i <= answermetadata.getColumnCount(); i++) {
                Object obj = answer.getObject(i);
                String colValue = "";
                if (obj instanceof String) {
                    colValue = (String) obj;
                } 
                else if (obj == null) {
                    colValue = "NULL";
                } 
                else if (obj instanceof Timestamp){
                    colValue = obj.toString().substring(0, 10);
                }
                else {
                    colValue = obj.toString();
                }
                int colWidth = Math.max(colValue.length(), 25); // minimum width of 15 characters
                System.out.printf("%-" + colWidth + "s", colValue);
            }
            System.out.println();
        }
    }
}