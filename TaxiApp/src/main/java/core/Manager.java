package core;

import Exceptions.NoEntityException;
import repository.DriverRepository;
import repository.OrderRepository;

public class Manager extends User {

    private final OrderRepository orderRepository = OrderRepository.getSingleton();
    private final DriverRepository driverRepository = DriverRepository.getSingleton();

    public Manager(int id, String login, PersonInfo personInfo) {
        super(id, login, personInfo, UserRole.MANAGER);
    }

    /**
     * Set order to manager for processing
     * Returns false if order has managerId already
     */
    public boolean assignOrder(Order order) {
//        if (order.getManagerId() != null)
//            return false;
//        order.setManagerId(id);
        return true;
    }

    /**
     * Send order to driver
     * Creates message and add it to messageRepository
     */
    public void sendOrderToDriver(Order order, Driver driver) {
        Message message = new Message(
                messageRepository.getUnusedId(), id, role, driver.id,
                UserRole.DRIVER, MessageType.ORDER, order.id
        );
        messageRepository.add(message);
    }

    /**
     * Assign driver to order. Sends message ACK to driver
     * Returns false if order has driverId already
     */
    public boolean setDriverToOrder(Order order, Driver driver) {
//        if (order.getDriverId() != null)
//            return false;
//
//        order.setDriverId(driver.getId());
        order.setStatus(OrderStatus.ACCEPTED);

        Message message = new Message(
                messageRepository.getUnusedId(), id, role, driver.id,
                UserRole.DRIVER, MessageType.ACK, order.id
        );
        messageRepository.add(message);

        return true;
    }

    /**
     * Reject driver's ACK
     * Use it when several drivers sent ACK for one order
     */
    public boolean rejectDriverToOrder(Order order, Driver driver) {
//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, driver.getId(),
//                UserRole.DRIVER, MessageType.NACK, order.getId()
//        );
//        messageRepository.add(message);

        return true;
    }

    /**
     * Sends ACK message to client for order
     */
    public void sendReplyToClient(Order order, int clientId, MessageType type) {
//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, clientId,
//                UserRole.CLIENT, type, order.getId()
//        );
//        messageRepository.add(message);
    }

    /**
     * Set driver.active = true
     * so driver can login
     */
    // todo maybe use Driver driver instead of driverId
    public void setDriverActivity(int driverId) throws NoEntityException {
//        Driver driver = driverRepository.getById(driverId);
//        driver.setActive(true);
    }

}
