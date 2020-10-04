package taxiApp.springapp.repos.impls;

import taxiApp.core.Manager;
import org.springframework.data.repository.NoRepositoryBean;
import taxiApp.springapp.repos.ManagerRepository;

@NoRepositoryBean
public class ManagerRepositoryImpl extends CrudRepositoryImpl<Manager> implements ManagerRepository {

    @Override
    public Manager findByLogin(String login) {
        for (Manager manager : items) {
            if (manager.getLogin().equals(login))
                return manager;
        }
        return null;
    }
}
