package taxiApp.springapp.repos;

import taxiApp.core.CV;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CVRepository extends CrudRepository<CV, Long> {
    List<CV> findAll();
}
