package core;

import org.hibernate.Session;

import repository.SessionUtil;

public class Main {

    public static void main(String[] args) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();

        Order order = new Order("test1", "test2", null);
        order.setStatus(OrderStatus.WAIT_FOR_DRIVER);

        session.save(order);

        session.getTransaction().commit();
        SessionUtil.shutdown();
    }

}
