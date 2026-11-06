package com.pluralsight;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.YearMonth;

//Report class holds the methods for displaying and filtering reports
public class Reports {

    //This method displays the reports menu
    public static void showReportsMenu(){
        //allows report menu to read what the user types
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("""
                     ======================================================
                                     JG FINANCIAL REPORTS
                     ======================================================
                    
                         [1]   Month to Date
                         [2]   Previous Month
                         [3]   Year to Date
                         [4]   Previous Year
                         [5]   Search by Vendor
                         [0]   BACK
                    
                     ======================================================
                    :""");
            System.out.print("Select and option: ");
            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    showDateReport("Month to Date", 1);
                    break;
                case "2":
                    showDateReport("Previous Month", 2);
                    break;
                case "3":
                    showDateReport("Year to Date", 3);
                    break;
                case "4":
                    showDateReport("Previous Year", 4);
                    break;
                case "5":
                    searchByVendor(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        }//closes while loop

    }//Closes showReportsMenu() method



    }
}//closes report class
