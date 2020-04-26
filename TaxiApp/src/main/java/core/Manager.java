package core;

public class Manager extends User {

    public Manager(int id, String login, PersonInfo personInfo) {
        super(id, login, personInfo, UserRole.MANAGER);
    }

    public void sendOrder(Driver driver, Order order) {
//        if (!driver.setNewOrder(order, ))
//            return;

    }

}
