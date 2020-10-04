package taxiApp.springapp.repos.impls;

import taxiApp.core.Car;
import org.springframework.data.repository.NoRepositoryBean;
import taxiApp.springapp.repos.CarRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoRepositoryBean
public class CarRepositoryImpl extends CrudRepositoryImpl<Car> implements CarRepository {
    @Override
    public List<Car> findAllByOwner(Long ownerId) {
        return items.stream().
                filter(car -> Objects.equals(car.getOwner().getId(), ownerId))
                .collect(Collectors.toList());
    }
}
