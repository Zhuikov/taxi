package taxiApp.springapp.repos;

import taxiApp.core.CarsOwner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarsOwnerRepository extends CrudRepository<CarsOwner, Long> {
    List<CarsOwner> findAll();
    CarsOwner findByLogin(String login);
}
