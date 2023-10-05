package src;

public class Order {
    /*
    Want to find the order with the customers social security number, can we make an inheritance from the class
    customer?
    */
    private String customerSSN;



    private String restOfOrderInfo;


    public Order(String customerSSN, String restOfOrderInfo) {
        this.customerSSN = customerSSN;
        this.restOfOrderInfo = restOfOrderInfo;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

    public String getRestOfOrderInfo() {
        return restOfOrderInfo;
    }

    public void setRestOfOrderInfo(String restOfOrderInfo) {
        this.restOfOrderInfo = restOfOrderInfo;
    }

    public String formattedStringsForFile() {
        return this.customerSSN + "," + this.restOfOrderInfo;
    }
}
