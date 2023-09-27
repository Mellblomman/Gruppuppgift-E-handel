import java.util.Scanner;
public class Login
{
    //Enter username
    //Enter password

    public void loginMenu()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Admin" +
                "\n2. Customer");
        int inputNumForChoice = scan.nextInt();

        if (inputNumForChoice == 1)
        {
            System.out.println("Enter admin username:");
            String inputAdminUsernameForLogin = scan.nextLine();
            System.out.println("Enter admin password:");
            String inputAdminPasswordForLogin = scan.nextLine();
        }
        else if (inputNumForChoice == 2)
        {
            System.out.println("Enter your social security number:");
            int inputSSNForLogin = scan.nextInt();
            System.out.println("Enter your password");
            String inputCustomerPasswordForLogin = scan.nextLine();
        }
        else
        {
            System.out.println("Please choose one of the two options above.");
        }
    }

}
