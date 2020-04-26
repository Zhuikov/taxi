package repository;

import core.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository extends TaxiItemRepository<Car> {

    @Override
    public Car getById(int id) {
        // todo go to db
        return null;
    }

    @Override
    public void addNew(Car item) {
        // todo go to db
    }

    @Override
    public int getUnusedId() {
        // todo go to db
        return 0;
    }

    public List<Car> getCarsByOwner(int ownerId) {
        // todo go to bd
        return new ArrayList<>();
    }

}
