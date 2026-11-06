
package com.pluralsight;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

//Report class holds all the methods for the reports
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
            String choice = scanner.nextLine().trim();

            //switch statement checks which report the user want see/use
            switch (choice) {
                case "1":
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

    // four date reports use this method because they work almost the same way
    public static void showDateReport(String reportType) {
        LocalDate today = LocalDate.now();
        LocalDate startDate;
        LocalDate endDate;

        switch (reportType) {
            case "Month to Date":
                startDate = today.withDayOfMonth(1);
                endDate = today;
                break;

            case "Previous Month":
                YearMonth lastMonth = YearMonth.from(today).minusMonths(1);
                startDate = lastMonth.atDay(1);
                endDate = lastMonth.atEndOfMonth();
                break;

            case "Year to Date":
                startDate = today.withDayOfYear(1);
                endDate = today;
                break;

            case "Previous Year":
                startDate = today.minusYears(1).withDayOfYear(1);
                endDate = startDate.withDayOfYear(startDate.lengthOfYear());
                break;

            default:
                System.out.println("Report not found.");
                return;
        }

        System.out.println("\n========== " + reportType.toUpperCase() + " ==========");
        System.out.println("From " + startDate + " to " + endDate);

        // totals help us see how much came in and how much went out
        double deposits = 0;
        double payments = 0;
        boolean found = false;

        for (Transaction transaction : Ledger.transactions) {
            LocalDate date = transaction.getDate();

            // Only show transactions that fall within the dates we picked
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                Ledger.printTransaction(transaction);
                found = true;

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

    // method searchByVendor() looks for transactions from a specific vendor
    public static void searchByVendor(Scanner scanner) {
        System.out.print("\nEnter vendor name: ");
        String vendorName = scanner.nextLine().trim();

        if (vendorName.isEmpty()) {
            System.out.println("Please enter a vendor name.");
            return;
        }

        boolean found = false;

        System.out.println("\n========== VENDOR SEARCH ==========");

        for (Transaction transaction : Ledger.transactions) {

            // case-insensitive
            if (transaction.getVendor().toLowerCase()
                    .contains(vendorName.toLowerCase())) {

                Ledger.printTransaction(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for " + vendorName);
        }
    }
}


