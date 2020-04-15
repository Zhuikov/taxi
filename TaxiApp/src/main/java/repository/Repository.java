package repository;

import core.*;

import java.util.List;

// Singleton
public class Repository {

    private static Repository repository = null;

    private List<Driver> drivers;
    private List<Car> cars;
    private List<TaxiClient> taxiClients;
    private List<Manager> managers;
    private List<CarsOwner> carsOwners;
    private List<Order> orders;

    private Repository() {}

    public static Repository getInstance() {
        if (repository == null)
            repository = new Repository();
        return repository;
    }

    boolean loginExist(String login) {
        User driverWithLogin = drivers.stream().filter(d -> d.getLogin().equals(login)).findAny().orElse(null);
        User managerWithLogin = managers.stream().filter(m -> m.getLogin().equals(login)).findAny().orElse(null);
        User clientWithLogin = taxiClients.stream().filter(c -> c.getLogin().equals(login)).findAny().orElse(null);
        User carsOwnerWithLogin = carsOwners.stream().filter(o -> o.getLogin().equals(login)).findAny().orElse(null);

        return driverWithLogin == null
                && managerWithLogin == null
                && clientWithLogin == null
                && carsOwnerWithLogin == null;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Order> getOrders() {
        return orders;
    }

//    public

}
