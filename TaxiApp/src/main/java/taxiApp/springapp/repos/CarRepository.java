package taxiApp.springapp.repos;

import taxiApp.core.Car;
import org.springframework.data.repository.CrudRepository;
import taxiApp.core.CarsOwner;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByOwner(CarsOwner owner);
}
