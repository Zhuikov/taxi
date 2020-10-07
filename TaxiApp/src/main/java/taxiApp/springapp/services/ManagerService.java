package taxiApp.springapp.services;

import taxiApp.Exceptions.NoEntityException;
import taxiApp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.springapp.repos.*;
import taxiApp.springapp.services.representations.DriverRepresentation;
import taxiApp.springapp.services.representations.OrderRepresentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Driver> getAllDrivers () {
        return driverRepository.findAll();
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

    public void sendOrderToDriver(Manager manager, Long orderId, Long idDriver) throws NoEntityException {
        Optional<Driver> driver = driverRepository.findById(idDriver);
        if (!driver.isPresent())
            throw new NoEntityException(idDriver);
        Message message = new Message(manager, driver.get(), MessageType.ORDER, orderId);
        messageRepository.save(message);
    }

    public void sendAnswerToClient(Manager manager, Long orderId, boolean isAck) throws NoEntityException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (!order.isPresent())
            throw new NoEntityException(orderId);
        Optional<TaxiClient> client = clientRepository.findById(order.get().getTaxiClient().getId());
        order.get().setStatus(isAck ? OrderStatus.ACCEPTED : OrderStatus.DECLINED);
        if (!client.isPresent())
            throw new NoEntityException(orderId);
        Message message = new Message(manager, client.get(), isAck ? MessageType.ACK : MessageType.NACK, null);
        messageRepository.save(message);
        orderRepository.save(order.get());
    }

    public void sendAnswerToDriver(Manager manager, Long driverId, boolean isAck) throws NoEntityException {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (!driver.isPresent())
            throw new NoEntityException(driverId);
        Message message = new Message(manager, driver.get(), isAck ? MessageType.ACK : MessageType.NACK, null);
        messageRepository.save(message);
    }

    public void activateDriver(Long driverId) throws NoEntityException {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (!driver.isPresent())
            throw new NoEntityException(driverId);
        driver.get().setActive(true);
        driverRepository.save(driver.get());
    }

}
