package src;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {
        Customers customers = new Customers();
        customers.createFileWithCustomers();
        Login login = new Login();
        login.loginMenu();
    }


}