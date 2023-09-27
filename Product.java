public class Product {
    public String name;
    public double price;
    public int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        Product p1 = new Product("mlss laptop", 10.000,50);
        Product p2 = new Product("mlss pro laptop", 20.000,100);

    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int amount) {
        stock -= amount;
    }
}

