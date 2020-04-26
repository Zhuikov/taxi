package core;

public class Order extends TaxiItem {

    private final String dstAddress;
    private final String srcAddress;
    private final TaxiClient taxiClient;
    private Driver driver = null;
    private OrderStatus status = OrderStatus.WAIT_FOR_DRIVER;

    public Order(int id, String srcAddress, String dstAddress, TaxiClient taxiClient) {
        super(id);
        this.srcAddress = srcAddress;
        this.dstAddress = dstAddress;
        this.taxiClient = taxiClient;
    }

    public String getDstAddress() {
        return dstAddress;
    }

    public String getSrcAddress() {
        return srcAddress;
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
