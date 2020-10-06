package taxiApp.springapp.services;

import taxiApp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.springapp.repos.*;
import taxiApp.springapp.services.representations.DriverRepresentation;
import taxiApp.springapp.services.representations.OrderRepresentation;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService extends UserService {

    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;
    private final ManagerRepository managerRepository;
    private final TaxiClientRepository clientRepository;

    public ManagerService(@Autowired MessageRepository messageRepository, @Autowired DriverRepository driverRepository,
                          @Autowired OrderRepository orderRepository, @Autowired ManagerRepository managerRepository,
                          @Autowired TaxiClientRepository clientRepository) {
        super(messageRepository);
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
        this.managerRepository = managerRepository;
        this.clientRepository = clientRepository;
    }

    public Manager getByLogin(String login) {
        return managerRepository.findByLogin(login);
    }

    public List<DriverRepresentation> getDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        List<DriverRepresentation> result = new ArrayList<>();
        for (Driver d : drivers)
            if (d.isActive())
                result.add(new DriverRepresentation(d));
        return result;
    }

    public List<OrderRepresentation> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderRepresentation> result = new ArrayList<>();
        for (Order o : orders)
            result.add(new OrderRepresentation(o));
        return result;
    }

    public void sendOrderToDriver(Manager manager, Long orderId, Long idDriver) {
        Driver driver = driverRepository.findById(idDriver).get();
        Message message = new Message(manager, driver, MessageType.ORDER, orderId);
        messageRepository.save(message);
    }

    public void sendAnswerToClient(Manager manager, Long orderId, boolean isAck) {
        Order order = orderRepository.findById(orderId).get();
        TaxiClient client = clientRepository.findById(order.getTaxiClient().getId()).get();
        order.setStatus(isAck ? OrderStatus.ACCEPTED : OrderStatus.DECLINED);
        Message message = new Message(manager, client, isAck ? MessageType.ACK : MessageType.NACK, null);
        messageRepository.save(message);
        orderRepository.save(order);
    }

    public void sendAnswerToDriver(Manager manager, Long driverId, boolean isAck) {
        Driver driver = driverRepository.findById(driverId).get();
        Message message = new Message(manager, driver, isAck ? MessageType.ACK : MessageType.NACK, null);
        messageRepository.save(message);
    }

    public void activateDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId).get();
        driver.setActive(true);
        driverRepository.save(driver);
    }

}
