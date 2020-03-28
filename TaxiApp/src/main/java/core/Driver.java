package core;

public class Driver extends User {

    private DriverStatus status = DriverStatus.FREE;
    private Car car;
    private Order order = null;
    private Order newOrder = null;

    public Driver(int userId, PersonInfo personInfo, Car car) {
        super(userId, personInfo);
        this.car = car;
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

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public void setCar(Car car) {
        this.car.setUsed(false);
        car.setUsed(true);
        this.car = car;
    }

    /**
     * We can successfully set new order to driver in 2 cases:
     *  - He hasn't got a newOrder
     *  - His newOrder has been finished or accepted by another driver already.
     * @return true if newOrder sets, otherwise -- false.
     */
    public boolean setNewOrder(Order order) {
        if (this.newOrder == null || this.newOrder.getStatus() != OrderStatus.WAIT_FOR_DRIVER) {
            this.newOrder = order;
            return true;
        }
        return false;
    }

    /**
     * Driver can accept order in 1 case:
     *  - He hasn't order, he has newOrder, newOrder is WAIT_FOR_DRIVERS and driver is FREE
     * @return True if newOrder was accepted; false otherwise
     */
    public boolean acceptOrder() {
        if (order == null && newOrder != null
                && newOrder.getStatus() == OrderStatus.WAIT_FOR_DRIVER && status == DriverStatus.FREE) {
            newOrder.setStatus(OrderStatus.ACCEPTED);
            status = DriverStatus.BUSY;
            order = newOrder;
            newOrder = null;
            return true;
        }
        return false;
    }

    public void rejectOrder() {
        newOrder = null;
    }

    public void finishOrder() {
        if (order != null) {
            order.setStatus(OrderStatus.FINISHED);
            order = null;
            status = DriverStatus.FREE;
        }
    }
}
