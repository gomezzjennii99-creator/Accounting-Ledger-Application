package com.pluralsight;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* util = tools
io = files
times = dates or times */

/* File reader - opens transactions.csv file
BufferedReader - reads the file one line at a time
IOException - handles errors if java can't read the file
 */

 /* Ledger - All entries should show the newest entries first
        - A) All - Display all entries
        - D) Deposits - Display only the entries that are deposits into the account
        - P) Payments - Display only the negative entries (or payments)
        - R) Reports - A new screen that allows the user to run pre-defined reports or
             to run a custom search
            ▪ 1) Month To Date
            ▪ 2) Previous Month
            ▪ 3) Year To Date
            ▪ 4) Previous Year
            ▪ 5) Search by Vendor - prompt the user for the vendor name and
                display all entries for that vendor
             ▪ 0) Back - go back to the Ledger page
        - H) Home - go back to the home page
     */

public class Ledger {

    // ArrayList - list to hold all transactions we read from the file (outside method)
    static ArrayList<Transaction> transactions = new ArrayList<>();




    //Create a method to read my CSV file that will load transactions
    public static void loadTransactions() throws IOException{

        //open the CSV file so I can read each transaction
        BufferedReader reader = new BufferedReader( new FileReader("src/main/java/com/pluralsight/transactions.csv"));
      /*
        BufferedReader           - type of variable
        reader                   - name of variable
        =                        - assigns value to variable
        new BufferedReader()     - creates something that can read file line by line
        new FileReader()         - opens file
        transactions.csv         - file we want to open
        new                      - creates a new object
         */


        //Read the first line of CSV file (the header) "date|time|description|vendor|amount"
        String line = reader.readLine();
        /*
        String             - data type
        line               - variable name
        =                  - assigns value
        reader             - variable I created
        .readLine()      - reads one line from file
         */

        //Skip the header and start reading actual transactions
        line = reader.readLine();

        //Read all the transactions
        /*Used a while loop because it keeps looping as there's a line to read.
        Avoiding processing a line that doesn't exist
        */

        /*
        while - keep repeating
        line  - declared variable holding the line we read
        !=    - means not equal to
        null  - means there's no line left to read
         */
        while (line != null) {
            //updating its value


            //Use a String Array to split each line so we can access the different parts of a transaction
            String[] parts = line.split("\\|");
            //move to the next line
            line = reader.readLine();

            LocalDate date = LocalDate.parse(parts[0]);
            LocalTime time = LocalTime.parse(parts[1]);
            String description = parts[2];
            String vendor = parts[3];
            double amount = Double.parseDouble(parts[4]);

            // new Transaction object - use info from the CSV to create a transaction
            Transaction transaction = new Transaction(date, time, description, vendor, amount);

            //saves transaction in the list - we are going use it later
            transactions.add(transaction);






        }


    }
    //Save a new transaction to the CSV file - new method
    public static void saveTransaction(Transaction transaction) throws IOException {

        //open the CSV file so I can add a new transaction without deleting the previous ones
        BufferedWriter writer = new  BufferedWriter(new FileWriter("src/main/java/com/pluralsight/transactions.csv", true));
         /*
         BufferedWriter   - helps write information into the file
         writer           - variable name
         new FileWriter() - opens csv file for writing
         true             - tells program to add new info at the end instead of replacing what's already there
          */
        //write the transaction info into the CSV file
        writer.write( transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" +
                transaction.getVendor() + "|" +  transaction.getAmount());

        // move to the next line so each transaction has its own line
        writer.newLine();

        //Close the writer to make sure the transaction is saved
        writer.close();



    }



}
