package taxiApp.springapp.repos;

import taxiApp.core.TaxiClient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaxiClientRepository extends CrudRepository<TaxiClient, Long> {
    List<TaxiClient> findAll();
    TaxiClient findByLogin(String login);
}
