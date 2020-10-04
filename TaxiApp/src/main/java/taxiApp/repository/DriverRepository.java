//package taxiApp.repository;
//
//import taxiApp.core.Driver;
//
//// singleton
//public class DriverRepository extends UserRepository<Driver> {
//
//    private static DriverRepository driverRepository = null;
//    private DriverRepository() {}
//
//    public static DriverRepository getInstance() {
//        if (driverRepository == null)
//            driverRepository = new DriverRepository();
//        return driverRepository;
//    }
//
////    @Override
////    protected void loginUser(Driver user) {
////        // todo check user's existence in DB
////        users.add(user);
////    }
//
////    @Override
////    public void addNew(Driver user) {
////        // todo go to DB
////    }
////
////    @Override
////    public int getUnusedId() {
////        // todo go to db
////        return 0;
////    }
//
//}
