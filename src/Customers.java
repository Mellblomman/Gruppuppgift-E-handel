package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
    private ArrayList<Customer> customerList = new ArrayList<>();
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
                System.out.println(" FEL!!! " + e.getMessage());
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
        String inputSSNForLogin = scan.nextLine();
        System.out.println("Enter password: ");
        String inputPassword = scan.nextLine();

        if (customerExistsInList(inputSSNForLogin, inputPassword)) {
            boolean run = true;
            while (run) {
                Products products = new Products();
                System.out.println("Welcome" +
                        "\n1. Shop" +
                        "\n2. Transaction History" +
                        "\n3. Logout" +
                        "\n\nChoice: ");
                String shopOrHistory = scan.next();

                switch (shopOrHistory) {

                    case "1":
                        System.out.println("You picked shop." +
                                "\nProducts: ");
                        products.printProducts();
                        break;

                    case "2":
                        System.out.println("Transaction History method");
                        break;

                    case "3":
                        System.out.println("Logging out..");
                        run = false;
                        break;
                    default:
                        System.out.println("Choose 1,2 or 3.");
                        break;
                }
            }
        }
    }
    public void printAllCustomers(){

        try {
            String filePath = "Customers.txt";
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: Customers.txt");
        }
    }

    public boolean createFileWithCustomers() {
        File file = new File(customersFileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File has been created: " + file.getName());
                return true;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when creating a txt-file: " + e.getMessage());
        }
        return false;
    }
    private boolean registerNewAccount() {
        Scanner scan = new Scanner(System.in);

        while (true) {                                 // loop to check if the user enter right Social Security Number
            System.out.println("Enter Your Social Security Number (YYYY-MM-DD-****) (this will be your username): ");
            String socialSecurityNumber = scan.next();
            if (!validSocialSecurityNumber(socialSecurityNumber)) {
                System.out.println("Invalid Social Security Number!"); //if not right Social Security Number
            }
            if (customerExistsInList(socialSecurityNumber, scan.nextLine())) {      //Checks if the account already exists on Social Security Number
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
            customerList.add(newCustomer); //adding to list

            try {
                FileOutputStream fos = new FileOutputStream(customersFileName, true); //Open a file to append to customersFileName
                PrintStream printStream = new PrintStream(fos);                               //Creating printstream to write to file

                printStream.println(newCustomer.formatedStringForFile());                     //Writing the formatted customer info to file
                fos.close();            //Closing file output stream
                printStream.close();    //Closing printstream
                return true;
            } catch (IOException e) {
                System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
            }
        }
    }
    private boolean validSocialSecurityNumber(String socialSecurityNumber) {
        if (socialSecurityNumber.length() != 15) { //Checking if Social Security Number holds 15 characters
            return false;
        }
        String[] partsSocialSecurityNumber = socialSecurityNumber.split("-"); //splits the Social Security Number into different parts with - between
        if (partsSocialSecurityNumber.length !=4) {
            return false;
        }
        try {
            int year = Integer.parseInt(partsSocialSecurityNumber[0]);              //1st part
            int month = Integer.parseInt(partsSocialSecurityNumber[1]);             //2nd part
            int day = Integer.parseInt(partsSocialSecurityNumber[2]);               //3rd part
            int lastDigits = Integer.parseInt(partsSocialSecurityNumber[3]);        //4th part

            if (year < 1900 || year > 9999 || month < 1 || month > 12               //set up to 9999 cause of last digits!
                || day < 1 || day > 31 || lastDigits < 0 || lastDigits > 9999) {
                return false;
            }
        } catch (NumberFormatException e) {                                         //Catch when trying to convert a string with improper format into a numeric value
            return false;
        }
        return true;
    }
    public boolean customerExistsInList(String socialSecurityNumber, String inputPassword) {
        for (Customer customer : customerList) {
            if (Customer.getSocialSecurityNumber().equals(socialSecurityNumber) && customer.getPassword().equals(inputPassword)) {
                return true; // Customer with matching Social Security Number found
            }
        }
        return false; // Customer not found, no matching Social Security Number
    }
}
