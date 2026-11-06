package com.pluralsight;

import java.io.IOException;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;

/* HomeScreen Should give the user the following options:
            App should continue to run until user chooses to exit

            - D) Add Deposit - Prompt user for the deposit information
            save it to the CSV file
            - P) Make Payment (Debit) -  Prompt the user for the debit information
            and save it to the CSV file
            ▪ L) Ledger - display the ledger screen
            ▪ X) Exit - exit the application
       */
public class HomeScreen {

    public static void main(String[] args) throws IOException {
        // Load saved transactions from the CSV before showing the menu
        Ledger.loadTransactions();

        //Create a scanner that is able to read user input
        Scanner scanner = new Scanner(System.in);





         /* I used a while loop because I want the app/menu to continue running
        and the user can keep choosing options until they choose to exit */
        while (true) {
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
                    Select an option:""");

            //This takes what the user types and save it as their choice
            //removes extra spaces at the beginning and end of what the user types
            String choice = scanner.nextLine().trim();

            //made while loop option case-insensitive
            if (choice.equalsIgnoreCase("D")) {

                System.out.println("ADD DEPOSIT SELECTED");

                //ask the user what the deposit is for
                System.out.print("Enter deposit description: ");
                String description = scanner.nextLine();
                //ask where the money is coming from
                System.out.print("Enter vendor: ");
                String vendor = scanner.nextLine();
                //ask how much money is being deposited
                System.out.print("Enter deposit amount: ");
                //math.abs() makes a negative number positive
                double amount = Math.abs(Double.parseDouble(scanner.nextLine()));
                //automatically get the date and time of the deposit
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now().withSecond(0).withNano(0);
                //Create a transaction object for the deposit
                Transaction deposit = new Transaction(date, time, description,vendor, amount);
                //add the new deposit to the list of transactions
                Ledger.transactions.add(deposit);
                //save the deposit to the CSV file so it is still there when i reopen the program
                Ledger.saveTransaction(deposit);

                //Confirm to user Deposit made Successfully
                System.out.println("Deposit information entered successfully :)!");




            } else if (choice.equalsIgnoreCase("P")) {

                System.out.println("MAKE PAYMENT SELECTED");

                //ask the user what the payment is for
                System.out.print("Enter payment description: ");
                String description = scanner.nextLine();
                //ask where the money is coming from
                System.out.print("Enter vendor: ");
                String vendor = scanner.nextLine();
                //ask how much money is being payed
                System.out.print("Enter payment amount: ");
                double amount = - Math.abs(Double.parseDouble(scanner.nextLine()));
                //automatically get the date and time of the payment
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now().withSecond(0).withNano(0);

                //Created a transaction object for the payment
                Transaction payment = new Transaction(date, time, description, vendor, amount);
                /*
                Transaction     - is the class created in Transaction.java
                payment         - variable of our new transaction object
                =               - assigns value
                new Transaction - creates a new object
                fields          - values passed to the constructor to fill in the object's information

                 */

                //add the payment to the list of transactions (memory) so it can appear in ledger
                Ledger.transactions.add(payment);

                //save the payment to the CSV file so it stays there when I reopen the program
                Ledger.saveTransaction(payment);
                /*
                Ledger            - class where my method is created
                saveTransaction() - the method created that saves transaction to the CVS file
                payment           - the object passing into the method
                 */

                //Let user know their payment was saved successfully!
                System.out.println("Payment record successfully! :)");
                //wait for use to press enter before returning home
                System.out.println("Please Enter to return to Home menu... ");
                //pauses the program and waits for the user to press Enter
                scanner.nextLine();







            } else if (choice.equalsIgnoreCase("L")) {
                System.out.println("VIEW LEDGER AND REPORTS SELECTED");
                //Show how many transactions were loaded from the csv
                System.out.println("Transactions loaded: "+ Ledger.transactions.size());
                //add headings to make the ledger easier to read for my capstone
                System.out.printf("%-12s %-8s %-25s %-18s %-10s%n", "DATE", "TIME", "DESCRIPTION", "VENDOR", "AMOUNT");
                System.out.println("-".repeat(80));
                //Use the saved transactions and display each one
                for (Transaction transaction : Ledger.transactions){
                    System.out.printf("%-12s %-8s %-25s %-18s %10.2f%n", transaction.getDate(), transaction.getTime(),
                            transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                    ;
                }

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


















