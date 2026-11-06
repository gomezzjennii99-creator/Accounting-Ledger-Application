package com.pluralsight;

import java.io.*;
import java.util.Comparator;

import java.time.LocalDate;
import java.time.LocalTime;


import java.io.FileWriter;
import java.io.FileReader;        //File reader - opens transactions.csv file
import java.io.BufferedWriter;
import java.io.BufferedReader;    //BufferedReader - reads the file one line at a time
import java.io.IOException;       //IOException - handles errors if java can't read the file

/*  TransactionFileManger class holds the methods
for reading and saving transactions to the CSV file  */

public class TransactionFileManager {

    //Create a method to read my CSV file that will load transactions
    public static void loadTransactions() throws IOException {

        //open the CSV file so I can read each transaction
        /*
        BufferedReader           - type of variable
        reader                   - name of variable
        =                        - assigns value to variable
        new BufferedReader()     - creates something that can read file line by line
        new FileReader()         - opens file
        transactions.csv         - file we want to open
        new                      - creates a new object
         */
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/pluralsight/transactions.csv"));

        //Read the first line of CSV file (the header) "date|time|description|vendor|amount"
        /*
        String             - data type
        line               - variable name
        =                  - assigns value
        reader             - variable I created
        .readLine()      - reads one line from file
         */
        String line = reader.readLine();


        //Skip the header and start reading actual transactions
        //Read all the transactions
        line = reader.readLine();

         /*Used a while loop because it keeps looping as there's a line to read.
        Avoiding processing a line that doesn't exist */

        /*
        while - keep repeating
        line  - declared variable holding the line we read
        !=    - means not equal to
        null  - means there's no line left to read  */

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
            Ledger.transactions.add(transaction);

        }//closes while loop

        //sort transactions from newest to oldest
        Ledger.transactions.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());

    }//closes loadTransactions method


    //Save a new transaction to the CSV file - new method
    public static void saveTransaction(Transaction transaction) throws IOException {

        //open the CSV file so I can add a new transaction without deleting the previous ones
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/com/pluralsight/transactions.csv", true));
         /*
         BufferedWriter   - helps write information into the file
         writer           - variable name
         new FileWriter() - opens csv file for writing
         true             - tells program to add new info at the end instead of replacing what's already there
          */
        //write the transaction info into the CSV file
        writer.write(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" +
                transaction.getVendor() + "|" + transaction.getAmount());

        // move to the next line so each transaction has its own line
        writer.newLine();

        //Close the writer to make sure the transaction is saved
        writer.close();

    }


}
