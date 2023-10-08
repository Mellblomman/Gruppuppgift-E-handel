package src;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Orders {

    public ArrayList<Order> orderList = new ArrayList<>();

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

        File transactionsFileReader = new File(ordersFileName); // Reading from orders.txt instead of orderList.
        Scanner scan = null;
        try {
            scan = new Scanner(transactionsFileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        scan.close();
    }

    public void addToShoppingCart(String customerSSN) {
        Products products = new Products();
        Scanner scan = new Scanner(System.in);
        ArrayList<Product> cart = new ArrayList<>();

        while (true) {
            products.printAllProducts();
            System.out.print("\nEnter the number of the product you want to add to your cart" +
                    "\nOr input 0 to go back" +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");
            int productIndex = scan.nextInt();

            if (productIndex == 0) {
                break;
            }
            if (productIndex >= 1 && productIndex <= products.productList.size()) {
                Product productToAdd = products.productList.get(productIndex - 1);
                cart.add(productToAdd);
                System.out.println("\nProduct added to cart: " + productToAdd.getBrand() + " " + productToAdd.getModel());
                System.out.println("\nCart contents: ");
                for (int i = 0; i < cart.size(); i++) {
                    System.out.println(cart.get(i).formattedToShoppingCart());
                }
                System.out.println("----------------------------------------------------");
                System.out.println("\nProducts");
            } else {
                System.out.println("Invalid product index.");
            }

        }
        makePurchase(cart, customerSSN);
    }

    private String generateReceipt(ArrayList<Product> products, double totalCost) {
        String receipt = "";

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("\tdd-MM-yyyy HH:mm:ss"); //Date and time
        String formattedDate = myDateObj.format(myFormatObj);

        for (Product product : products) {
            receipt += product.getBrand() + " - " + product.getModel() + ", - ";
        }

        receipt += totalCost + " " + formattedDate;

        return receipt;
    }

    public void makePurchase(ArrayList<Product> cart, String customerSSN) {
        if (cart.isEmpty()) {
            System.out.println("The shopping cart is empty.");
            return;
        }
        System.out.println("The products in your cart are: ");
        for (Product product : cart) {
            System.out.println(product.brand + "-" + product.getModel() + " -  $" + product.getPrice());
        }

        // Add logic here to calculate the total cost of the items in the cart
        double totalCost = calculateTotalCost(cart);

        // Display the total cost to the user
        System.out.println("\nTotal cost of the items in the cart: $" + totalCost);

        // Ask the user to confirm the purchase
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you want to confirm the purchase? (yes/no): ");
        String confirmation = scanner.nextLine();


        if (confirmation.equalsIgnoreCase("yes")) {
            Order newOrder = new Order(customerSSN, generateReceipt(cart, totalCost));
            orderList.add(newOrder);
            updateOrdersTextFile();
            System.out.println("Purchase completed successfully. We hope to see you again soon!");
            System.out.println("--------");
            showReceipt();

        } else {
            System.out.println("Purchase cancelled.");
        }
    }

    public void printOrdersByCustomer(String customerSSN) {
        System.out.println("Orders for Customer with SSN: " + customerSSN);
        for (Order order : orderList) {
            if (order.getCustomerSSN().equals(customerSSN)) {
                System.out.println(order.getRestOfOrderInfo());
                System.out.println("----------------------------------------------------");
            }
        }
    }

    private double calculateTotalCost(ArrayList<Product> cart) {
        double totalCost = 0.0;
        for (Product product : cart) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public void updateOrdersTextFile() {
        try (PrintStream printStream = new PrintStream(new FileOutputStream(ordersFileName, true))) {
            Order lastOrder = orderList.get(orderList.size() - 1); //Append After last token on that line.
            //Now it won't overwrite date,time,totalcost
            printStream.print(lastOrder.formattedStringsForFile());

        } catch (IOException e) {
            System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
        }
    }
    public void showReceipt() {
        for (Order order : orderList) {

            System.out.println("Receipt:\n " + order.getRestOfOrderInfo());
            System.out.println("Customer with SSN: " + order.getCustomerSSN());
            System.out.println("---------------------------------------");
        }
    }
}