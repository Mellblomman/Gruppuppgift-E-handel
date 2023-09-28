package src;

import java.util.Scanner;

public class Admin {
    Scanner scan = new Scanner(System.in);


    private final String adminUserName = "admin";
    private final String adminPassword = "mlssadmin";

    Admin() {
    }

    public void logInAdmin() {
        Login login = new Login();

        boolean runAdminLogIn = true;

        while (runAdminLogIn) {
            System.out.println("Enter admin username:");
            String inputAdminUsernameForLogin = scan.nextLine().toLowerCase();
            System.out.println("Enter admin password:");
            String inputAdminPasswordForLogin = scan.nextLine().toLowerCase();


            if (inputAdminUsernameForLogin.equals(adminUserName) && inputAdminPasswordForLogin.equals(adminPassword)) {

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
                            login.loginMenu();
                            break;
                        default:
                            System.out.println("Wrong input. Choose 1-4");
                    }
                }
            } else {
                System.out.println("Please enter the correct credentials for admin.");

            }
        }
    }
}
