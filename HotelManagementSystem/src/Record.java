/*
 * Name: Ali Sartaz Khan
 * Course: CSc 460
 * Assignment: Project 4
 * File: Record.java
 * Instructor: Lester McCann 
 * TAs: Tanner and Aayush
 * Due Date: 05/02/2023
 * 
 * Description: Contains the record class that represents a record that contains data and can be
 * inserted, updated, or deleted from a table in the sql database
 * 
 * Operational Requirements: Java JDK 16.
 * 
 * Unimplemented Features & Bugs: N/A
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;

/*+----------------------------------------------------------------------
||
||  Class  
||
||         Author: 	Ali Sartaz Khan
||
||        Purpose:  Contains the record class that represents a record that contains data and can be
                    inserted, updated, or deleted from a table in the sql database
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
        public void setAttributes(String[] data)
        public void setRecordData(String[] data)
        public void setPrimaryKeys(String[] data)
        public String getAttributesString()
        public String getRecordDataString()
        public String getPrimaryKeysString()
        public ArrayList<String> getAttributes()
        public ArrayList<String> getRecordData()
        public ArrayList<String> getPrimaryKeys()
        public void displayPrimaryKeys()
        public void displayAttributes() 
        public void displayRecordData()

||
++-----------------------------------------------------------------------*/
public class Record {
    private ArrayList<String> attributes;
    private ArrayList<String> recordData;
    private ArrayList<String> primaryKeys;
  
    // setters
    public void setAttributes(String[] data){this.attributes = new ArrayList<>(Arrays.asList(data));}
    public void setRecordData(String[] data){this.recordData = new ArrayList<>(Arrays.asList(data));}
    public void setPrimaryKeys(String[] data){this.primaryKeys = new ArrayList<>(Arrays.asList(data));}

    // getters
    public String getAttributesString() { return String.join(",", this.attributes); }
    public String getRecordDataString() { return String.join(",", this.recordData); }
    public String getPrimaryKeysString() { return String.join(",", this.primaryKeys); }
    public ArrayList<String> getAttributes(){return this.attributes;}
    public ArrayList<String> getRecordData(){return this.recordData;}
    public ArrayList<String> getPrimaryKeys(){return this.primaryKeys;}


    /*---------------------------------------------------------------------
    |  Method displayPrimaryKeys
    |
    |  Purpose: Display all the primary keys in the record
    |
    |  Pre-condition:  primaryKeys must be initialized
    |
    |  Post-condition: None.

    |  Parameters: None.
    |      
    |  Returns:  None.
    *-------------------------------------------------------------------*/
    public void displayPrimaryKeys(){
        if (this.primaryKeys == null){
            System.out.println("No primary keys defined");
            return;
        }
        System.out.print("--- Primary Key(s): ");
        System.out.print(this.primaryKeys.get(0));
        for(int i=1; i<this.primaryKeys.size(); i++){
            System.out.print(',' + this.primaryKeys.get(i));
        }
        System.out.println();
    }

    /*---------------------------------------------------------------------
    |  Method displayAttributes
    |
       Purpose: Display all the attribute keys in the record
    |
    |  Pre-condition: attributes must be initialized
    |
    |  Post-condition: None.

    |  Parameters: None.
    |      
    |  Returns:  None.
    *-------------------------------------------------------------------*/
    public void displayAttributes() {
        if (attributes == null)
            return;
        System.out.print("--- Entity Attributes: ");
        System.out.print(this.attributes.get(0));
        for(int i=1; i<this.attributes.size(); i++){
            System.out.print(',' + this.attributes.get(i));
        }
        System.out.println();
    }

    /*---------------------------------------------------------------------
    |  Method displayDataRecord
    |
       Purpose: Display all the data values in the record
    |
    |  Pre-condition: recordData must be initialized
    |
    |  Post-condition: None.

    |  Parameters: None.
    |      
    |  Returns:  None.
    *-------------------------------------------------------------------*/
    public void displayDataRecord(){
        if (recordData == null)
            return;
        System.out.print("--- List of Data in record: ");
        System.out.print(this.recordData.get(0));
        for(int i=1; i<this.recordData.size(); i++){
            System.out.print(',' + this.recordData.get(i));
        }
        System.out.println();
    }





}