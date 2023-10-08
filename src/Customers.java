package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
    private ArrayList<Customer> customerList = new ArrayList<>();
    private final String customersFileName = "Customers.txt";

    Orders orders = new Orders();

    public Customers() {
        readCustomersFromFile(); //(1) Starting to read customers from txt file Customers
    }
    public void readCustomersFromFile(){ //(1) Starting to read customers from txt file Customers
        if (!createFileWithCustomers()) { //(2) Check if creating a file with customers was unsuccessful
            try {
                Scanner scan = new Scanner(new File(customersFileName)); //If file already exists, it will get scanned
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

    public boolean createFileWithCustomers() { //(2) Creating a file
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

    public void customerMenu() { //(3) Customer start menu.
        Scanner scan = new Scanner(System.in);

        boolean run = true;

        while (run) {
            System.out.println("\n----------------------------------------------------" +
                    "\nCustomer Menu" +
                    "\n1. Login" +
                    "\n2. Register new account" +
                    "\n0. Go back." +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");
            String loginOrRegister = scan.next();

            switch (loginOrRegister) {

                case "1":
                    logInCustomer(); //(5)
                    break;

                case "2":
                    registerNewAccount(); //(4)

                    break;

                case "0":
                    System.out.println("Going back to menu");
                    run = false;
                    break;

                default:
                    System.out.println("Choose 1,2 or 0");
                    break;
            }
        }
    }
    private boolean registerNewAccount() { //(4) Register new account
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your social security number (YYYY-MM-DD-****) (this will be your username): ");
            String socialSecurityNumber = scan.next();
            System.out.println("Enter Password: ");
            String password = scan.next();
            System.out.println("Enter First Name: ");
            String firstName = scan.next();
            System.out.println("Enter Last Name: ");
            String lastName = scan.next();
            System.out.println("Enter Your Email: ");
            String email = scan.next();
            if (customerExistsInList(socialSecurityNumber, password)) { //(4) Checks if the account already exists on SSN and password
                System.out.println("Account already exists! Please log in.");
                return false;
            }

            Customer newCustomer = new Customer(socialSecurityNumber, password, firstName, lastName, email); //(4) Creating new Customer
            customerList.add(newCustomer); //adding to list
            System.out.println("Account registered! \nWelcome " + firstName + " " + lastName);
            updateCustomersTextFile();
            return true;
        }
    }
    public boolean customerExistsInList(String socialSecurityNumber, String inputPassword) { //(step: (4, 5) Checks if the customer already exist in list
        for (Customer customer : customerList) {
            if (customer.getSocialSecurityNumber().equals(socialSecurityNumber) && customer.getPassword().equals(inputPassword)) {
                return true; // Customer found with matching Social Security Number and Password
            }
        }
        return false; // Customer not found, no matching Social Security Number or Password
    }

    public void logInCustomer() { //(5) Asking for log in information,
        Customer customer = new Customer();
        Scanner scan = new Scanner(System.in);
        System.out.println("\n----------------------------------------------------");
        System.out.println("Enter your social security number (YYYY-MM-DD-****): ");
        customer.setSocialSecurityNumber(scan.nextLine());
        System.out.println("\nEnter password: ");
        customer.setPassword(scan.nextLine());

        if (customerExistsInList(customer.getSocialSecurityNumber(), customer.getPassword())) { //(5) Calling customerExistInList method to see if socialsecurity and password is correct

            boolean run = true;

            while (run) {
                System.out.println("----------------------------------------------------" +
                        "\nWelcome!" +
                        "\n1. Shop" +
                        "\n2. Transaction History" +
                        "\n0. Logout" +
                        "\n----------------------------------------------------" +
                        "\nChoice: ");
                String shopOrHistory = scan.next();

                switch (shopOrHistory) {

                    case "1":
                        System.out.println("\n----------------------------------------------------" +
                                "\nYou picked shop" +
                                "\n\nProducts: ");
                        orders.addToShoppingCart(customer.getSocialSecurityNumber()); //(6)
                        break;
                    case "2":
                        orders.printOrdersByCustomer(customer.getSocialSecurityNumber()); //(7)
                        break;
                    case "0":
                        System.out.println("Logging out..");
                        run = false;
                        break;
                    default:
                        System.out.println("Choose 1,2 or 0.");
                        break;
                }
            }
        }else{
            System.out.println("Wrong login credentials.");
        }
    }

    public void printAllCustomers() {
        System.out.println("All customers: ");
        for (int i = 0; i < this.customerList.size(); i++) {
            System.out.println((i + 1) + ". " +
                    "Social Security Number - Username: " +
                    this.customerList.get(i).getSocialSecurityNumber() + ", " +
                    "Password: " +
                    this.customerList.get(i).getPassword() + ", " +
                    "First name: " +
                    this.customerList.get(i).getFirstName() + ", " +
                    "Last name: " +
                    this.customerList.get(i).getLastName() + ", " +
                    "Email: " +
                    this.customerList.get(i).getEmail());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void editCustomerInformation() {
        Scanner scan = new Scanner(System.in);

        // Display the list of customers with their index numbers
        printAllCustomers();

        // Prompt the user to select a customer to edit
        boolean run = true;
        while(run) {
            System.out.print("Enter the number of the customer you want to edit: " +
                    "\nPress 0 to go back" +
                    "\nChoice: ");
            int customerIndex = scan.nextInt();

            if(customerIndex == 0) {
                run = false;
            }

            // Check if the provided index is valid
            if (customerIndex >= 1 && customerIndex <= customerList.size()) {
                Customer customerToEdit = customerList.get(customerIndex - 1);

                // Prompt the user to choose which information to edit
                System.out.println("\nEdit Customer Information ");
                System.out.println("\n1. Social security number (Current: " + customerToEdit.getSocialSecurityNumber() + ")");
                System.out.println("2. Password (Current: " + customerToEdit.getPassword() + ")");
                System.out.println("3. First name (Current: " + customerToEdit.getFirstName() + ")");
                System.out.println("4. Last name (Current: " + customerToEdit.getLastName() + ")");
                System.out.println("5. Email (Current: " + customerToEdit.getEmail() + ")");
                System.out.println("0. Go back");
                System.out.print("\nEnter the number of the information to edit or press 0 to go back: ");
                String infoChoice = scan.next();

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
                    case "0":
                        break;  //Going back

                    default:
                        System.out.println("Invalid choice. No changes made.");
                        break;
                }

                if (!newValue.isEmpty()) {
                    System.out.println("Customer information updated.");
                }

            } else if (customerIndex > customerList.size()) {
                System.out.println("Invalid customer index. Please enter a valid index.");
            }
        }
    }

    public void updateCustomersTextFile(){
        try (PrintStream printStream = new PrintStream(new FileOutputStream(customersFileName))) {
            for (Customer customer : customerList) {
                String customerData = customer.formattedStringForFile();
                printStream.println(customerData);
            }
        } catch(IOException e) {
            System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
        }
    }
}
