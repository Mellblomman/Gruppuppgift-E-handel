package src;

import java.util.Scanner;

public class Admin {
    Scanner scan = new Scanner(System.in);

    Products products = new Products();

    Customers customers = new Customers();

    Orders orders = new Orders();

    Admin() {
    }

    public void logInAdmin() {  //(1)

        boolean runAdminLogIn = true;
        while (runAdminLogIn) {
            System.out.println("Enter admin username:");
            String inputAdminUsernameForLogin = scan.next();
            System.out.println("Enter admin password:");
            String inputAdminPasswordForLogin = scan.next();


            String adminUserName = "admin";
            String adminPassword = "mlssadmin";
            //(2)
            if (inputAdminUsernameForLogin.equalsIgnoreCase(adminUserName) && inputAdminPasswordForLogin.equalsIgnoreCase(adminPassword)) {

                boolean runAdminMenu = true;

                while (runAdminMenu) {
                    System.out.println("\n----------------------------------------------------" +
                            "\nAdmin menu " +
                            "\n1. Product Management" +
                            "\n2. Manage Customer Information" +
                            "\n3. Transactions" +
                            "\n0. Logout" +
                            "\n----------------------------------------------------" +
                            "\nChoice: ");
                    String menuChoice = scan.next();
                    switch (menuChoice) {
                        case "1":
                            productManagement(); // (3)
                            break;
                        case "2":
                            manageCustomersAsAdmin(); // (4)
                            break;
                        case "3":
                            customerTransactions(); // (5)
                            break;
                        case "0":
                            System.out.println("Logout");
                            runAdminMenu = false;
                            break;
                        default:
                            System.out.println("Wrong input. Choose 1-3 or 0 to go back.");
                    }
                }
                runAdminLogIn = false;
            } else {
                System.out.println("Please enter the correct credentials for admin.");

            }
        }
    }

    private void productManagement() { // (3)

        boolean run = true;

        while (run) {

            System.out.println("\n----------------------------------------------------" +
                    "\nAdmin Product Management Menu " +
                    "\n1. Print all products" +
                    "\n2. Add new product" +
                    "\n3. Remove a product from list" +
                    "\n4. Edit Products Information" +
                    "\n0. Go back" +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");
            String choiceInProductMenu = scan.next();

            switch (choiceInProductMenu) {
                case "1":
                    products.printAllProducts();
                    break;
                case "2":
                    products.addNewProduct();
                    break;
                case "3":
                    products.removeProductFromList();
                    break;
                case "4":
                    products.editProductInformation();
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    System.out.println("Choose 1-4 or 0");
                    break;
            }
        }
    }

    private void manageCustomersAsAdmin() { // (4)
        boolean runAdminCustomerMenu = true;

        while (runAdminCustomerMenu) {

            System.out.println("\nManage customers menu" +
                    "\n1. View all." +
                    "\n2. Edit a specific customer." +
                    "\n0. Go back." +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");
            String choiceForCustomerMenu = scan.next();
            switch (choiceForCustomerMenu) {
                case "1":
                    customers.printAllCustomers();
                    break;
                case "2":
                    customers.editCustomerInformation();
                    break;
                case "0":
                    runAdminCustomerMenu = false;
                    break;
                default:
                    System.out.println("Please choose one of the two options above.");
                    break;
            }
        }
    }

    private void customerTransactions() { // (5)
        boolean runAdminOrdersMenu = true;

        while (runAdminOrdersMenu) {

            System.out.println("\nAll Transactions" +
                    "\n1. View all." +
                    "\n0. Go back." +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");
            String choiceForCustomerMenu = scan.next();
            switch (choiceForCustomerMenu) {
                case "1":
                    orders.printAllTransactions();
                    break;
                case "0":
                    runAdminOrdersMenu = false;
                    break;
                default:
                    System.out.println("Please choose one of the two options above.");
                    break;
            }
        }
    }
}
