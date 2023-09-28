package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customers {
    private ArrayList<Customer> customerList = new ArrayList<Customer>();
    private String customerFileName = "Customers.txt";

    Customers() {}

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }
    public void logInCustomer(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your social security number:");
        String inputSSNForLogin = scan.next();

        if(getCustomerList().contains(inputSSNForLogin)) {
            System.out.println("Enter your password");

        }else{
            System.out.println("Wrong social security number.");}

        String inputCustomerPasswordForLogin = scan.next();
        if(getCustomerList().contains(inputCustomerPasswordForLogin)){
            System.out.println("Welcome");
        } else{
            System.out.println("Wrong password!");
        }
    }
    public void verifyLogin(String inputSSNForLogin, String inputCustomerPasswordForLogin){

    }

    boolean createFileWithCustomers() {
        File file = new File("Customers.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File have been created: " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists!");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when creating a txt-file!");
        }
        return true;
    }

    private void readCustomersFromFile() {
        try {
            Scanner scan = new Scanner(new File(customerFileName));
            while (scan.hasNextLine()) { //if file already exist, a Scanner will read every line of the file and seperate with ", "
                String Customer = scan.nextLine();
                String[] customerInfo = Customer.split(", ");

                Customer tempCustomer = new Customer( //creating a customer object, with split values and this object will be added to customer list
                        Integer.parseInt(customerInfo[0]),
                        customerInfo[1],
                        customerInfo[2],
                        customerInfo[3],
                        customerInfo[4]
                );
                customerList.add(tempCustomer); //added to customerList
            }
        } catch (FileNotFoundException e) {
            System.out.println("Wrong!" + e.getMessage());
        }
    }
    private boolean addCustomerToTextFile(Customer newCustomer)  {

        try{
            FileOutputStream fos = new FileOutputStream(this.customerFileName, true); //fos created to write data to the file customerFileName
            PrintStream printStream = new PrintStream(fos); //append: true indicates that data should be appended to the file if it already exists

            printStream.print("\n" + newCustomer.formatedStringForFile()); //print the customers information to the file
            System.out.println(newCustomer.formatedStringForFile());

            fos.close();
            printStream.close();
            return true;                    //if it works without exceptions it will be true, else false!
        } catch (Exception e){
            System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
        }

        return false;
    }
}


