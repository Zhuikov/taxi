package taxiApp.core;

import taxiApp.Exceptions.NoEntityException;
import taxiApp.Exceptions.NoUnusedCarsException;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Owners")
@PrimaryKeyJoinColumn(name = "id_user")
public class CarsOwner extends User {

//    private final DriverRepository driverRepository = DriverRepository.getSingleton();
//    private final TaxiClientRepository taxiClientRepository = TaxiClientRepository.getSingleton();
//    private final CarRepository carRepository = CarRepository.getSingleton();
    @OneToMany(mappedBy="owner")
    private Set<Car> cars = new HashSet<>();

    public CarsOwner() {
        super();
    }

    public CarsOwner(String login, PersonInfo personInfo) {
        super(login, personInfo);
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

    public void addCar(Car car) {
        cars.add(car);
    }

    public Set<Car> getCars() {
        return cars;
    }
}
