package repository;

import Exceptions.NoEntityException;
import core.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// singleton
public class CarRepository extends TaxiItemRepository<Car> {

    private static CarRepository carRepository = null;

    private static final String INSERT_STMT =
            "insert into Cars id_owner, license_plate, model values (?,?,?)";

    private CarRepository() {}

    public static CarRepository getInstance() {
        if (carRepository == null)
            carRepository = new CarRepository();
        return carRepository;
    }

    @Override
    public long add(Car entity) {
        return 0;
    }

    @Override
    public void deleteById(int entity) {

    }

    @Override
    public Car getById(int id) throws NoEntityException {
        return null;
    }

    @Override
    public void update(Car entity) {

    }
}
