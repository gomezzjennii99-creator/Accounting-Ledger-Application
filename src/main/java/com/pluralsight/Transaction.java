package com.pluralsight;

/* LocalDate and LocalTime must be imported because
they are part of Java's Package */
import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

    //add fields
    //LocalDate stores dates as, 2026-11-03
    //LocalTime stores a time as, 10:58:00
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;


    //Create a constructor
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount){
        //Use the "this" keyword       Explanation:
        /*  this.name - the name stored in the Transaction object
               name   - the name passed into the constructor
                 =    - assigns the value on the right to the variable on the left */
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }

    //Getter - returns the value of a private field
    //Setter - updates the amount stored in the object


    /* Understand getter
    public: other classes can use this method
    String: The method returns text
    getName: The method's name
    return name;: Sends back the employee's name
     */
    public LocalDate getDate() {
        return date;
    }

    /* understand setter
    public: other classes can use this method
    void: the method doesn't return a value
    setName(): receives the new name
    this.name = name; saves the new name to the employee object
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
