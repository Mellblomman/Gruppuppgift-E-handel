package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin {
    Scanner scan = new Scanner(System.in);
    Products products = new Products();

    Customers customers = new Customers();

    Admin(){
    }

    public void logInAdmin() {

        boolean runAdminLogIn = true;
        while (runAdminLogIn) {
            System.out.println("Enter admin username:");
            String inputAdminUsernameForLogin = scan.nextLine();
            System.out.println("Enter admin password:");
            String inputAdminPasswordForLogin = scan.nextLine();


            String adminUserName = "a";
            String adminPassword = "a";
            if (inputAdminUsernameForLogin.equalsIgnoreCase(adminUserName) && inputAdminPasswordForLogin.equalsIgnoreCase(adminPassword)) {

                boolean runAdminMenu = true;

                while (runAdminMenu) {
                    System.out.println("Pick a number:" +
                            "\n1. Product Management" +
                            "\n2. Customer Information" +
                            "\n3. Order/Transactions" +
                            "\n4. Logout");
                    String menuChoice = scan.next();
                    switch (menuChoice) {
                        case "1":
                            manageProductsAsAdmin();
                            break;
                        case "2":
                            manageCustomersAsAdmin();
                            break;
                        case "3":
                            System.out.println("Insert Order/Transactions Method");
                            break;
                        case "4":
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

            System.out.println("What do you want to do?" +
                    "\n1. View all the products." +
                    "\n2. Add a product." +
                    "\n3. Remove a product." +
                    "\n4. Go back.");
            String choiceInProductMenu = scan.nextLine();

            switch(choiceInProductMenu){
                case "1":
                    products.printProducts();
                    break;
                case "2":
                    break;
                case "3":
                    //products.removeProductFromTextFile();
                    break;
                case "4":
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
            System.out.println("\nWhat do you want to do?");
            String choiceForCustomerMenu = scan.nextLine();
            System.out.println("\nCustomer menu:" +
                    "\n1. View all." +
                    "\n2. Edit a specific customer." +
                    "\n3. Go back.");
            if(choiceForCustomerMenu.equals("1")){
                customers.printAllCustomers();
            }
            else if(choiceForCustomerMenu.equals("2")){
                customers.editCustomerInformation();
            }
            else if(choiceForCustomerMenu.equals("3")){
                runAdminCustomerMenu = false;
            }
            else {
                System.out.println("Please choose one of the two options above.");
            }
        }
    }

}
