package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Products {
        public ArrayList<Product> productsList = new ArrayList<Product>();
        public String productFileName = "products.txt";
        Products() {
            if (!createFileWithProducts()) {//Control if the file product.txt exist, if not it will call createFileWithCustomers
                createFileWithProducts();
            }
        }
        public ArrayList<Product> getProductsList() { return productsList; }
        public boolean createFileWithProducts() {
            File file = new File("products.txt");

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

        private void readProductsFromFile() {
            try {
                Scanner scan = new Scanner(new File(productFileName));
                while (scan.hasNextLine()) { //if file already exist, a Scanner will read every line of the file and seperate with ", "
                    String Products = scan.nextLine();
                    String[] productsInfo = Products.split(", ");

                    Product tempProducts = new Product( //creating a product object, with split values and this object will be added to products list
                            productsInfo[0],
                            Integer.parseInt(productsInfo[1]),
                            productsInfo[2]
                    );
                    productsList.add(tempProducts); //added to productList
                }
            } catch (FileNotFoundException e) {
                System.out.println("Wrong!" + e.getMessage());
            }
        }

        private boolean addProductToTextFile(Product newProduct)  {

            try{
                FileOutputStream fos = new FileOutputStream(this.productFileName, true); //fos created to write data to the file productFileName
                PrintStream printStream = new PrintStream(fos); //append: true indicates that data should be appended to the file if it already exists

                printStream.print("\n" + newProduct.formatedStringForFile()); //print the products information to the file
                System.out.println(newProduct.formatedStringForFile());

                fos.close();
                printStream.close();
                return true;                    //if it works without exceptions it will be true, else false!
            } catch (Exception e){
                System.out.println("Something went wrong when we added Customers to file " + e.getMessage());
            }

            return false;
        }
    }



