package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin {
    Scanner scan = new Scanner(System.in);
    Products products = new Products();

    private final String adminUserName = "admin";
    private final String adminPassword = "mlssadmin";

    Admin() {
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
                            System.out.println("Insert Product Management Method");
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

            System.out.println("What do you want to do?");
            String choiceInProductMenu = scan.nextLine();

            System.out.println("1. View all the products." +
                    "\n2. Add a product." +
                    "\n3. Remove a product.");

            switch(choiceInProductMenu){
                case "1":
                    System.out.println("View all the products.");
                    products.printProducts();
                    break;
                case "2":
                    System.out.println("Add a product.");
                    break;
                case "3":
                    System.out.println("Remove a product.");
                    //products.removeProductFromTextFile();
                    break;
                case "4":
                    break;
                default:
                    break;
            }
        }
    }

}
