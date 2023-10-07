package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin {
    Scanner scan = new Scanner(System.in);

    Products products = new Products();

    Customers customers = new Customers();

    Orders orders = new Orders();

    Admin(){
    }

    public void logInAdmin() {  //(1) Log in as admin

        boolean runAdminLogIn = true;
        while (runAdminLogIn) {
            System.out.println("Enter admin username:");
            String inputAdminUsernameForLogin = scan.next();
            System.out.println("Enter admin password:");
            String inputAdminPasswordForLogin = scan.next();


            String adminUserName = "a";
            String adminPassword = "a";
            //(2) If inputAdminUserName and Password equals adminUserName and adminPassword = logged in as admin
            if (inputAdminUsernameForLogin.equalsIgnoreCase(adminUserName) && inputAdminPasswordForLogin.equalsIgnoreCase(adminPassword)) {

                boolean runAdminMenu = true;

                while (runAdminMenu) {
                    System.out.println("\n----------------------------------------------------" +
                            "\nAdmin menu " +
                            "\n1. Product Management" +
                            "\n2. Customer Information" +
                            "\n3. Order/Transactions" +
                            "\n0. Logout" +
                            "\n----------------------------------------------------" +
                            "\nChoice: ");
                    String menuChoice = scan.next();
                    switch (menuChoice) {
                        case "1":
                            manageProductsAsAdmin(); // (3) FORTSÄTT MED FLÖDET HÄR när case "3" är klart!!!
                            break;
                        case "2":
                            manageCustomersAsAdmin();
                            break;
                        case "3":
                            System.out.println("testing");
                        case "0":
                            System.out.println("Logout");
                            runAdminMenu = false;
                            break;
                        default:
                            System.out.println("Wrong input. Choose 1-4");
                    }
                }
                runAdminLogIn = false; //Stops while loop to make exiting program work.
            } else {
                System.out.println("Please enter the correct credentials for admin.");

            }
        }
    }


    public void manageProductsAsAdmin(){

        boolean runAdminProductManagementMenu = true;

        while(runAdminProductManagementMenu){

            System.out.println("\n----------------------------------------------------" +
                    "\nProduct menu " +
                    "\n1. View all the products." +
                    "\n2. Add a product." +
                    "\n3. Remove a product." +
                    "\n4. Edit products." +
                    "\n0. Go back." +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");
            String choiceInProductMenu = scan.next();

            switch(choiceInProductMenu){
                case "1":
                    products.printAllProducts();
                    break;
                case "2":
                    products.addNewProduct();
                    break;
                case "3":
                    products.pickAProductToRemoveFromList();
                    break;
                case "4":
                    products.editProductInformation();
                    break;
                case "0":
                    runAdminProductManagementMenu = false;
                    break;
                default:
                    System.out.println("Please choose one of the options above.");
                    break;
            }
        }
    }

    public void manageCustomersAsAdmin(){
        boolean runAdminCustomerMenu = true;

        while(runAdminCustomerMenu){

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

    public void manageOrdersAsAdmin(){
        boolean runAdminOrdersMenu = true;

        while(runAdminOrdersMenu){

            System.out.println("\nManage customers menu" +
                    "\n1. View all." +
                    "\n2. Edit a specific customer." +
                    "\n0. Go back." +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");
            String choiceForCustomerMenu = scan.next();
            switch (choiceForCustomerMenu) {
                case "1":
                    orders.printAllTransactions();
                    break;
                case "2":
                    //orders.printOrdersByCustomer();
                case "0":
                    break; //Going back
                default:
                    System.out.println("Please choose one of the two options above.");
                    break;
            }
        }
    }

}
