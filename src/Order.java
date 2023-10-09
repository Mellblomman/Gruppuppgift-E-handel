package src;

public class Order {
    private String customerSSN;
    private String restOfOrderInfo;


    public Order(String customerSSN, String restOfOrderInfo) {
        this.customerSSN = customerSSN;
        this.restOfOrderInfo = restOfOrderInfo;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getRestOfOrderInfo() {
        return restOfOrderInfo;
    }

    public String formattedStringsForFile() {
        return this.customerSSN + "," + this.restOfOrderInfo;
    }
}
