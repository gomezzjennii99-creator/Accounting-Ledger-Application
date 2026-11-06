
package com.pluralsight;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

/* Report class holds all the methods for my reports menu
 and filers transactions by date or vendor*/
public class Reports {

    //method that displays the  report menu
    public static void showReportsMenu() {
        //Scanner allows the user to type an option
        Scanner scanner = new Scanner(System.in);


        // i used a while loop so the user can keep choosing reports until they go back
        while (true) {
            System.out.println("""
                    ==========================================
                              JG FINANCIAL REPORTS
                    ==========================================
                    [1] Month to Date
                    [2] Previous Month
                    [3] Year to Date
                    [4] Previous Year
                    [5] Search by Vendor
                    [0] Back
                    ==========================================
                    """);

            System.out.print("Select an option: ");
            //scanner reads the user's selection
            String choice = scanner.nextLine().trim();

            //switch statement checks which report the user want see/use and runs it
            switch (choice) {
                case "1":
                    //showDateReport is method name - month to date is the argument
                    showDateReport("Month to Date");

                    break;
                case "2":
                    showDateReport("Previous Month");
                    break;
                case "3":
                    showDateReport("Year to Date");
                    break;
                case "4":
                    showDateReport("Previous Year");
                    break;
                case "5":
                    searchByVendor(scanner);
                    break;
                case "0":
                    return; // Takes us back to the ledger menu
                default:
                    System.out.println("Please choose a valid option.");
            }
        }
    }

    // Im using one method to find all four different reports within what date it ranges
    // also calculating deposits, payments, and total
    public static void showDateReport(String reportType) {
    //string reportType - receives the mane of the selected report

        //gets today's date
        LocalDate today = LocalDate.now();
        //holds beginning date range
        LocalDate startDate;
        //holds end of date range
        LocalDate endDate;

        switch (reportType) {

            case //this month's report
                    "Month to Date":
                startDate = today.withDayOfMonth(1);
                endDate = today;
                break;

            case //last month's report
                /*
                YearMonth	        The data type. It stores a month and year.
                lastMonth	        The variable name where we're saving the result.
                        =	        Assigns the result to lastMonth.
            YearMonth.from(today)	Takes today's date and gets just the month and year.
                  .minusMonths(1)  	Subtracts one month.
                 */
                "Previous Month":
                YearMonth lastMonth = YearMonth.from(today).minusMonths(1);
                startDate = lastMonth.atDay(1);
                endDate = lastMonth.atEndOfMonth();
                break;

            case //this year's report
                    "Year to Date":
                //gets transaction from Jan 1 of this up to this year
                startDate = today.withDayOfYear(1);
                endDate = today;
                break;

            case //last rear's report
                    "Previous Year":
                startDate = today.minusYears(1).withDayOfYear(1);
                endDate = startDate.withDayOfYear(startDate.lengthOfYear());
                /*
                startDate.lengthOfYear() → Finds how many days are in 2025: 365
                startDate.withDayOfYear(365) → Changes January 1 to the 365th day of the year: December 31
                 endDate = → Stores December 31, 2025
                 */

                break;

            default:
                System.out.println("Report not found.");
                return;
        }
        //makes report name all capital
        System.out.println("\n========== " + reportType.toUpperCase() + " ==========");
        System.out.println("From " + startDate + " to " + endDate);

        // totals help us see how much came in and how much went out
        //havent added any  payments
        double deposits = 0;
        double payments = 0;
        //not found matches
        boolean found = false;

        /*
        Transaction	          The class/type of object we're working with
        transaction        	   Variable representing one transaction at a time
               :	           Means "from" or "in" in this loop
      Ledger.transactions	   The ArrayList containing all transactions
      transaction.getDate()	   Gets the date of the current transaction
         LocalDate             date	Stores that date in a variable
         */

        // for-each loop to go through all the transactions in my ledger one at a time and get each transaction's date
        for (Transaction transaction : Ledger.transactions) {
            LocalDate date = transaction.getDate();

            // Only show transactions that fall within the dates we picked
            /*
            date.isBefore(startDate)	Checks if the transaction happened before the starting date
                                   !	Means NOT, reversing true and false
                                  &&	Means AND; both conditions must be true
                  date.isAfter(endDate)	Checks if the transaction happened after the ending date
   Ledger.printTransaction(transaction)	Calls my Ledger method to display that transaction
                         found = true	Records that at least one matching transaction was found
             */

            //Only display a transaction if its date is on or after the starting date AND on or before the ending date
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                Ledger.printTransaction(transaction);
                found = true;

                // Check if the transaction is a deposit or payment
                    // and add the amount to the correct total
                if (transaction.getAmount() > 0) {
                    deposits += transaction.getAmount();
                } else if (transaction.getAmount() < 0) {
                    payments += transaction.getAmount();
                }
            }
        }

        if (!found) {
            System.out.println("No transactions found for this period.");
        }

        System.out.printf("Total Deposits: $%.2f%n", deposits);
        System.out.printf("Total Payments: $%.2f%n", payments);
        System.out.printf("Net Total: $%.2f%n", deposits + payments);
    }

    // method searchByVendor() looks for/ finds transactions matching a vendor name
    public static void searchByVendor(Scanner scanner) {
        // Ask the user which vendor they want to search for
        System.out.print("\nEnter vendor name: ");
        // Read their answer and remove extra space
        String vendorName = scanner.nextLine().trim();

        // If the user didn't enter a name, stop the search
        if (vendorName.isEmpty()) {
            System.out.println("Please enter a vendor name.");
            return;
        }
        // Keep track of whether we find a matching transaction
        boolean found = false;

        System.out.println("\n========== VENDOR SEARCH ==========");

        // Go through every transaction in my ledger one at a time
        for (Transaction transaction : Ledger.transactions) {

            // Get the transaction's vendor and compare it to what the user typed
            // toLowerCase() ignores capitalization
            // contains() checks if the vendor includes the text they entered
            if (transaction.getVendor().toLowerCase()
                    .contains(vendorName.toLowerCase())) {

                // Display the matching transaction
                Ledger.printTransaction(transaction);
                found = true;
            }
        }
        // If no transactions matched, let the user know
        if (!found) {
            System.out.println("No transactions found for " + vendorName);
        }
    }
}


