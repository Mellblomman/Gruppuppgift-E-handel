package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
    private final ArrayList<Customer> customerList = new ArrayList<>();
    private final String customersFileName = "Customers.txt";



    public Customers() {
        if (!createFileWithCustomers()){
            try {
                Scanner scan = new Scanner(new File(customersFileName));
                while (scan.hasNextLine()){
                    String customer = scan.nextLine();
                    String[] customerInfo = customer.split(",");

                    Customer tempCustomer = new Customer(
                            customerInfo[0],
                            customerInfo[1],
                            customerInfo[2],
                            customerInfo[3],
                            customerInfo[4]
                    );
                    customerList.add(tempCustomer);
                }
            } catch (FileNotFoundException e) {
                System.out.println(" FEL!!! " + e.getMessage());;
            }

        }

    }
    public void customerMenu(){ //New menu after you choose Customer in start menu.
        Scanner scan = new Scanner(System.in);

        boolean runCustomerMenu = true;

        while(runCustomerMenu) {
            System.out.println("1. Login" +
                    "\n2. Register new account" +
                    "\n3. Go back." +
                    "\n\nChoice: ");
            String loginOrRegisterChoice = scan.next();

            switch (loginOrRegisterChoice) {

                case "1":
                    logInCustomer();

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

        if (customerExists(inputSSNForLogin)) {
            System.out.println("Welcome");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
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

    private boolean registerNewAccount() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Your Social Security Number (this will be your username): ");
        String socialSecurityNumber = scan.next();


        if(customerExists(socialSecurityNumber)) {      //Check if the account already exists on Social Security Number
            System.out.println("Account already exists! Please log in.");
            return false;
        }
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

    public boolean customerExists(String socialSecurityNumber) {
        for (Customer customer : customerList) {
            if (customer.getSocialSecurityNumber().equals(socialSecurityNumber)) {
                return true; // Customer with matching Social Security Number found
            }
        }
        return false; // Customer not found, no matching Social Security Number
    }
}
