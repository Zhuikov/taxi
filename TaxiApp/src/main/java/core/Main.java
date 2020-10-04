package core;

import Exceptions.NoEntityException;
import org.hibernate.Session;

import repository.CVRepository;
import repository.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NoEntityException {

//        CVRepository cvRepository = CVRepository.getInstance();
//        CV cv = new CV("Ivan", "Ivanova", "78273619312", 14);
//        long id = cvRepository.add(cv);

//        cv.setId(id);
//        cv.setRead(true);
//        CV cv = cvRepository.getById(6);
//        cv.setRead(true);
//        cvRepository.update(cv);
//        System.out.println(cv);

        Session session = SessionUtil.getSession();
        session.beginTransaction();

//        Order order = new Order("test1", "test2", null);
        Manager manager = new Manager("Manager", new PersonInfo("Ivam", "Ivamov", "213"));
        TaxiClient client = new TaxiClient("cl", new PersonInfo("Petr", "Petrov", "123"));
//        order.setStatus(OrderStatus.WAIT_FOR_DRIVER);

        System.out.println("Manager Id: " + manager.id);

//        session.save(order);
        session.save(manager);
        session.save(client);
        session.getTransaction().commit();
        List<Manager> managers = new ArrayList<>();
//        managers = session.createQuery("FROM Managers").list();
        for (User u : managers) {
            System.out.println("MANAGER: " + u);
        }
        SessionUtil.shutdown();
    }

}
