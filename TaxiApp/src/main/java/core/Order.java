package core;

public class Order extends TaxiItem {

    private final String dstAddress;
    private final String srcAddress;
    private final int taxiClientId;
    private Integer managerId = null;
    private Integer driverId = null;
    private OrderStatus status = OrderStatus.WAIT_FOR_DRIVER;

    public Order(int id, String srcAddress, String dstAddress, int taxiClientId) {
        super(id);
        this.srcAddress = srcAddress;
        this.dstAddress = dstAddress;
        this.taxiClientId = taxiClientId;
    }

    public String getDstAddress() {
        return dstAddress;
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public int getTaxiClientId() {
        return taxiClientId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}
