package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin {
    Scanner scan = new Scanner(System.in);
    Products products = new Products();

    Customers customers = new Customers();

    private final String adminUserName = "admin";
    private final String adminPassword = "mlssadmin";

    Admin(){
    }

    public void logInAdmin() {

        boolean runAdminLogIn = true;
        while (runAdminLogIn) {
            System.out.println("Enter admin username:");
            String inputAdminUsernameForLogin = scan.nextLine();
            System.out.println("Enter admin password:");
            String inputAdminPasswordForLogin = scan.nextLine();


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
                            System.out.println("Insert Customer Information Method");
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
            String choiceForCustomerMenu = "";
            System.out.println("Customer menu:" +
                    "\n1. View all." +
                    "\n2. Edit a specific customer." +
                    "\n3. Go back.");
            if(choiceForCustomerMenu == "1"){
                customers.printAllCustomers();
            }
            else if(choiceForCustomerMenu == "2"){
                //Get the arrayList shit working......
            }
            else if(choiceForCustomerMenu == "3"){
                runAdminCustomerMenu = false;
            }
            else {
                System.out.println("Please choose one of the two options above.");
            }
        }
    }

}
