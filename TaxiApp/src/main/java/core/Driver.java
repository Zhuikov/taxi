package core;

import repository.OrderRepository;

public class Driver extends User {

    private final OrderRepository orderRepository = OrderRepository.getSingleton();

    private DriverStatus status = DriverStatus.FREE;
    private Car car;
    // todo check it when login
    private boolean active = false;
    private Order order = null;

    public Driver(int id, String login, PersonInfo personInfo, Car car) {
        super(id, login, personInfo, UserRole.DRIVER);
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

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean acceptOrder(Order order) {
        if (this.order != null || status != DriverStatus.FREE) {
            return false;
        }
//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, order.getManagerId(),
//                UserRole.MANAGER, MessageType.ACK, order.id
//        );
//        messageRepository.add(message);

        return true;
    }

    public boolean rejectOrder(Order order) {
//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, order.getManagerId(),
//                UserRole.MANAGER, MessageType.NACK, order.id
//        );
//        messageRepository.add(message);

        return true;
    }

    public void finishOrder(Order order) {

        order.setStatus(OrderStatus.FINISHED);

        this.order = null;
        this.status = DriverStatus.FREE;
    }

    public void assignOrder(Order order) {
        status = DriverStatus.BUSY;
        this.order = order;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
