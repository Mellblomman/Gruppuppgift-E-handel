package src;

public class Order {
    /*
    Want to find the order with the customers social security number, can we make an inheritance from the class
    customer?
    */
    private String nameOrder;
    private String product;
    private double totalSumOrder;
    private double dateAndTime;


    public Order(String nameOrder, String product, double totalSumOrder, double dateAndTime) {
        this.nameOrder = nameOrder;
        this.product = product;
        this.totalSumOrder = totalSumOrder;
        this.dateAndTime = dateAndTime;
    }

    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getTotalSumOrder() {
        return totalSumOrder;
    }

    public void setTotalSumOrder(double totalSumOrder) {
        this.totalSumOrder = totalSumOrder;
    }

    public double getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(double dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String formattedStringsForFile() {
        return this.nameOrder + "," + this.product + "," + this.totalSumOrder + "," + this.dateAndTime;
    }
}
