package src;

import java.util.Scanner;
public class Login
{
    public  void loginMenu() {  //(1)
        Scanner scan = new Scanner(System.in);
        boolean runLoginMenu = true;
        while(runLoginMenu){
            System.out.println("\nWelcome To MLSS - Computers");
            System.out.println("" +
                    "                __\n" +
                    "    ..=====..  |==|\n" +
                    "    ||     ||  |= |\n" +
                    "    ||     ||  |^*| \n" +
                    "    o=,===,=o  |__|\n" +
                    "     _______\n" +
                    "    [=======]  () ");
            System.out.println("----------------------------------------------------");
            System.out.println("Please choose which form of login you want to do:");    //(2)
            System.out.println("1. Admin" +
                    "\n2. Customer" +
                    "\n0. Exit" +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");

            String inputChoice = scan.nextLine();

            switch(inputChoice){
                case "1":
                    Admin admin = new Admin();
                    admin.logInAdmin();     // (3)
                    break;
                case "2":
                    Customers customers = new Customers();
                    customers.customerMenu(); // (3)
                    break;
                case "0":
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
