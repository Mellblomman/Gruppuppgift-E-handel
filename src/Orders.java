package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {
    private ArrayList<Order> orderList = new ArrayList<Order>();
    private String ordersFileName = Customer.getSocialSecurityNumber() + "Orders.txt";

    Orders() {

    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    static boolean createFileForOrders() {
        File file = new File("Orders.txt");

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

    private void readOrdersFromFile() {
        try {
            Scanner scan = new Scanner(new File(ordersFileName));
            while (scan.hasNextLine()) { //if file already exist, a Scanner will read every line of the file and separate with ", "
                String Order = scan.nextLine();
                String[] orderInfo = Order.split(", ");

                Order tempOrder = new Order( //creating a customer object, with split values and this object will be added to customer list
                        orderInfo[0],
                        orderInfo[1],
                        Double.parseDouble(orderInfo[2]),
                        Double.parseDouble(orderInfo[3]),       //OBS!!! Fix later if we only get one variable from DateTime!!!!!!
                        Double.parseDouble(orderInfo[4])
                );
                orderList.add(tempOrder); //added to customerList
            }
        } catch (FileNotFoundException e) {
            System.out.println("Wrong!" + e.getMessage());
        }
    }
    private boolean addCustomerToTextFile(Customer newCustomer)  {

        try{
            FileOutputStream fos = new FileOutputStream(this.ordersFileName, true); //fos created to write data to the file customerFileName
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
