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
}

