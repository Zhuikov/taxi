package taxiApp.springapp.repos.impls;

import taxiApp.core.CarsOwner;
import org.springframework.data.repository.NoRepositoryBean;
import taxiApp.springapp.repos.CarsOwnerRepository;

@NoRepositoryBean
public class CarsOwnerRepositoryImpl extends CrudRepositoryImpl<CarsOwner> implements CarsOwnerRepository {
    @Override
    public CarsOwner findByLogin(String login) {
        for (CarsOwner carsOwner : items) {
            if (carsOwner.getLogin().equals(login))
                return carsOwner;
        }
        return null;
    }
}
