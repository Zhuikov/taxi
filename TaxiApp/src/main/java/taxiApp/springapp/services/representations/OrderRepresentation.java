package taxiApp.springapp.services.representations;

import taxiApp.core.Order;

public class OrderRepresentation {

    private final String dstAddress;
    private final String srcAddress;
    private final String status;
    private final Long id;
    private final Long idClient;

    public OrderRepresentation(Order order) {
        dstAddress = order.getDstAddress();
        srcAddress = order.getSrcAddress();
        status = order.getStatus().toString();
        id = order.getId();
        idClient = order.getTaxiClient().getId();
    }

    public String getDstAddress() {
        return dstAddress;
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public String getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public Long getIdClient() {
        return idClient;
    }
}
