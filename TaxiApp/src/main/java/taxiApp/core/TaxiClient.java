package taxiApp.core;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
//import taxiApp.repository.OrderRepository;

@Entity
@Table(name = "Clients")
@PrimaryKeyJoinColumn(name = "id_user")
public class TaxiClient extends User {

//    private final OrderRepository orderRepository = OrderRepository.getSingleton();
//    private final CVRepository cvRepository = CVRepository.getInstance();

    public TaxiClient(String login, PersonInfo personInfo) {
        super(login, personInfo);
    }

//    public Order createOrder(String srcAddress, String dstAddress) {
//        return new Order(orderRepository.getUnusedId(), srcAddress, dstAddress, id);
//    }

    public boolean sendOrder(Order order) {
        // todo try-catch
//        orderRepository.add(order);
//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, null,
//                UserRole.MANAGER, MessageType.ORDER, order.getId()
//        );
//        messageRepository.add(message);
        return true;
    }

//    public CV createCV(int experience) {
//        return new CV(cvRepository.getUnusedId(), id, experience);
//    }

//    public boolean sendCV(CV cv) {
//        // todo try-catch
//        cvRepository.add(cv);
//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, null,
//                UserRole.OWNER, MessageType.CV, cv.id
//        );
//        messageRepository.add(message);
//        return true;
//    }

}
