package src;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Products {
public ArrayList<Product> productList = new ArrayList<>();
public String productFileName = "products.txt";

    public Products(){
        readProductsFromFile();
    }
    public void readProductsFromFile(){
        if (!createFileWithProducts()) {
            try {
                Scanner scan = new Scanner(new File(productFileName));
                while (scan.hasNextLine()) { //if file already exist, a Scanner will read every line of the file and seperate with ", "
                    String Products = scan.nextLine();
                    String[] productsInfo = Products.split(",");

                    Product tempProducts = new Product( //creating a product object, with split values and this object will be added to products list
                            productsInfo[0],
                            productsInfo[1],
                            productsInfo[2]
                    );
                    productList.add(tempProducts); //added to productList
                }
            } catch (FileNotFoundException e) {
                System.out.println("Wrong!" + e.getMessage());
            }
        }
    }

    public boolean createFileWithProducts() {  //Calling this method in admin when product is created.
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


    public void addNewProduct() {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("\n----------------------------------------------------" +
                    "\nEnter the brand of the product: ");
            String brandOfProduct = scan.next();
            System.out.println("\nEnter the model of the product: ");
            String modelOfProduct = scan.next();
            System.out.println("\nSet the price for the product: ");
            String priceProduct = scan.next();
            if (productExistsInList(modelOfProduct, scan.nextLine())) {
                System.out.println("Product already exists! Please log in.");

            }else {
                Product newProduct = new Product(brandOfProduct, modelOfProduct, priceProduct); //Creating new Customer
                productList.add(newProduct); //adding to list
                updateProductsTextFile();
                System.out.println("\nProduct added! " + brandOfProduct + ", " + modelOfProduct + ", " + priceProduct);
            }
            while(true) {
                System.out.println("\nDo you want to add another product? \n(Yes/No)" +
                        "\n----------------------------------------------------" +
                        "\nChoice: ");
                String yesOrNo = scan.next();
                if (yesOrNo.equalsIgnoreCase("Yes")) {
                    break;
                } else if (yesOrNo.equalsIgnoreCase("No")) {
                    run = false;
                    break;
                }else{
                    System.out.println("\nInvalid input");
                }
            }
        }
    }

    public void editProductInformation () {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n----------------------------------------------------" +
                "\nProduct List");// Display the list of customers with their index numbers
        printAllProducts();

        // Prompt the user to select a customer to edit
        System.out.print("\n----------------------------------------------------" +
                "\nEnter the index of the product you want to edit" +
                "\nChoice: ");
        int productIndex = scan.nextInt();

        // Check if the provided index is valid
        if (productIndex >= 1 && productIndex <= productList.size()) {
            Product productToEdit = productList.get(productIndex - 1);

            // Prompt the user to choose which information to edit
            System.out.println("\n----------------------------------------------------" +
                    "\nEdit Product Information for " + productToEdit.getBrand() + " " + productToEdit.getModel());
            System.out.println("\n1. Name of brand (Current: " + productToEdit.getBrand() + ")");
            System.out.println("2. Name of model (Current: " + productToEdit.getModel() + ")");
            System.out.println("3. Product price (Current: " + productToEdit.getPrice() + ")");
            System.out.print("\nChoice: ");
            String infoChoice = scan.next();

            // Prompt the user for the updated value based on their choice
            String newValue = "";
            switch (infoChoice) {
                case "1":
                    System.out.print("\n----------------------------------------------------" +
                            "\nEnter new brand name: ");
                    newValue = scan.next();
                    productToEdit.setBrand(newValue);
                    break;
                case "2":
                    System.out.print("\n----------------------------------------------------" +
                            "\nEnter new model name: ");
                    newValue = scan.next();
                    productToEdit.setModel(newValue);
                    break;
                case "3":
                    System.out.print("\n----------------------------------------------------" +
                            "\nEnter new price: ");
                    newValue = scan.next();
                    productToEdit.setPrice(newValue);
                    break;
                default:
                    System.out.println("\nInvalid choice. No changes made.");
            }
            if (!newValue.isEmpty()) {
                System.out.println("\nProduct information updated.");
            }
        } else {
            System.out.println("Invalid product index. Please enter a valid index.");
        }
    }


    public void pickAProductToRemoveFromList(){
        // Create a scanner to read input from the user
        Scanner scan = new Scanner(System.in);
        // Print the list of products
        System.out.println("\n----------------------------------------------------" +
                "\nProduct List\n");
        for (int i = 0; i < this.productList.size(); i++) {
            System.out.println((i + 1) + ". " +
                    this.productList.get(i).getBrand() + ", " +
                    this.productList.get(i).getModel() + ", " +
                    this.productList.get(i).getPrice());
        }
        // Ask the user which product they want to remove
        System.out.println("\n----------------------------------------------------" +
                "\nWhich product do you want to remove? Enter a number between 1 and " + this.productList.size() +
                "\nChoice: ");
        // Read the user's choice and check that it is valid
        int choice = scan.nextInt();
        if (choice >= 1 && choice <= this.productList.size()) {
            // Remove the product from the list using the remove method
            Product removed = this.productList.remove(choice - 1);
            // Print a confirmation message
            System.out.println("\nYou have removed " + removed.getBrand() + ", " + removed.getModel() + ", " + removed.getPrice());
        } else {
            // Print an error message
            System.out.println("\nInvalid choice. You must enter a number between 1 and " + this.productList.size());
        }
    }

    public void printAllProducts () {

        for (int i = 0; i < this.productList.size(); i++) {
            System.out.println((i + 1) + ". " +
                    this.productList.get(i).getBrand() + ", " +
                    this.productList.get(i).getModel() + ", " +
                    this.productList.get(i).getPrice());
        }
    }

    public boolean productExistsInList (String brandName, String modelName){
        for (Product product : productList) {
            if (product.getBrand().equals(brandName) && product.getModel().equals(modelName)) {
                return true;
            }
        }
        return false;
    }

    public void updateProductsTextFile () {
        try (PrintStream printStream = new PrintStream(new FileOutputStream(productFileName))) {
            for (Product product : productList) {
                String productData = product.formattedStringForFile();
                printStream.println(productData);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when we added Products to file " + e.getMessage());
        }
    }
}







