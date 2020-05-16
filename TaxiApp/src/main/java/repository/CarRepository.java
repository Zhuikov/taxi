package repository;

import Exceptions.NoUnusedCarsException;
import core.Car;

import java.util.ArrayList;
import java.util.List;

// singleton
public class CarRepository extends TaxiItemRepository<Car> {

    private static CarRepository carRepository = null;
    private CarRepository() {}

    public static CarRepository getSingleton() {
        if (carRepository == null)
            carRepository = new CarRepository();
        return carRepository;
    }

    public Car getUnusedCar() throws NoUnusedCarsException {
        return entities.stream()
                .filter(e -> !e.isUsed())
                .findFirst()
                .orElseThrow(NoUnusedCarsException::new);
    }

//    @Override
//    public Car getById(int id) {
//        // todo go to db
//        return null;
//    }

//    @Override
//    public void addNew(Car item) {
//        // todo go to db
//    }

//    @Override
//    public int getUnusedId() {
//        // todo go to db
//        return 0;
//    }

    public List<Car> getCarsByOwner(int ownerId) {
        // todo go to bd
        return new ArrayList<>();
    }

}
