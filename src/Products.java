package src;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Products {
public ArrayList<Product> productList = new ArrayList<>();
private String productFileName = "products.txt";

Scanner scanInput = new Scanner(System.in);


    public Products(){
        readProductsFromFile(); //(1)
    }
    public void readProductsFromFile(){ //(1)
        if (!createFileWithProducts()) {
            try {
                Scanner scan = new Scanner(new File(productFileName));
                while (scan.hasNextLine()) { //if file already exist, a Scanner will read every line of the file and seperate with ", "
                    String Products = scan.nextLine();
                    String[] productsInfo = Products.split(",");

                    if(productsInfo.length>=3) {
                        Product tempProducts = new Product( //creating a product object, with split values and this object will be added to products list
                                productsInfo[0],
                                productsInfo[1],
                                Double.parseDouble(productsInfo[2])
                        );
                        productList.add(tempProducts); //added to productList
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Wrong!" + e.getMessage());
            }
        }
    }

    public boolean createFileWithProducts() {  //(2)
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
    public void printAllProducts() { //(3)
        System.out.println("All products: ");

        for (int i = 0; i < this.productList.size(); i++) {
            System.out.println((i + 1) + ". " +
                    "Brand: " +
                    this.productList.get(i).getBrand() + ", " +
                    "Model: " +
                    this.productList.get(i).getModel() + ", " +
                    "Price: $" +
                    this.productList.get(i).getPrice());
        }
    }

    public void addNewProduct() { //(4)
        boolean run = true;
        while (run) {
            System.out.println("\n----------------------------------------------------" +
                    "\nEnter the brand of the product: ");
            String brandOfProduct = scanInput.next();
            System.out.println("\nEnter the model of the product: ");
            String modelOfProduct = scanInput.next();
            System.out.println("\nEnter the price for the product: ");
            String priceProduct = scanInput.next();
            if (productExistsInList(brandOfProduct, modelOfProduct)) {
                System.out.println("Product already exists! Please log in.");

            }else {
                Product newProduct = new Product(brandOfProduct, modelOfProduct, Double.parseDouble(priceProduct)); //Creating new Customer
                productList.add(newProduct); //adding to list
                System.out.println("\nProduct added! " + "\nBrand: " + brandOfProduct + ", " + "\nModel: " + modelOfProduct + ", " + "\nPrice: $" + priceProduct);
            }
            while(true) {
                System.out.println("\nDo you want to add another product? \n(Yes/No)" +
                        "\n----------------------------------------------------" +
                        "\nChoice: ");
                String yesOrNo = scanInput.next();
                if (yesOrNo.equalsIgnoreCase("Yes")) {
                    break;
                } else if (yesOrNo.equalsIgnoreCase("No")) {
                    run = false;
                    break;
                }else{
                    System.out.println("\nInvalid input");
                }
            }
            updateProductsTextFile();
        }
    }
    public boolean productExistsInList (String brandName, String modelName){ //(4)
        for (Product product : productList) {
            if (product.getBrand().equals(brandName) && product.getModel().equals(modelName)) {
                return true;
            }
        }
        return false;
    }
    public void removeProductFromList() { //(5)
        printAllProducts();

        boolean run = true;
        while (run) {
            System.out.println("\n----------------------------------------------------" +
                    "\nWhich product do you want to remove? \nEnter a number between 1 and " + this.productList.size() +
                    "\nPress 0 to go back" +
                    "\nChoice: ");
            int productIndex = scanInput.nextInt();

            if (productIndex == 0) {
                run = false;
            }
            if (productIndex >= 1 && productIndex <= this.productList.size()) {
                // Remove the product from the list using the remove method
                Product removed = this.productList.remove(productIndex - 1);
                // Print a confirmation message
                System.out.println("\nYou have removed " + "\nBrand: " + removed.getBrand() + ", " + "\nModel: " + removed.getModel() + ", " + "\nPrice: $" + removed.getPrice());
            } else if (productIndex > productList.size()) {
                // Print an error message
                System.out.println("\nInvalid choice. You must enter a number between 1 and " + this.productList.size());
            }
        }
    }
    public void editProductInformation() { //(6)

        // Display the list of products with their index numbers
        printAllProducts();

        // Prompt the user to select a customer to edit
        boolean run = true;
        while (run) {
            System.out.print("\nEnter the number of the product you want to edit: " +
                    "\nPress 0 to go back" +
                    "\nChoice: ");
            int productIndex = scanInput.nextInt();

            if (productIndex == 0) {
                run = false;
            }

            // Check if the provided index is valid
            if (productIndex >= 1 && productIndex <= productList.size()) {
                Product productToEdit = productList.get(productIndex - 1);

                // Prompt the user to choose which information to edit
                System.out.println("\n----------------------------------------------------" +
                        "\nEdit Product Information ");
                System.out.println("\n1. Name of brand (Current: " + productToEdit.getBrand() + ")");
                System.out.println("2. Name of model (Current: " + productToEdit.getModel() + ")");
                System.out.println("3. Product price (Current: $" + productToEdit.getPrice() + ")");
                System.out.println("0. Go back");
                System.out.print("\nChoice: ");
                String infoChoice = scanInput.next();

                // Prompt the user for the updated value based on their choice
                while(run) {
                    String newValue = "";
                    switch (infoChoice) {
                        case "1":
                            System.out.print("\n----------------------------------------------------" +
                                    "\nEnter new brand name: ");
                            newValue = scanInput.next();
                            productToEdit.setBrand(newValue);
                            break;
                        case "2":
                            System.out.print("\n----------------------------------------------------" +
                                    "\nEnter new model name: ");
                            newValue = scanInput.next();
                            productToEdit.setModel(newValue);
                            break;
                        case "3":
                            System.out.print("\n----------------------------------------------------" +
                                    "\nEnter new price: ");
                            newValue = scanInput.next();
                            productToEdit.setPrice(Double.parseDouble(newValue));
                            break;
                        case "0":
                            System.out.println("Go back");
                            break;
                        default:
                            System.out.println("\nInvalid choice. No changes made.");
                            break;
                    }

                    if (!newValue.isEmpty()) {
                        System.out.println("\nProduct information updated.");
                    }
                    run = false;

                }

            } else if(productIndex > productList.size()) {
                System.out.println("Invalid product index. Please enter a valid index.");
            }
        } updateProductsTextFile();
    }

    public void updateProductsTextFile() { //(7)
        try (PrintStream printStream = new PrintStream(new FileOutputStream(productFileName, false))) {
            for (Product product : productList) {
                String productData = product.formattedStringForFile();
                printStream.println(productData);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when we added Products to file " + e.getMessage());
        }
    }
}







