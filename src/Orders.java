package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {

    private ArrayList<Order> orderList = new ArrayList<>();

    //private String ordersFileName = customer.getSocialSecurityNumber() + "Orders.txt";

  /*  Orders() {
        readOrdersFromFile();
    }


    public void readOrdersFromFile() {
        try {
            Scanner scan = new Scanner(new File(ordersFileName));
            while (scan.hasNextLine()) { //if file already exist, a Scanner will read every line of the file and separate with ", "
                String Order = scan.nextLine();
                String[] orderInfo = Order.split(",");

                Order tempOrder = new Order( //creating a customer object, with split values and this object will be added to customer list
                        orderInfo[0],
                        orderInfo[1],
                        Double.parseDouble(orderInfo[2]),
                        Double.parseDouble(orderInfo[3])
                );
                orderList.add(tempOrder); //added to customerList
            }
        } catch (FileNotFoundException e) {
            System.out.println("Wrong!" + e.getMessage());
        }
    }
 */

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    boolean createFileForAllOrders() {
        File file = new File("Orders.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File has been created: " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists!");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when creating a txt-file:" + e.getMessage());
        }
        return true;
    }

    public void createFileWithCustomerOrders(String ssn) {

        // Specify the directory where we want to store customer orders files
        String directoryPath = "customer_orders";

        // Ensure the directory exists; create it if it doesn't
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            // Create the directory if it doesn't exist
            if (directory.mkdir()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                // Print an error message if directory creation fails
                System.err.println("Failed to create directory: " + directoryPath);
                return; // Exit the method
            }
        }

        // Construct the file name with the customer's SSN and "orders" as the extension
        File customerOrdersFile = new File(ssn + "orders.txt");

        try {
            if (customerOrdersFile.createNewFile()) {
                System.out.println("Customer orders file has been created: " + customerOrdersFile.getName());
            } else {
                System.out.println("File " + customerOrdersFile.getName() + " already exists!");
            }
        } catch (IOException e) {
            System.err.println("Something went wrong when creating the txt-file: " + e.getMessage());
        }
    }
}
