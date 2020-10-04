package core;

import Exceptions.NoEntityException;
import Exceptions.NoUnusedCarsException;

import javax.persistence.Entity;
import javax.persistence.Table;
//import repository.CarRepository;
//import repository.DriverRepository;
//import repository.TaxiClientRepository;

@Entity
@Table(name = "Owners")
public class CarsOwner extends User {

//    private final DriverRepository driverRepository = DriverRepository.getSingleton();
//    private final TaxiClientRepository taxiClientRepository = TaxiClientRepository.getSingleton();
//    private final CarRepository carRepository = CarRepository.getSingleton();

    public CarsOwner(int id, String login, PersonInfo personInfo) {
        super(login, personInfo, UserRole.OWNER);
    }

    public void sendReplyToClient(CV cv, TaxiClient client, MessageType type) {
//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, client.getId(),
//                UserRole.CLIENT, type, cv.getId()
//        );
//        messageRepository.add(message);
    }

    public void createDriver(User client) throws NoUnusedCarsException, NoEntityException {
//        int driverId = driverRepository.getUnusedId();
//        Car car = carRepository.getUnusedCar();
//        String clientLogin = taxiClientRepository.getById(client.getId()).getLogin();
//        Driver driver = new Driver(driverId, clientLogin, client.personInfo, car);
//        driverRepository.add(driver);

//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, null,
//                UserRole.MANAGER, MessageType.DRIVER, driver.getId()
//        );
//        messageRepository.add(message);
    }

}
