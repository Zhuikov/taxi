package taxiApp.core;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order extends TaxiItem {

    @Column
    private final String dstAddress;

    @Column
    private final String srcAddress;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private final TaxiClient taxiClient;

    @Enumerated
    @Column(name = "status", columnDefinition = "smallint")
    private OrderStatus status = OrderStatus.WAIT_FOR_DRIVER;

    public Order() {
        this.srcAddress = "";
        this.dstAddress = "";
        this.taxiClient = null;
    }

    public Order(String srcAddress, String dstAddress, TaxiClient taxiClient) {
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public TaxiClient getTaxiClient() {
        return taxiClient;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dstAddress='" + dstAddress + '\'' +
                ", srcAddress='" + srcAddress + '\'' +
                ", taxiClient=" + taxiClient +
                ", status=" + status +
                '}';
    }
}
