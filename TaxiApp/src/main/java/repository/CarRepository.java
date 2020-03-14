package repository;

import core.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private List<Car> cars = new ArrayList<>();

    public List<Car> getAll() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

}
