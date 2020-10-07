package taxiApp.springapp.repos;

import taxiApp.core.Car;
import taxiApp.core.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface DriverRepository extends CrudRepository<Driver, Long> {
    List<Driver> findAll();
    Driver findByLogin(String login);
    Driver findByCar(Car car);
}