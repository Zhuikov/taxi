package taxiApp.springapp.services;

import taxiApp.Exceptions.CarIsUsingException;
import taxiApp.Exceptions.NoEntityException;
import taxiApp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.springapp.repos.*;

import java.util.List;
import java.util.Optional;

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

    public List<Car> getCars(CarsOwner owner) {
        return carRepository.findAllByOwner(owner);
    }

    public List<CV> getCVs() {
        return cvRepository.findAll();
    }

    public void nackCV(CarsOwner owner, Long cvId) throws NoEntityException {
        Optional<CV> cv = cvRepository.findById(cvId);
        if (!cv.isPresent())
            throw new NoEntityException(cvId);
        Optional<TaxiClient> client = clientRepository.findById(cv.get().getUserId());
        if (!client.isPresent())
            throw new NoEntityException(cv.get().getUserId());
        cv.get().setShown(true);
        Message message = new Message(owner, client.get(), MessageType.NACK, null);
        messageRepository.save(message);
        cvRepository.save(cv.get());
    }

    public void ackCV(CarsOwner owner, Long cvId, Long carId) throws NoEntityException, CarIsUsingException {
        Optional<CV> cv = cvRepository.findById(cvId);
        if (!cv.isPresent())
            throw new NoEntityException(cvId);
        Optional<TaxiClient> client = clientRepository.findById(cv.get().getUserId());
        if (!client.isPresent())
            throw new NoEntityException(cv.get().getUserId());
        Optional<Car> car = carRepository.findById(carId);
        if (!car.isPresent())
            throw new NoEntityException(carId);
        Driver carDriver = checkUsingCar(car.get());
        if (carDriver != null)
            throw new CarIsUsingException(car.get(), carDriver);
        Driver newDriver = new Driver(cv.get().getName() + cv.get().getSurname(),
                new PersonInfo(cv.get().getName(), cv.get().getSurname(), cv.get().getPhone()), car.get());
        newDriver = driverRepository.save(newDriver);

        List<Manager> managers = managerRepository.findAll();
        for (Manager m : managers) {
            Message message = new Message(owner, m, MessageType.DRIVER, newDriver.getId());
            messageRepository.save(message);
        }
        Message message = new Message(owner, client.get(), MessageType.ACK, null);
        messageRepository.save(message);
        cv.get().setShown(true);
        cvRepository.save(cv.get());
    }

    private Driver checkUsingCar(Car car) {
        return driverRepository.findByCar(car);
    }

}
