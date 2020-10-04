package taxiApp.repository;

// Singleton
public class Repository {

    private static Repository repository = null;

////    private final static UserRepository<Driver> driverRepository     = new DriverRepository();
////    private final static UserRepository<Manager> managerRepository   = new ManagerRepository();
////    private final static UserRepository<TaxiClient> clientRepository = new TaxiClientRepository();
////    private final static UserRepository<CarsOwner> ownerRepository   = new CarsOwnerRepository();
////
////    private final static OrderRepository orderRepository             = new OrderRepository();
////    private final static CarRepository carRepository                 = new CarRepository();
////    private final static CVRepository CVRepository                   = new CVRepository();

    private Repository() {}

    public static Repository getInstance() {
        if (repository == null)
            repository = new Repository();
        return repository;
    }

//    public int getUnusedId(Class<? extends TaxiItem> clazz) {
//
//        // too sad ...
//        if (Order.class.equals(clazz)) {
//            return orderRepository.getUnusedId();
//        } else if (TaxiClient.class.equals(clazz)) {
//            return clientRepository.getUnusedId();
//        } else if (CV.class.equals(clazz)) {
//            return CVRepository.getUnusedId();
//        } else if (Driver.class.equals(clazz)) {
//            return driverRepository.getUnusedId();
//        } else if (Manager.class.equals(clazz)) {
//            return managerRepository.getUnusedId();
//        } else if (Car.class.equals(clazz)) {
//            return carRepository.getUnusedId();
//        } else if (CarsOwner.class.equals(clazz)) {
//            return ownerRepository.getUnusedId();
//        }
//        throw new IllegalArgumentException("Unexpected clazz value");
//    }
//
//    public List<Driver> getDrivers() {
//        return driverRepository.getAll();
//    }
//
//    public List<Manager> getManagers() {
//        return managerRepository.getAll();
//    }
//
//    public List<CarsOwner> getCarsOwner() {
//        return ownerRepository.getAll();
//    }
//
//    public List<TaxiClient> getTaxiClients() {
//        return clientRepository.getAll();
//    }
}
