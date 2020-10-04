package taxiApp.springapp.repos.impls;

import taxiApp.core.TaxiClient;
import org.springframework.data.repository.NoRepositoryBean;
import taxiApp.springapp.repos.TaxiClientRepository;

@NoRepositoryBean
public class TaxiClientRepositoryImpl extends CrudRepositoryImpl<TaxiClient> implements TaxiClientRepository {
    @Override
    public TaxiClient findByLogin(String login) {
        for (TaxiClient client : items) {
            if (client.getLogin().equals(login))
                return client;
        }
        return null;
    }
}
