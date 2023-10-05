package src;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {

    private ArrayList<Order> orderList = new ArrayList<>();

    private String ordersFileName = "Orders.txt";

    Orders() {
        readOrdersFromFile();
    }

    public void readOrdersFromFile() {
        if (!createFileForAllOrders()) {
            try {
                Scanner scan = new Scanner(new File(ordersFileName));
                while (scan.hasNextLine()) { //if file already exist, a Scanner will read every line of the file and separate with ", "
                    String Order = scan.nextLine();
                    String[] orderInfo = Order.split(",");

                    Order tempOrder = new Order( //creating a customer object, with split values and this object will be added to customer list
                            orderInfo[0],
                            orderInfo[1]
                    );
                    orderList.add(tempOrder); //added to customerList
                }
            } catch (FileNotFoundException e) {
                System.out.println("Wrong!" + e.getMessage());
            }
        }
    }

    public void dateTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

    }

    boolean createFileForAllOrders() {
        File file = new File("Orders.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File has been created: " + file.getName());
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when creating a txt-file:" + e.getMessage());
        }
        return true;
    }
    public void printAllTransactions() {
        for (int i = 0; i < this.orderList.size(); i++) {
            System.out.println((i + 1) + ". " +
                    this.orderList.get(i).getCustomerSSN() + ", " +
                    this.orderList.get(i).getRestOfOrderInfo());
            System.out.println("----------------------------------------------------");
        }
    }

    public void printOrdersByCustomer(String customerSSN) {
        System.out.println("Orders for Customer with SSN: " + customerSSN);
        for (Order order : orderList) {
            if (order.getCustomerSSN().equals(customerSSN)) {
                System.out.println("Name: " + order.getRestOfOrderInfo());
                System.out.println("--------------------");
            }
        }
    }
    private boolean validSocialSecurityNumber(String socialSecurityNumber) {
        if (socialSecurityNumber.length() != 15) { //Checking if Social Security Number holds 15 characters
            return false;
        }
        String[] partsSocialSecurityNumber = socialSecurityNumber.split("-"); //splits the Social Security Number into different parts with - between
        if (partsSocialSecurityNumber.length != 4) {
            return false;
        }
        try {
            int year = Integer.parseInt(partsSocialSecurityNumber[0]);              //1st part
            int month = Integer.parseInt(partsSocialSecurityNumber[1]);             //2nd part
            int day = Integer.parseInt(partsSocialSecurityNumber[2]);               //3rd part
            int lastDigits = Integer.parseInt(partsSocialSecurityNumber[3]);        //4th part

            if (year < 1900 || year > 9999 || month < 1 || month > 12               //set up to 9999 cause of last digits!
                    || day < 1 || day > 31 || lastDigits < 0 || lastDigits > 9999) {
                return false;
            }
        } catch (
                NumberFormatException e) { //Catch when trying to convert a string with improper format into a numeric value
            return false;
        }
        return true;
    }
}
// en som printar alla transationer.
// en som printar alla transationer kopplat till varje kund.
// en metod som skapar ett köp där lägger produktobjekt i en temporär arraylist
// skapa en varukorg
