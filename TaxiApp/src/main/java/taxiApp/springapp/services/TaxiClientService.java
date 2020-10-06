package taxiApp.springapp.services;

import taxiApp.core.CV;
import taxiApp.core.Order;
import taxiApp.core.TaxiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.springapp.repos.CVRepository;
import taxiApp.springapp.repos.MessageRepository;
import taxiApp.springapp.repos.OrderRepository;
import taxiApp.springapp.repos.TaxiClientRepository;
import taxiApp.springapp.services.representations.OrderRepresentation;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxiClientService extends UserService {

    private final OrderRepository orderRepository;
    private final TaxiClientRepository clientRepository;
    private final CVRepository cvRepository;

    public TaxiClientService(@Autowired MessageRepository messageRepository, @Autowired OrderRepository orderRepository,
                             @Autowired  TaxiClientRepository clientRepository, @Autowired CVRepository cvRepository) {
        super(messageRepository);
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.cvRepository = cvRepository;
    }

    public TaxiClient getByLogin(String login) {
        return clientRepository.findByLogin(login);
    }

    public TaxiClient getById(Long id) {
        return clientRepository.findById(id).get();
    }

    public List<OrderRepresentation> getClientsOrders(TaxiClient client) {
        List<Order> orders = orderRepository.findByTaxiClient(client);
        List<OrderRepresentation> result = new ArrayList<>();
        for (Order o : orders)
            result.add(new OrderRepresentation(o));
        return result;
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public CV addCV(CV cv) {
        return cvRepository.save(cv);
    }

}
