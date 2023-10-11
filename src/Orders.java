package src;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {

    private ArrayList<Order> orderList = new ArrayList<>();

    private String ordersFileName = "Orders.txt";

    Scanner scanInput = new Scanner(System.in);

    Orders() {
        readOrdersFromFile(); //(1)
    }

    private void readOrdersFromFile() { //(1)
        if (!createFileForAllOrders()) { //(2)
            try {
                Scanner scan = new Scanner(new File(ordersFileName));
                while (scan.hasNextLine()) {
                    String Order = scan.nextLine();
                    String[] orderInfo = Order.split(",");

                    Order tempOrder = new Order(
                            orderInfo[0],
                            orderInfo[1]
                    );
                    orderList.add(tempOrder);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Wrong!" + e.getMessage());
            }
        }
    }

    private boolean createFileForAllOrders() { //(2)
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

    public void addToShoppingCart(String customerSSN) { //(3)
        Products products = new Products();
        ArrayList<Product> cart = new ArrayList<>();

        while (true) {
            products.printAllProducts();
            System.out.print("\nEnter the number of the product you want to add to your cart" +
                    "\nOr input 0 to go back" +
                    "\n----------------------------------------------------" +
                    "\nChoice: ");
            int productIndex = scanInput.nextInt();

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
            } else {
                System.out.println("Invalid product index.");
            }

        }
        makePurchase(cart, customerSSN);
    }

    private void removeFromShoppingCart(ArrayList<Product> tempCart){ //(4)

        if (tempCart.isEmpty()) {
            System.out.println("The shopping cart is empty.");
            return;
        }

        boolean run = true;
        while (run) {
            System.out.println("\nCart contents: ");
            for (int i = 0; i < tempCart.size(); i++) {
                System.out.println((i + 1) + ". " + tempCart.get(i).formattedToShoppingCart());
            }
            System.out.print("Enter the number of the product you want to remove (or 0 to go back): ");
            int productToRemove = scanInput.nextInt();

            if (productToRemove == 0) {
                break;
            }

            if (productToRemove >= 1 && productToRemove <= tempCart.size()) {
                Product removedProduct = tempCart.remove(productToRemove - 1);
                System.out.println("Product removed from cart: " + removedProduct.getBrand() + " " + removedProduct.getModel());
            } else {
                System.out.println("Invalid product index.");
            }
        }
    }

    private void makePurchase(ArrayList<Product> cart, String customerSSN) { //(5)
        if (cart.isEmpty()) {
            System.out.println("The shopping cart is empty.");
            return;
        }
        System.out.println("The products in your cart are: ");
        for (Product product : cart) {
            System.out.println(product.getBrand() + "-" + product.getModel() + " -  $" + product.getPrice());
        }

        System.out.print("\nDo you want to remove items from the cart before confirming the purchase? (yes/no): ");
        String removeOption = scanInput.next();

        if (removeOption.equalsIgnoreCase("yes")) {
            removeFromShoppingCart(cart);
        }

        double totalCost = calculateTotalCost(cart);

        System.out.println("\nTotal cost of the items in the cart: $" + totalCost);

        System.out.print("\nDo you want to confirm the purchase? (yes/no): ");
        String confirmation = scanInput.next();

        if (confirmation.equalsIgnoreCase("yes")) {
            Order newOrder = new Order(customerSSN, generateReceipt(cart, totalCost));
            orderList.add(newOrder);
            updateOrdersTextFile();
            System.out.println("\nPurchase completed successfully!");

            System.out.println("\nPurchased items:");
            for (Product product : cart) {
                System.out.println(product.getBrand() + " - " + product.getModel() + " - $" + product.getPrice());
            }
        } else {
            System.out.println("Purchase cancelled.");
        }
    }
    private double calculateTotalCost(ArrayList<Product> cart) { //(6)
        double totalCost = 0.0;
        for (Product product : cart) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }
    private String generateReceipt(ArrayList<Product> products, double totalCost) { //(7)
        String receipt = "";

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        receipt += "Products: ";
        for (Product product : products) {
            receipt += product.getBrand() + " " + product.getModel() + " - ";
        }

        receipt += "$" + totalCost + " " + "\tDate and time: " + formattedDate;

        return receipt;
    }

    public void updateOrdersTextFile() { //(8)
        try (PrintStream printStream = new PrintStream(new FileOutputStream(ordersFileName, true))) {
            Order lastOrder = orderList.get(orderList.size() - 1);
            printStream.println(lastOrder.formattedStringsForFile());
        } catch (IOException e) {
            System.out.println("Something went wrong when we added Orders to file: " + e.getMessage());
        }
    }

    public void printOrdersByCustomer(String customerSSN) {
        System.out.println("Orders for Customer with SSN: " + customerSSN);
        for (Order order : orderList) {
            if (order.getCustomerSSN().equals(customerSSN)) {
                System.out.println(order.getRestOfOrderInfo());
            }
        }
    }

    public void printAllTransactions() {
        for (int i = 0; i < this.orderList.size(); i++) {
            System.out.println((i + 1) + ". " +
                    "Social Security Number - Username: " +
                    this.orderList.get(i).getCustomerSSN() + ", " +
                    "Order info: " +
                    this.orderList.get(i).getRestOfOrderInfo());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
}

