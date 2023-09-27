import java.util.Scanner;

public class Admin {
    Scanner scan = new Scanner(System.in);
    private String name = "admin";
    private String password = "mlssadmin";

    Admin(){}
    public String getName() {
        return name;
    }
    public String getPassWord() {
        return password;
    }
    public void logInAdmin(){


        System.out.println("Enter admin username:");
        String inputAdminUsernameForLogin = scan.nextLine().toLowerCase();
        System.out.println("Enter admin password:");
        String inputAdminPasswordForLogin = scan.nextLine().toLowerCase();

        if(inputAdminUsernameForLogin.equals(this.name) && inputAdminPasswordForLogin.equals(this.password))
        {
            boolean run = true;
            while(run){
                System.out.println("Pick a number." +
                        "\n1. Product Management" +
                        "\n2. Customer Information" +
                        "\n3. Order/Transactions" +
                        "\n4. Logout");
                String menuChoice = scan.next();
                switch(menuChoice) {
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
                        run = false;
                        Login loginmenu = new Login();
                        loginmenu.loginMenu();
                        break;
                    default:
                        System.out.println("Wrong input. Choose 1-4");
                }
            }
        }
    }
}