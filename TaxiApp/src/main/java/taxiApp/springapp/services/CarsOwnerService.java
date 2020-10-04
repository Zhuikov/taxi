package taxiApp.springapp.services;

import taxiApp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.springapp.repos.*;

import java.util.List;

@Service
public class CarsOwnerService extends UserService {

    private final CVRepository cvRepository;
    private final CarRepository carRepository;
    private final CarsOwnerRepository ownerRepository;
    private final DriverRepository driverRepository;
    private final ManagerRepository managerRepository;
    private final TaxiClientRepository clientRepository;

    public CarsOwnerService(@Autowired MessageRepository messageRepository, @Autowired CVRepository cvRepository,
                            @Autowired  CarRepository carRepository, @Autowired CarsOwnerRepository ownerRepository,
                            @Autowired DriverRepository driverRepository, @Autowired ManagerRepository managerRepository,
                            @Autowired TaxiClientRepository clientRepository) {
        super(messageRepository);
        this.cvRepository = cvRepository;
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
        this.driverRepository = driverRepository;
        this.managerRepository = managerRepository;
        this.clientRepository = clientRepository;
    }

    public CarsOwner getByLogin(String login) {
        return ownerRepository.findByLogin(login);
    }

    public List<Car> getCars(Long ownerId) {
        return carRepository.findAllByOwner(ownerId);
    }

    public void nackCV(Long id, Long cvId) {
        CV cv = cvRepository.findById(cvId).get();
        CarsOwner owner = ownerRepository.findById(id).get();
        TaxiClient client = clientRepository.findById(cv.getUserId()).get();
        cv.setShown(true);
        Message message = new Message(owner, client, MessageType.NACK, null);
        messageRepository.save(message);
        cvRepository.save(cv);
    }

    public void ackCV(Long id, Long cvId, Long carId) {
        CV cv = cvRepository.findById(cvId).get();
        TaxiClient client = clientRepository.findById(cv.getUserId()).get();
        CarsOwner owner = ownerRepository.findById(id).get();
        Car car = carRepository.findById(carId).get();

        Driver newDriver = new Driver(cv.getName() + cv.getSurname(),
                new PersonInfo(cv.getName(), cv.getSurname(), cv.getPhone()), car);
        newDriver = driverRepository.save(newDriver);

        List<Manager> managers = managerRepository.findAll();
        for (Manager m : managers) {
            Message message = new Message(owner, m, MessageType.DRIVER, newDriver.getId());
            messageRepository.save(message);
        }
        Message message = new Message(owner, client, MessageType.ACK, null);
        messageRepository.save(message);
        cv.setShown(true);
        cvRepository.save(cv);
    }


}
