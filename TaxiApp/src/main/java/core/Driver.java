package core;

public class Driver {

    private final String fName;
    private final String sName;
    private String phone;

    private DriverStatus status;
    private Car car = null;
    private Order order = null;
    private Order newOrder = null;

    public Driver(String fName, String sName, String phone) {
        this.fName = fName;
        this.sName = sName;
        this.phone = phone;
    }

    public String getFName() {
        return fName;
    }

    public String getSName() {
        return sName;
    }

    public String getPhone() {
        return phone;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public Car getCar() {
        return car;
    }

    public Order getOrder() {
        return order;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public void setCar(Car car) {
        this.car.setUsed(false);
        car.setUsed(true);
        this.car = car;
    }

    /**
     * We can send order to driver in 2 cases:
     *  - He hasn't got a newOrder
     *  - His newOrder has been finished or accepted by another driver already.
     * @return true if newOrder sets, otherwise -- false.
     */
    public boolean sendOrder(Order order) {
        if (this.newOrder == null || this.newOrder.getStatus() != OrderStatus.WAIT_FOR_DRIVER) {
            this.newOrder = order;
            return true;
        }
        return false;
    }

    /**
     * Driver can accept order in 1 case:
     *  - He hasn't order, he has newOrder and newOrder is WAIT_FOR_DRIVERS
     * @return True if newOrder was accepted; false otherwise
     * In any case sets newOrder = null
     */
    public boolean acceptOrder() {
        if (order == null && newOrder != null && newOrder.getStatus() == OrderStatus.WAIT_FOR_DRIVER) {
            newOrder.setStatus(OrderStatus.ACCEPTED);
            order = newOrder;
            newOrder = null;
            return true;
        }
//        newOrder = null;
        return false;
    }

    public void rejectOrder() {
        newOrder = null;
    }

    public void finishOrder() {
        if (order != null) {
            order.setStatus(OrderStatus.FINISHED);
            order = null;
        }
    }
}
