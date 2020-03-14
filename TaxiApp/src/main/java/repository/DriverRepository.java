package repository;

import core.Driver;
import core.DriverStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DriverRepository {

    private List<Driver> drivers = new ArrayList<>();

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public List<Driver> getAll() {
        return drivers;
    }

    public List<Driver> getWithStatus(DriverStatus status) {
        return drivers.stream().filter(driver -> driver.getStatus() == status).collect(Collectors.toList());
    }

}
