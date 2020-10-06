package taxiApp.springapp.services;

import taxiApp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.springapp.repos.DriverRepository;
import taxiApp.springapp.repos.ManagerRepository;
import taxiApp.springapp.repos.MessageRepository;
import taxiApp.springapp.repos.OrderRepository;

import java.util.List;

@Service
public class DriverService extends UserService {

    private final OrderRepository orderRepository;
    private final ManagerRepository managerRepository;
    private final DriverRepository driverRepository;

    public DriverService(@Autowired MessageRepository messageRepository, @Autowired OrderRepository orderRepository,
                         @Autowired ManagerRepository managerRepository, @Autowired DriverRepository driverRepository) {
        super(messageRepository);
        this.orderRepository = orderRepository;
        this.managerRepository = managerRepository;
        this.driverRepository = driverRepository;
    }

    public String getName(Long id) {
        Driver driver = driverRepository.findById(id).get();
        return driver.getPersonInfo().toString();
    }

    public Driver getByLogin(String login) {
        return driverRepository.findByLogin(login);
    }

//    public Driver getById(Long id) {
//        return driverRepository.findById(id).get();
//    }

    // todo method for manager
//    public List<Order> getOrders(Long id) {
//        List<Message> orderMessages = messageRepository.findByUserId(id)
//                .stream()
//                .filter(message -> message.getType() == MessageType.ORDER)
//                .collect(Collectors.toList());
//        List<Order> orders = new ArrayList<>();
//        for (Message message : orderMessages)
//            orders.add(orderRepository.findById(message.getPayload()).get());
//        return orders;
//    }

    public Order getOrder(Long id) {
        Driver driver = driverRepository.findById(id).get();
        return driver.getOrder();
    }

    public void processOrder(Driver driver, boolean isAck) {
        List<Manager> managers = managerRepository.findAll();
        for (Manager m : managers) {
            Message message = new Message(driver, m, isAck ? MessageType.ACK : MessageType.NACK, null);
            messageRepository.save(message);
        }
    }

    public void finishOrder(Driver driver, Long idOrder) {
        Order order = orderRepository.findById(idOrder).get();
        order.setStatus(OrderStatus.FINISHED);
        driver.setOrder(null);
        driver.setStatus(DriverStatus.FREE);
        driverRepository.save(driver);
        orderRepository.save(order);
    }

    public void setOrder(Driver driver, Long idOrder) {
        Order order = orderRepository.findById(idOrder).get();
        order.setStatus(OrderStatus.ACCEPTED);
        driver.setOrder(order);
        driver.setStatus(DriverStatus.BUSY);
        driverRepository.save(driver);
        orderRepository.save(order);
    }
}
