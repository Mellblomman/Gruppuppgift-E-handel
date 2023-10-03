package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
    private ArrayList<Customer> customerList = new ArrayList<>();
    private final String customersFileName = "Customers.txt";

    public Customers() {
        if (!createFileWithCustomers()) {
            try {
                Scanner scan = new Scanner(new File(customersFileName));
                while (scan.hasNextLine()) {
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

    public void customerMenu() { //New menu after you choose Customer in start menu.
        Scanner scan = new Scanner(System.in);

        boolean runCustomerMenu = true;

        while (runCustomerMenu) {
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

            updateCustomersTextFile();
        }
    }

    private boolean validSocialSecurityNumber(String socialSecurityNumber) {
        if (socialSecurityNumber.length() != 15) { //Checking if Social Security Number holds 15 characters
            return false;
        }
        String[] partsSocialSecurityNumber = socialSecurityNumber.split("-"); //splits the Social Security Number into different parts with - between
        if (partsSocialSecurityNumber.length != 4) {
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
        } catch (
                NumberFormatException e) {                                         //Catch when trying to convert a string with improper format into a numeric value
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

    public void printAllCustomers() {

        for (int i = 0; i < this.customerList.size(); i++) {
            System.out.println((i + 1) + ". " +
                    this.customerList.get(i).getSocialSecurityNumber() + ", " +
                    this.customerList.get(i).getPassword() + ", " +
                    this.customerList.get(i).getFirstName() + ", " +
                    this.customerList.get(i).getLastName() + ", " +
                    this.customerList.get(i).getEmail());
        }
    }

    public void editCustomerInformation() {
        Scanner scan = new Scanner(System.in);

        // Display the list of customers with their index numbers
        printAllCustomers();

        // Prompt the user to select a customer to edit
        System.out.print("Enter the index of the customer you want to edit: ");
        int customerIndex = scan.nextInt();

        // Check if the provided index is valid
        if (customerIndex >= 1 && customerIndex <= customerList.size()) {
            Customer customerToEdit = customerList.get(customerIndex - 1);

            // Prompt the user to choose which information to edit
            System.out.println("\nEdit Customer Information for " + customerToEdit.getFirstName() + " " + customerToEdit.getLastName() + ":");
            System.out.println("\n1. Social security number (Current: " + customerToEdit.getSocialSecurityNumber() + ")");
            System.out.println("2. Password (Current: " + customerToEdit.getPassword() + ")");
            System.out.println("3. First name (Current: " + customerToEdit.getFirstName() + ")");
            System.out.println("4. Last name (Current: " + customerToEdit.getLastName() + ")");
            System.out.println("5. Email (Current: " + customerToEdit.getEmail() + ")");
            System.out.print("\nEnter the number of the information to edit: ");
            String infoChoice = scan.nextLine();

            // Prompt the user for the updated value based on their choice
            String newValue = "";
            switch (infoChoice) {
                case "1":
                    System.out.print("Enter new social security number: ");
                    newValue = scan.next();
                    customerToEdit.setSocialSecurityNumber(newValue);
                    break;
                case "2":
                    System.out.print("Enter new password: ");
                    newValue = scan.next();
                    customerToEdit.setPassword(newValue);
                    break;
                case "3":
                    System.out.print("Enter new first name: ");
                    newValue = scan.next();
                    customerToEdit.setFirstName(newValue);
                    break;
                case "4":
                    System.out.print("Enter new last name: ");
                    newValue = scan.next();
                    customerToEdit.setLastName(newValue);
                    break;
                case "5":
                    System.out.print("Enter new email: ");
                    newValue = scan.next();
                    customerToEdit.setEmail(newValue);
                    break;

                default:
                    System.out.println("Invalid choice. No changes made.");
            }

            if (!newValue.isEmpty()) {
                System.out.println("Customer information updated.");
            }
        } else {
            System.out.println("Invalid customer index. Please enter a valid index.");
        }


    }

    public void updateCustomersTextFile(){
        try (PrintStream printStream = new PrintStream(new FileOutputStream(customersFileName))) {
            for (Customer customer : customerList) {
                // Format customer data as a string and write it to the file
                String customerData = customer.formatedStringForFile();
                printStream.println(customerData);
            }
        } catch(IOException e) {
            System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
        }
    }
}
