package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private final String customersFileName = "Customers.txt";



    public Customers() {

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


    private boolean addCustomerToTextFile(Customer newCustomer) {
        try {
            FileOutputStream fos = new FileOutputStream(customersFileName, true);
            PrintStream printStream = new PrintStream(fos);

            printStream.println(newCustomer.formatedStringForFile());

            fos.close();
            printStream.close();
            return true;
        } catch (IOException e) {
            System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
        }
        return false;
    }
}
