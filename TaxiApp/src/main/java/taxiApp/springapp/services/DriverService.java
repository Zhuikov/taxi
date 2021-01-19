package taxiApp.springapp.services;

import taxiApp.Exceptions.NoEntityException;
import taxiApp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.springapp.repos.DriverRepository;
import taxiApp.springapp.repos.ManagerRepository;
import taxiApp.springapp.repos.MessageRepository;
import taxiApp.springapp.repos.OrderRepository;
import taxiApp.springapp.services.representations.OrderRepresentation;

import java.util.List;
import java.util.Optional;

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

    public Driver getByLogin(String login) {
        return driverRepository.findByLogin(login);
    }

    public Order getOrder(Long id) {
//        Driver driver = driverRepository.findById(id).get();
        return orderRepository.findById(id).get();
    }

    public Order getCurrentOrder(Driver driver) {
        return driver.getOrder();
    }

    public void processOrder(Driver driver, boolean isAck) {
        List<Manager> managers = managerRepository.findAll();
        for (Manager m : managers) {
            Message message = new Message(driver, m, isAck ? MessageType.ACK : MessageType.NACK, null);
            messageRepository.save(message);
        }
    }

    public void finishOrder(Driver driver, Long idOrder) throws NoEntityException {
        Optional<Order> order = orderRepository.findById(idOrder);
        if (!order.isPresent())
            throw new NoEntityException(idOrder, "order");
        order.get().setStatus(OrderStatus.FINISHED);
        driver.setOrder(null);
        driver.setStatus(DriverStatus.FREE);
        driverRepository.save(driver);
        orderRepository.save(order.get());
    }

    public void setOrder(Driver driver, Long idOrder) throws NoEntityException {
        Optional<Order> order = orderRepository.findById(idOrder);
        if (!order.isPresent())
            throw new NoEntityException(idOrder, "order");
        order.get().setStatus(OrderStatus.ACCEPTED);
        driver.setOrder(order.get());
        driver.setStatus(DriverStatus.BUSY);
        driverRepository.save(driver);
        orderRepository.save(order.get());
    }
}
