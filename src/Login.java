package src;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Login
{
    Admin admin = new Admin();
    Customers customers = new Customers();

    public  void loginMenu() {
        Scanner scan = new Scanner(System.in);
        boolean runLoginMenu = true;
        while(runLoginMenu){
            System.out.println("\nWelcome - Please choose which form of login you want to do:");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Admin" +
                    "\n2. Customer" +
                    "\n3. Exit");

            String inputChoice = scan.nextLine();

            switch(inputChoice){
                case "1":
                    admin.logInAdmin();
                    break;
                case "2":
                    customers.customerMenu(); // customerMenu instead of logInCustomer
                    break;
                case "3":
                    System.out.println("Program is closing.");
                    runLoginMenu = false;
                    break;
                default:
                    System.out.println("Please enter a valid number.");
                    break;
            }
        }
    }
}
