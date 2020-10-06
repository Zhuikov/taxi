package taxiApp.springapp.repos.impls;

import taxiApp.core.Order;
import org.springframework.data.repository.NoRepositoryBean;
import taxiApp.core.TaxiClient;
import taxiApp.springapp.repos.OrderRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoRepositoryBean
public class OrderRepositoryImpl extends CrudRepositoryImpl<Order> implements OrderRepository {

    @Override
    public List<Order> findByTaxiClient(TaxiClient client) {
        return items.stream()
                .filter(order -> Objects.equals(order.getTaxiClient().getId(), client.getId()))
                .collect(Collectors.toList());
    }
}
