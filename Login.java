import java.util.InputMismatchException;
import java.util.Scanner;
public class Login
{
    //Enter username
    //Enter password

    Admin admin = new Admin();

    public  void loginMenu()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nWelcome - Please choose which form of login you want to do:");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Admin" +
                "\n2. Customer" +
                "\n3. Exit");
        try {
            int inputNumForChoice = scan.nextInt();

            if (inputNumForChoice == 1) {
                admin.logInAdmin();
            } else if (inputNumForChoice == 2) {
                System.out.println("Enter your social security number:");
                int inputSSNForLogin = scan.nextInt();
                System.out.println("Enter your password");
                String inputCustomerPasswordForLogin = scan.nextLine();

                //if(inputSSNForLogin == )
            } else if (inputNumForChoice == 3) {
                System.out.println("Exiting program.");
            } else {
                System.out.println("Please choose one of the three options above.");
            }
        }catch(InputMismatchException e){
            System.out.println("Wrong input! Enter 1,2 or 3");
        }
    }

}
