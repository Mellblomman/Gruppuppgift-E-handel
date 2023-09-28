package src;

public class Product {
    public String brand;
    public String model;
    public int price;


    public Product(String brand, int price, String model) {
        this.brand = brand;
        this.price = price;
        this.model = model;
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

    public String formatedStringForFile() {
        return this.brand + "," + this.model + "," + this.price;


    }
}

