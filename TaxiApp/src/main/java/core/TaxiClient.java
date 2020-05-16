package core;

import repository.CVRepository;
import repository.OrderRepository;

public class TaxiClient extends User {

    private final OrderRepository orderRepository = OrderRepository.getSingleton();
    private final CVRepository cvRepository = CVRepository.getSingleton();

    public TaxiClient(int id, String login, PersonInfo personInfo) {
        super(id, login, personInfo, UserRole.CLIENT);
    }

    public Order createOrder(String srcAddress, String dstAddress) {
        return new Order(orderRepository.getUnusedId(), srcAddress, dstAddress, id);
    }

    public boolean sendOrder(Order order) {
        // todo try-catch
        orderRepository.add(order);
        Message message = new Message(
                messageRepository.getUnusedId(), id, role, null,
                UserRole.MANAGER, MessageType.ORDER, order.getId()
        );
        messageRepository.add(message);
        return true;
    }

    public CV createCV(int experience) {
        return new CV(cvRepository.getUnusedId(), id, experience);
    }

    public boolean sendCV(CV cv) {
        // todo try-catch
        cvRepository.add(cv);
        Message message = new Message(
                messageRepository.getUnusedId(), id, role, null,
                UserRole.OWNER, MessageType.CV, cv.id
        );
        messageRepository.add(message);
        return true;
    }

}
