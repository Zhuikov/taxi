package core;


public class TaxiClient extends User {

    public TaxiClient(int id, String login, PersonInfo personInfo) {
        super(id, login, personInfo, UserRole.CLIENT);
    }

    public Order createOrder(String srcAddress, String dstAddress) {
        //TODO ask id.
        return new Order(0, srcAddress, dstAddress, this);
    }

    public boolean sendOrder(Order order) {
        //TODO order to orderRepository -- OrderRepository.add(order);
        Message message =
                new Message(this, UserRole.MANAGER, null, MessageType.ORDER, order.getId());
        // MessageManager.sendToManager(message);
        return true;
    }

}
