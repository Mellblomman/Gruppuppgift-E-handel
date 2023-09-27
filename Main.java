import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {
        Customer customer = new Customer();
        customer.createFileWithCustomers();
        Login login = new Login();
        login.loginMenu();
    }
}
