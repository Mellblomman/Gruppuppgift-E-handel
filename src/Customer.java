package src;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    //Use socialSecurityNumber as username to log in
    private String socialSecurityNumber;  //Need to be static to make sure that this variable is owned Customer Class and not by any other class
    private String password;    //Uses to log in
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String socialSecurityNumber,String password, String firstName, String lastName, String email) {
        this.socialSecurityNumber = socialSecurityNumber; //Uses as username to log in
        this.password = password; //Uses as password to log in
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer() {
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String formattedStringForFile() {
        return this.socialSecurityNumber + "," + this.password + "," + this.firstName + "," + this.lastName + "," + this.email;
    }



    private List<Order> transactionHistory = new ArrayList<>();

    public void addTransaction(String nameOrder, String product, double totalSumOrder, double dateAndTime) {
        Order transaction = new Order(this.socialSecurityNumber, nameOrder, product, totalSumOrder, dateAndTime);
        transactionHistory.add(transaction);
    }
    public List<Order> getTransactionHistory() {
        return transactionHistory;
    }
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("You have no previous transactions.");
        } else {
            System.out.println("Transaction history for " + this.firstName + " " + this.lastName + " " + this.socialSecurityNumber + ":");
            for (Order order : transactionHistory) {
                System.out.println("Name: " + order.getNameOrder() + ", Product: " + order.getProduct() + ", Sum: " + order.getTotalSumOrder() +
                        "Date/Time: " + order.getDateAndTime());
            }
        }
    }
}

