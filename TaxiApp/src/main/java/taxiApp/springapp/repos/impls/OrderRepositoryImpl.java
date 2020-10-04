package taxiApp.springapp.repos.impls;

import taxiApp.core.Order;
import org.springframework.data.repository.NoRepositoryBean;
import taxiApp.springapp.repos.OrderRepository;

@NoRepositoryBean
public class OrderRepositoryImpl extends CrudRepositoryImpl<Order> implements OrderRepository {
    
}
