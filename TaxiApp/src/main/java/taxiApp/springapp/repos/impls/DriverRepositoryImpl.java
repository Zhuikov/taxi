package taxiApp.springapp.repos.impls;

import taxiApp.core.Driver;
import org.springframework.data.repository.NoRepositoryBean;
import taxiApp.springapp.repos.DriverRepository;

@NoRepositoryBean
public class DriverRepositoryImpl extends CrudRepositoryImpl<Driver> implements DriverRepository {
    @Override
    public Driver findByLogin(String login) {
        for (Driver driver : items) {
            if (driver.getLogin().equals(login))
                return driver;
        }
        return null;
    }
}
