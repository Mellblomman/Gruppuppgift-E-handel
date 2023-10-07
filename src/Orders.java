package src;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {

    Order order = new Order("", "");

    Customer customer = new Customer();

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

    public void dateTime() {
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

    public void addToShoppingCart(String customerSSN) {
        Products products = new Products();
        Scanner scan = new Scanner(System.in);
        ArrayList<Product> cart = new ArrayList<>();

        while (true) {
            products.printAllProducts();
            System.out.print("\nEnter the number of the product you want to add to your cart" +
                    "\nOr input 0 to go back" +
                    "\n----------------------------------------------------" +
                    "\nChoice:");
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
                System.out.println("----------------------------------------------------" +
                        "\nProducts");
            } else {
                System.out.println("Invalid product index.");
            }

        }
        makePurchase(cart, customerSSN);
    }

    private String generateReceipt(ArrayList<Product> products, double totalCost) {
        String receipt = "";

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("\tdd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        for (Product product : products) {
            receipt += product.getBrand() + " - " + product.getModel() + ", - ";
        }

        receipt += totalCost;
        receipt += formattedDate;

        return receipt;
    }

    public void makePurchase(ArrayList<Product> cart, String customerSSN) {
        if (cart.isEmpty()) {
            System.out.println("The shopping cart is empty.");
            return;
        }
        System.out.println("The product in your cart are:");
        for (Product product : cart) {
            System.out.println(product.brand + "-" + product.getModel() + " -  $" + product.getPrice());
        }

        // Add logic here to calculate the total cost of the items in the cart
        double totalCost = calculateTotalCost(cart);

        // Display the total cost to the user
        System.out.println("Total cost of the items in the cart: $" + totalCost);

        // Ask the user to confirm the purchase
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to confirm the purchase? (yes/no): ");
        String confirmation = scanner.nextLine();



        if (confirmation.equalsIgnoreCase("yes")) {
            Order newOrder = new Order(customerSSN, generateReceipt(cart, totalCost));
            orderList.add(newOrder);
            updateOrdersTextFile();
            System.out.println("Purchase completed successfully!");

            System.out.println("Purchased items:");
            for (Product product : cart) {
                System.out.println(product.getBrand() + " - " + product.getModel() + " - $" + product.getPrice());
            }
        } else {
            System.out.println("Purchase cancelled.");
        }
    }

    public void printOrdersByCustomer(String customerSSN) {
        System.out.println("Orders for Customer with SSN: " + customerSSN);
        for (Order order : orderList) {
            if (order.getCustomerSSN().equals(customerSSN)) {
                System.out.println(order.getRestOfOrderInfo());
                System.out.println("--------------------");
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
        try (PrintStream printStream = new PrintStream(new FileOutputStream(ordersFileName))) {
            for (Order order : orderList) {
                String orderData = order.formattedStringsForFile();
                printStream.println(orderData);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
        }
    }
}