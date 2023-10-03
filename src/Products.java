package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Products {
    public ArrayList<Product> productsList = new ArrayList<Product>();
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
                            Integer.parseInt(productsInfo[2]));
                    productsList.add(tempProducts); //added to productList
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


    public boolean addProductToTextFile (Product newProduct){

        try {
            FileOutputStream fos = new FileOutputStream(this.productFileName, true); //fos created to write data to the file productFileName
            PrintStream printStream = new PrintStream(fos); //append: true indicates that data should be appended to the file if it already exists

            printStream.print("\n" + newProduct.formatedStringForFile()); //print the products information to the file
            System.out.println(newProduct.formatedStringForFile());

            fos.close();
            printStream.close();
            return true;                    //if it works without exceptions it will be true, else false!
        } catch (Exception e) {
            System.out.println("Product added successfully." + e.getMessage());
        }

        return false;
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

    public void printProducts () {
        try {
            // Specify the path to the products.txt file
            String filePath = "products.txt";

            // Create a File object to represent the file
            File file = new File(filePath);

            // Create a Scanner to read the file
            Scanner fileScanner = new Scanner(file);

            // Read and print each line from the file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }

            // Close the file scanner
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: products.txt");

        }
    }
}




