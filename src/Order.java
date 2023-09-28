package src;

public class Order {
    /*
        //Want to find the order with the customers social security number, can we make an inheritance from the class
        customer?
         */
    private String nameOrder; //See comment on line 3.
    private String product;
    private double totalSumOrder;
    private double time;            //NOT DONE, fix after leccure
    private double date;            //NOT DONE, fix after leccure
    public Order(String nameOrder, String product, double totalSumOrder, double time, double date) {
        this.nameOrder = nameOrder;
        this.product = product;
        this.totalSumOrder = totalSumOrder;
        this.time = time;
        this.date = date;
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

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public String formatedStringForFile() {         //Probably need to be changed after DateTime
        return this.nameOrder + "," + this.product + "," + this.totalSumOrder + "," + this.time + "," + this.date;
    }
}
