package com.pluralsight;

/* util = tools
io = files
times = dates or times */

import java.util.Scanner;
import java.util.ArrayList;

 /* (Instructions)
 Ledger - All entries should show the newest entries first
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

    // Created an ArrayList - list to hold all transactions we read from the file (outside method)
    static ArrayList<Transaction> transactions = new ArrayList<>();


    public static void showLedgerMenu() {
        while (true) {

            System.out.println("""
                    
                    ======================================================
                                       LEDGER MENU
                    ======================================================
                    
                        [A]   ALL
                        [D]   DEPOSITS
                        [P]   PAYMENTS
                        [R]   REPORTS
                        [H]   HOME
                    
                    ------------------------------------------------------
                    Select an option:""");

            Scanner scanner = new Scanner(System.in);

            String choice = scanner.nextLine().trim().toUpperCase();

            //switch statement tp handle the users choice
            switch (choice) {
                case "A":
                    //Show all transactions
                    //Show how many transactions were loaded from the csv using size()
                    System.out.println("Transactions loaded: " + Ledger.transactions.size());

                    //add headings to make the ledger easier to read for my capstone
                    System.out.printf("%-12s %-8s %-25s %-18s %-10s%n", "DATE", "TIME", "DESCRIPTION", "VENDOR", "AMOUNT");
                    System.out.println("-".repeat(80));

                    //Use the saved transactions and display each one
                    //for each loop goes through each transaction object
                    for (Transaction transaction : Ledger.transactions) {
                        System.out.printf("%-12s %-8s %-25s %-18s %10.2f%n", transaction.getDate(), transaction.getTime(),
                                transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                    }
                    break;

                case "D":
                    //Show deposits (Positive amounts)
                    //is the get Amount() is greater than zero
                    for (Transaction transaction : Ledger.transactions) {
                        if (transaction.getAmount() > 0) {
                            printTransaction(transaction);
                        }
                    }
                    break;

                case "P":
                    //Show payments
                    //is the get Amount() is less than zero
                    for (Transaction transaction : Ledger.transactions) {
                        if (transaction.getAmount() < 0) {
                            printTransaction(transaction);
                        }
                    }
                    break;

                case "R":
                    //Show reports method
                    Reports.showReportsMenu();
                    break;
                case "H":
                    //Return back home
                    return;
                default:
                    System.out.println("Invalid option.Please try again");
            }//closes the switch

        }//closes the while loop

    }//closes showLedgerMenu()

    //method i can print my transactions and re-use it !
    public static void printTransaction (Transaction transaction){
        System.out.printf("%-12s %-8s %-25s %-18s %10.2f%n", transaction.getDate(), transaction.getTime(),
                transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

    }


}//Closes Ledger class







