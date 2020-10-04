package taxiApp.springapp.services;

import taxiApp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.springapp.repos.*;

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

//    public Manager getById(Long managerId) { return managerRepository.findById(managerId).get(); }

    public List<Driver> getDrivers() { return driverRepository.findAll(); }

    public void sendOrderToDriver(Long id, Long orderId, Long idDriver) {
        Manager manager = managerRepository.findById(id).get();
        Driver driver = driverRepository.findById(idDriver).get();
        Message message = new Message(manager, driver, MessageType.ORDER, orderId);
        messageRepository.save(message);
    }

    public void sendAnswerToClient(Long id, Long orderId, boolean isAck) {
        Manager manager = managerRepository.findById(id).get();
        Order order = orderRepository.findById(orderId).get();
        TaxiClient client = clientRepository.findById(order.getTaxiClient().getId()).get();
        order.setStatus(isAck ? OrderStatus.ACCEPTED : OrderStatus.DECLINED);
        Message message = new Message(manager, client, isAck ? MessageType.ACK : MessageType.NACK, null);
        messageRepository.save(message);
        orderRepository.save(order);
    }

    public void sendAnswerToDriver(Long id, Long driverId, boolean isAck) {
        Manager manager = managerRepository.findById(id).get();
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
