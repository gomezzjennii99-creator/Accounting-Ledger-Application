package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
        /* HomeScreen Should give the user the following options:
            App should continue to run until user chooses to exit

            - D) Add Deposit - Prompt user for the deposit information
            save it to the CSV file
            - P) Make Payment (Debit) -  Prompt the user for the debit information
            and save it to the CSV file
            ▪ L) Ledger - display the ledger screen
            ▪ X) Exit - exit the application
       */


        //Create a scanner that is able to read user input
        Scanner scanner = new Scanner(System.in);


            //Display home Scree,
            System.out.println("""
                    ======================================================
                                        JG FINANCIAL
                                    YOUR PERSONAL FINANCE HUB
                    ======================================================
                                WELCOME BACK TO JG FINANCIAL :)!
                                WHERE DREAMS ARE MADE POSSIBLE.
                    
                    --------------------- MAIN MENU ----------------------
                        [D]     ADD DEPOSIT
                        [P]     MAKE PAYMENT
                        [L]     VIEW LEDGER AND REPORTS
                        [X]     EXIT
                    
                    ------------------------------------------------------
                    Select an option:\s""");


         /* I used a while loop because I want the app/menu to continue running
        and the user can keep choosing options until they choose to exit */
        while (true) {

            //This takes what the user types and save it as their choice
            //removes extra spaces at the beginning and end of what the user types
            String choice = scanner.nextLine().trim();

            //made while loop option case-insensitive
            if (choice.equalsIgnoreCase("D")) {
                System.out.println("ADD DEPOSIT SELECTED");
            } else if (choice.equalsIgnoreCase("P")) {
                System.out.println("MAKE PAYMENT SELECTED");
            } else if (choice.equalsIgnoreCase("L")) {
                System.out.println("VIEW LEDGER AND REPORTS SELECTED");
            } else if (choice.equalsIgnoreCase("X")) {
                System.out.println("Thank you for using JG Financial! ");
                break;
            }
            //made it user-friendly
            else {
                System.out.println("Invalid option. Please try again  ");
            }

        }


    }

}


















