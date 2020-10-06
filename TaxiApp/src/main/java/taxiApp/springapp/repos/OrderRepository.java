package taxiApp.springapp.repos;

import taxiApp.core.Order;
import org.springframework.data.repository.CrudRepository;
import taxiApp.core.TaxiClient;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findByTaxiClient(TaxiClient client);
}
