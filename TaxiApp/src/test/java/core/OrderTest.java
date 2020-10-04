package core;

import Exceptions.NoEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
//import repository.MessageRepository;
//import repository.OrderRepository;

import java.util.List;


class OrderTest {

//    private final MessageRepository messageRepository = MessageRepository.getSingleton();
//    private final OrderRepository orderRepository = OrderRepository.getSingleton();

    private static final int taxiClientId = 0;
    private static final int driverId = 0;
    private static final int managerId = 0;
    private static final String srcAddr = "Src1";
    private static final String dstAddr = "Dst";

    private CarsOwner owner;
//    private final Driver driver = new Driver(driverId, "driver1",
//            new PersonInfo("FirstName", "SecondName", "8-888-888-88-88"));
//    private final TaxiClient taxiClient = new TaxiClient(taxiClientId, "taxiClient1",
//            new PersonInfo("Client1", "Client1", "23423432443"));
//    private final Manager manager = new Manager(managerId, "manager1",
//            new PersonInfo("Manager1", "Manager1", "213"));

    @BeforeEach
    private void initOrder() {
//        messageRepository.removeAll();
//        orderRepository.removeAll();
    }

    @Test
    public void createOrder() {
//        Order order = taxiClient.createOrder(srcAddr, dstAddr);
//        taxiClient.sendOrder(order);

//        List<Message> messages = messageRepository.getAll();
//        Assertions.assertEquals(messages.size(), 1);

//        Message messageFromRepo = messages.get(0);
//        Assertions.assertNull(messageFromRepo.getRecipientId());
//        Assertions.assertFalse(messageFromRepo.isRead());
//        Assertions.assertEquals(messageFromRepo.getRecipientRole(), UserRole.MANAGER);
//        Assertions.assertEquals(messageFromRepo.getSenderId(), taxiClientId);
//        Assertions.assertEquals(messageFromRepo.getType(), MessageType.ORDER);
//        Assertions.assertEquals(messageFromRepo.getDataId(), order.getId());

//        List<Order> orders = orderRepository.getAll();
//        Assertions.assertEquals(orders.size(), 1);
//
//        Order orderFromRepo = orders.get(0);
//        Assertions.assertEquals(orderFromRepo, order);
    }

    @Test
    public void sendOrderToDriverTest() throws NoEntityException {
//        Order order = taxiClient.createOrder(srcAddr, dstAddr);
//        taxiClient.sendOrder(order);

//        Assertions.assertEquals(messageRepository.getAll().size(), 1);

//        List<Message> messages = manager.getMessagesByRole(true);
//        Assertions.assertEquals(messages.size(), 1);
//
//        Message messageForManager = messages.get(0);
//        Assertions.assertEquals(messageForManager.getDataId(), order.getId());

//        Order orderFromRepo = orderRepository.getById(messageForManager.getDataId());
//        Assertions.assertEquals(orderFromRepo, order);

        // manager action
//        manager.assignOrder(order);
//        Assertions.assertEquals(order.getManagerId(), managerId);
//        manager.sendOrderToDriver(order, driver);

//        Assertions.assertEquals(messageRepository.getAll().size(), 2);

//        messages = driver.getMessagesByUser(true);
//
//        Assertions.assertEquals(messages.size(), 1);
//
//        Message messageForDriver = messages.get(0);
//        Assertions.assertEquals(messageForDriver.getDataId(), order.getId());

        // driver action
//        Assertions.assertTrue(driver.acceptOrder(orderFromRepo));

//        Assertions.assertEquals(messageRepository.getAll().size(), 3);

        // manager action
//        messages = manager.getMessagesByUser(true);
//        Assertions.assertEquals(messages.size(), 1);
//
//        Message messageFromDriver = messages.stream().filter(m -> m.getType() == MessageType.ACK).findFirst().get();
//        Assertions.assertEquals(messageFromDriver.getDataId(), order.getId());
//        Assertions.assertEquals(messageFromDriver.getSenderRole(), UserRole.DRIVER);
//        Assertions.assertEquals(messageFromDriver.getRecipientRole(), UserRole.MANAGER);

//        manager.setDriverToOrder(order, driver);
//        Assertions.assertEquals(order.getDriverId(), driver.getId());
//        Assertions.assertEquals(order.getStatus(), OrderStatus.ACCEPTED);
//        Assertions.assertEquals(messageRepository.getAll().size(), 4);

//        messages = driver.getMessagesByUser(true);
//        Assertions.assertEquals(messages.size(), 2);
//        Message messageFromManager = messages.stream().filter(m -> m.getType() == MessageType.ACK).findFirst().get();
//        Assertions.assertEquals(messageFromManager.getType(), MessageType.ACK);
//        Assertions.assertEquals(messageFromManager.getDataId(), order.getId());

//        manager.sendReplyToClient(order, taxiClient.getId(), MessageType.ACK);
//        Assertions.assertEquals(messageRepository.getAll().size(), 5);

        // taxiClient
//        messages = taxiClient.getMessagesByUser(true);
//        Assertions.assertEquals(messages.size(), 1);
//        Message messageForTaxiClient = messages.get(0);
//        Assertions.assertEquals(messageForTaxiClient.getType(), MessageType.ACK);
//        Assertions.assertEquals(messageForTaxiClient.getSenderId(), manager.getId());
//        Assertions.assertEquals(messageForTaxiClient.getSenderRole(), UserRole.MANAGER);
//        Assertions.assertEquals(messageForTaxiClient.getDataId(), order.getId());
    }
}