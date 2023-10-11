package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
    private ArrayList<Customer> customerList = new ArrayList<>();
    private String customersFileName = "Customers.txt";

    Scanner scanInput = new Scanner(System.in);

    Orders orders = new Orders();

    public Customers() {
        readCustomersFromFile(); //(1)
    }
    private void readCustomersFromFile(){ //(1)
        if (!createFileWithCustomers()) { //(2)
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
                System.out.println("Wrong!" + e.getMessage());
            }
        }
    }

    private boolean createFileWithCustomers() { //(2)
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

    public void customerMenu() { //(3)

        boolean run = true;

        while (run) {
            System.out.println("\n----------------------------------------------------" +
                    "\nCustomer Menu" +
                    "\n1. Login" +
                    "\n2. Register new account" +
                    "\n0. Go back." +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");
            String loginOrRegister = scanInput.next();

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
    private boolean registerNewAccount() { //(4)

        while (true) {
            System.out.println("Enter your social security number (YYYY-MM-DD-****) (this will be your username): ");
            String socialSecurityNumber = scanInput.next();
            System.out.println("Enter Password: ");
            String password = scanInput.next();
            System.out.println("Enter First Name: ");
            String firstName = scanInput.next();
            System.out.println("Enter Last Name: ");
            String lastName = scanInput.next();
            System.out.println("Enter Your Email: ");
            String email = scanInput.next();
            if (customerExistsInList(socialSecurityNumber, password)) { //(4)
                System.out.println("Account already exists! Please log in.");
                return false;
            }

            Customer newCustomer = new Customer(socialSecurityNumber, password, firstName, lastName, email); //(4)
            customerList.add(newCustomer); //adding to list
            System.out.println("Account registered! \nWelcome " + firstName + " " + lastName);
            updateCustomersTextFile();
            return true;
        }
    }
    private boolean customerExistsInList(String socialSecurityNumber, String inputPassword) { //(step: (4, 5)
        for (Customer customer : customerList) {
            if (customer.getSocialSecurityNumber().equals(socialSecurityNumber) && customer.getPassword().equals(inputPassword)) {
                return true;
            }
        }
        return false;
    }

    private void logInCustomer() { //(5)

        Customer customer = new Customer();
        System.out.println("\n----------------------------------------------------");
        System.out.println("Enter your social security number (YYYY-MM-DD-****): ");
        customer.setSocialSecurityNumber(scanInput.next());
        System.out.println("\nEnter password: ");
        customer.setPassword(scanInput.next());

        if (customerExistsInList(customer.getSocialSecurityNumber(), customer.getPassword())) { //(5)

            boolean run = true;

            while (run) {
                System.out.println("----------------------------------------------------" +
                        "\nWelcome!" +
                        "\n1. Shop" +
                        "\n2. Transaction History" +
                        "\n0. Logout" +
                        "\n----------------------------------------------------" +
                        "\nChoice: ");
                String shopOrHistory = scanInput.next();

                switch (shopOrHistory) {

                    case "1":
                        System.out.println("\n----------------------------------------------------" +
                                "\nYou picked shop");
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

        printAllCustomers();


        boolean run = true;
        while(run) {
            System.out.print("Enter the number of the customer you want to edit: " +
                    "\nPress 0 to go back" +
                    "\nChoice: ");
            int customerIndex = scanInput.nextInt();

            if(customerIndex == 0) {
                run = false;
            }

            if (customerIndex >= 1 && customerIndex <= customerList.size()) {
                Customer customerToEdit = customerList.get(customerIndex - 1);

                System.out.println("\nEdit Customer Information ");
                System.out.println("\n1. Social security number (Current: " + customerToEdit.getSocialSecurityNumber() + ")");
                System.out.println("2. Password (Current: " + customerToEdit.getPassword() + ")");
                System.out.println("3. First name (Current: " + customerToEdit.getFirstName() + ")");
                System.out.println("4. Last name (Current: " + customerToEdit.getLastName() + ")");
                System.out.println("5. Email (Current: " + customerToEdit.getEmail() + ")");
                System.out.println("0. Go back");
                System.out.print("\nEnter the number of the information to edit or press 0 to go back: ");
                String infoChoice = scanInput.next();

                while(run) {
                    String newValue = "";
                    switch (infoChoice) {
                        case "1":
                            System.out.print("Enter new social security number: ");
                            newValue = scanInput.next();
                            customerToEdit.setSocialSecurityNumber(newValue);
                            break;
                        case "2":
                            System.out.print("Enter new password: ");
                            newValue = scanInput.next();
                            customerToEdit.setPassword(newValue);
                            break;
                        case "3":
                            System.out.print("Enter new first name: ");
                            newValue = scanInput.next();
                            customerToEdit.setFirstName(newValue);
                            break;
                        case "4":
                            System.out.print("Enter new last name: ");
                            newValue = scanInput.next();
                            customerToEdit.setLastName(newValue);
                            break;
                        case "5":
                            System.out.print("Enter new email: ");
                            newValue = scanInput.next();
                            customerToEdit.setEmail(newValue);
                            break;
                        case "0":
                            break;

                        default:
                            System.out.println("Invalid choice. No changes made.");
                            break;
                    }
                    if (!newValue.isEmpty()) {
                        System.out.println("Customer information updated.");
                    }
                    run = false;

                }


            } else if (customerIndex > customerList.size()) {
                System.out.println("Invalid customer index. Please enter a valid index.");
            }
        }updateCustomersTextFile();
    }

    private void updateCustomersTextFile(){
        try (PrintStream printStream = new PrintStream(new FileOutputStream(customersFileName, false))) {
            for (Customer customer : customerList) {
                String customerData = customer.formattedStringForFile();
                printStream.println(customerData);
            }
        } catch(IOException e) {
            System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
        }
    }
}
