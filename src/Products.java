package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Products {
    public ArrayList<Product> productList = new ArrayList<Product>();
    public String productFileName = "products.txt";

    public Products() {
        if (!createFileWithProducts()) {
            try {
                Scanner scan = new Scanner(new File(productFileName));
                while (scan.hasNextLine()) { //if file already exist, a Scanner will read every line of the file and seperate with ", "
                    String Products = scan.nextLine();
                    String[] productsInfo = Products.split(", ");

                    Product tempProducts = new Product( //creating a product object, with split values and this object will be added to products list
                            productsInfo[0],
                            productsInfo[1],
                            productsInfo[2]);
                    productList.add(tempProducts); //added to productList
                }
            } catch (FileNotFoundException e) {
                System.out.println("Wrong!" + e.getMessage());
            }
        }
    }

    public boolean createFileWithProducts () {  //Calling this method in admin when product is created.
        File file = new File(productFileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File have been created: " + file.getName());
                return true;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when creating a txt-file!");
        }
        return false;
    }


    private boolean addNewProduct() {
        Scanner scan = new Scanner(System.in);

        while (true) {                                 // loop to check if the user enter right Social Security Number
            System.out.println("Enter the brand of the product: ");
            String brandOfProduct = scan.next();
            System.out.println("Enter the model of the produect: ");
            String modelOfProduct = scan.next();
            System.out.println("Set the price for the product: ");
            String priceProduct = scan.next();
            !!!!!!!!!!!!!!!!!!!
            if (customerExistsInList(socialSecurityNumber, scan.nextLine())) {      //Checks if the account already exists on Social Security Number
                System.out.println("Account already exists! Please log in.");
                return false;
            }

            Customer newCustomer = new Customer(socialSecurityNumber, password, firstName, lastName, email); //Creating new Customer
            customerList.add(newCustomer); //adding to list
            updateCustomersTextFile();
            System.out.println("Account registered! Welcome " + firstName);
            return true;
        }
    }
    public void removeProductFromTextFile(Product product) {
        try {
            Scanner reader = new Scanner(new File(productFileName));
            ArrayList<String> lines = new ArrayList<>();
            boolean found = false;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.equals(product.getBrand() + ";" + product.getModel() + ";" + product.getPrice())) {
                    found = true;
                } else {
                    lines.add(line);
                }
            }
            reader.close();
            if (found) {
                PrintWriter writer = new PrintWriter(new FileWriter(productFileName));
                for (String line : lines) {
                    writer.println(line);
                }
                writer.close();
                System.out.println("Product removed.");
            } else {
                System.out.println("product not found");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong when we removed Product from the file" + e.getMessage());
        }
    }

    public void printAllProducts() {

        for (int i = 0; i < this.productList.size(); i++) {
            System.out.println((i + 1) + ". " +
                    this.productList.get(i).getBrand() + ", " +
                    this.productList.get(i).getModel() + ", " +
                    this.productList.get(i).getPrice());
        }
    }
}




