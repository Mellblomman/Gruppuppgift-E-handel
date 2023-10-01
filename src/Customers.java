package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private final String customersFileName = "Customers.txt";



    public Customers() {

    }
    public void customerMenu(){ //New menu after you choose Customer in start menu.
        Scanner scan = new Scanner(System.in);

        boolean runCustomerMenu = true;

        while(runCustomerMenu) {
            System.out.println("1. Login" +
                    "\n2. Register new account" +
                    "\n3. Go back.");
            String loginOrRegisterChoice = scan.next();

            switch (loginOrRegisterChoice) {

                case "1":
                    logInCustomer();
                    runCustomerMenu = false;
                    break;

                case "2":
                    registerNewAccount();

                    break;

                case "3":
                    System.out.println("Going back to menu");
                    runCustomerMenu = false;
                    break;

                default:
                    System.out.println("Choose 1,2 or 3");
                    break;
            }
        }
    }


    public void logInCustomer() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your social security number:");
        String inputSSNForLogin = scan.next();

        System.out.println("Enter your password:");
        String inputCustomerPasswordForLogin = scan.next();

        if (customerExists(inputSSNForLogin, inputCustomerPasswordForLogin)) {
            System.out.println("Welcome");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    public boolean customerExists(String ssn, String password) {
        if (customerList.isEmpty()) {
            readCustomersFromFile(); // Load customers from file if not already loaded
        }
        for (Customer customer : customerList) {
            if (customer.getSocialSecurityNumber().equals(ssn) && customer.getPassword().equals(password)) {
                return true; // Customer with matching SSN and password found
            }
        }
        return false; // Customer not found
    }

    public boolean createFileWithCustomers() {
        File file = new File(customersFileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File has been created: " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists!");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when creating a txt-file: " + e.getMessage());
        }
        return true;
    }

    public void readCustomersFromFile() {
        try {
            Scanner scan = new Scanner(new File(customersFileName));
            while (scan.hasNextLine()) {
                String customerData = scan.nextLine();
                String[] customerInfo = customerData.split(",");

                if (customerInfo.length == 5) {
                    Customer tempCustomer = new Customer(
                            customerInfo[0],
                            customerInfo[1],
                            customerInfo[2],
                            customerInfo[3],
                            customerInfo[4]
                    );
                    customerList.add(tempCustomer);
                } else {
                    System.out.println("Invalid customer data in file: " + customerData);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    private boolean registerNewAccount() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Your Social Security Number (this will be your username): ");
        String socialSecurityNumber = scan.next();
        System.out.println("Enter Password: ");
        String password = scan.next();
        System.out.println("Enter First Name: ");
        String firstName = scan.next();
        System.out.println("Enter Last Name: ");
        String lastName = scan.next();
        System.out.println("Enter Your Email: ");
        String email = scan.next();

        Customer newCustomer = new Customer(socialSecurityNumber, password, firstName, lastName, email); //Creating new Customer
        customerList.add(newCustomer);                                                                   //Adding to list

        try {
            FileOutputStream fos = new FileOutputStream(customersFileName, true); //Open a file to append to customersFileName
            PrintStream printStream = new PrintStream(fos);                               //Creating printstream to write to file

            printStream.println(newCustomer.formatedStringForFile());                     //Writing the formated customer info to file

            fos.close();            //Closing file output stream
            printStream.close();    //Closing printstream
            return true;
        } catch (IOException e) {
            System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
        }
        return false;               //If registration doesn't work, it will return false
    }
}
