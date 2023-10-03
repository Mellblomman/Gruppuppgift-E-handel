package src;

public class Product {

    public String brand;
    public String model;
    public String price;


    public Product(String brand, String model,String price) {
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

    public String getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String formatedStringForFile() {
        return this.brand + "," + this.model + "," + this.price;
    }
}

