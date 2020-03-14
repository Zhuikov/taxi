package core;

public class Order {

    private final String address;
    private final TaxiClient taxiClient;
    private OrderStatus status;

    public Order(String address, TaxiClient taxiClient) {
        this.address = address;
        this.taxiClient = taxiClient;
        this.status = OrderStatus.WAIT_FOR_DRIVER;
    }

    public String getAddress() {
        return address;
    }

    public TaxiClient getTaxiClient() {
        return taxiClient;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
