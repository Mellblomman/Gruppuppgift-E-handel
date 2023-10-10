package src;

public class Product {

    private String brand;
    private String model;
    private double price;


    public Product(String brand, String model,double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;

    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String formattedStringForFile() {
        return this.brand + "," + this.model + "," + this.price;
    }
    public String formattedToShoppingCart() {
        return this.brand + ", " + this.model + ", " + this.price;
    }
}

