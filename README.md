
# JG Financial - Accounting Ledger Application

## About My Project

JG Financial is a Java accounting ledger application I created for my first capstone project during the Year Up United Application Development program.

The purpose of my application is to help users keep track of their financial transactions. Users can add deposits, make payments, view their transaction history, and generate reports.

I wanted to make my application feel like a personal banking system, so I gave it its own name, welcome screen, and menu design.

## Features

### Home Screen
The home screen allows users to:
- Add a deposit
- Make a payment
- View the ledger and reports
- Exit the application

### Ledger
The ledger allows users to:
- View all transactions, with the newest first
- View deposits only
- View payments only
- Access the reports menu
- Return to the home screen

### Reports
Users can view transactions using the following reports:
- Month to Date - Transactions from the beginning of the current month to today
- Previous Month - Transactions from the entire previous month
- Year to Date - Transactions from January 1 to today
- Previous Year - Transactions from the entire previous year
- Search by Vendor - Find transactions by entering a vendor name

The date reports also display total deposits, total payments, and the net total for the selected period.

## How to Run My Application

1. Download or clone this repository from GitHub.
2. Open the project in IntelliJ IDEA.
3. Make sure Java 17 is installed and selected as the project SDK.
4. Open `HomeScreen.java` inside `src/main/java/com/pluralsight`.
5. Run the `main()` method.
6. The JG Financial main menu will appear in the console.
7. Enter the letter for the option you want to use and follow the instructions.

## How Transactions Are Saved

My application uses a CSV file called `transactions.csv` to store transaction information.

Each transaction contains:
- Date
- Time
- Description
- Vendor
- Amount

Deposits are saved as positive amounts and payments are saved as negative amounts.

When the application starts, it loads previously saved transactions from the CSV file. When a new deposit or payment is entered, the transaction is added to the ledger and saved to the file.

## Java Classes

I separated my application into different classes to keep my code organized.

- **HomeScreen.java** - Runs the main menu and handles deposits and payments.
- **Transaction.java** - Represents a transaction and stores its information.
- **Ledger.java** - Displays transactions and provides the ledger menu.
- **Reports.java** - Filters transactions by date or vendor and calculates report totals.
- **TransactionFileManager.java** - Reads transactions from and saves transactions to the CSV file.

## Technologies Used

- Java 17
- IntelliJ IDEA
- Git and GitHub
- CSV file handling

## What I Learned

This project helped me practice using Java classes, objects, methods, loops, if statements, ArrayLists, and file handling.

One of the biggest challenges for me was understanding how the different classes connect, especially when working with CSV files and generating reports.

I learned how to break a larger project into smaller parts and organize my code so each class has its own responsibility.

## Author

Jennifer Gomez

Year Up United - Application Development Pathway
