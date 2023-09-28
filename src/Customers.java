package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
    private ArrayList<Customer> customerList = new ArrayList<Customer>();
    private String customerFileName = "Customers.txt";

    Customers() {

    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }
    public void logInCustomer(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your social security number:");
        String inputSSNForLogin = scan.next();

        if(getCustomerList().contains(inputSSNForLogin)) {
            System.out.println("Enter your password");

        }else{
            System.out.println("Wrong social security number.");}

        String inputCustomerPasswordForLogin = scan.next();
        if(getCustomerList().contains(inputCustomerPasswordForLogin)){
            System.out.println("Welcome");
        } else{
            System.out.println("Wrong password!");
        }
    }
    public void verifyLogin()  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Social Security Number: ");
        String socialSecurityInput = scanner.nextLine();
        System.out.println("Password: ");
        String passwordInput = scanner.nextLine();


        String tempSocialSecurityNumber = ""; // used to temporarily store values read from the file during the comparison.
        String tempPassword = "";             //
        String file = "Customers.txt"; // Where the customer information is stored

        try {

            Scanner scan = new Scanner(new File(file)); //Reads data from the Customers.txt file
            scan.useDelimiter(","); // Delimiter set to ',' assuming the file stores information in the format  "SocialSecurityNumber,Password."
            boolean found = false;
            while (scan.hasNextLine() && !found) {
                tempSocialSecurityNumber = scan.next();
                tempPassword = scan.next();

                if (tempSocialSecurityNumber.trim().equals(socialSecurityInput.trim()) && tempPassword.trim().equals(passwordInput.trim())) { // Trim removes spaces.
                    found = true;
                    System.out.println("Login successful!"); // If a match is found, it sets found to true and prints "Login successful!"
                }
            }

            if (!found) {
                System.out.println("Login failed. Invalid credentials.");
            }

            scan.close();
        } catch (Exception e) {
            System.out.println("Wrong");
            // Handle exceptions here
        }
    }

    boolean createFileWithCustomers() {
        File file = new File("Customers.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File have been created: " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists!");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when creating a txt-file!");
        }
        return true;
    }

    private void readCustomersFromFile() {
        try {
            Scanner scan = new Scanner(new File(customerFileName));
            while (scan.hasNextLine()) { //if file already exist, a Scanner will read every line of the file and seperate with ", "
                String Customer = scan.nextLine();
                String[] customerInfo = Customer.split(", ");

                Customer tempCustomer = new Customer( //creating a customer object, with split values and this object will be added to customer list
                        Integer.parseInt(customerInfo[0]),
                        customerInfo[1],
                        customerInfo[2],
                        customerInfo[3],
                        customerInfo[4]
                );
                customerList.add(tempCustomer); //added to customerList
            }
        } catch (FileNotFoundException e) {
            System.out.println("Wrong!" + e.getMessage());
        }
    }
    private boolean addCustomerToTextFile(Customer newCustomer)  {

        try{
            FileOutputStream fos = new FileOutputStream(this.customerFileName, true); //fos created to write data to the file customerFileName
            PrintStream printStream = new PrintStream(fos); //append: true indicates that data should be appended to the file if it already exists

            printStream.print("\n" + newCustomer.formatedStringForFile()); //print the customers information to the file
            System.out.println(newCustomer.formatedStringForFile());

            fos.close();
            printStream.close();
            return true;                    //if it works without exceptions it will be true, else false!
        } catch (Exception e){
            System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
        }

        return false;
    }
}


