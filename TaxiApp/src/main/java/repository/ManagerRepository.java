//package repository;
//
//import core.Manager;
//
//// singleton
//public class ManagerRepository extends UserRepository<Manager> {
//
//    private static ManagerRepository managerRepository = null;
//    private ManagerRepository() {}
//
//    public static ManagerRepository getInstance() {
//        if (managerRepository == null)
//            managerRepository = new ManagerRepository();
//        return managerRepository;
//    }
//
////    @Override
////    protected void loginUser(Manager user) {
////        // todo check user's existence in DB
////        entities.add(user);
////    }
//
////    @Override
////    public void addNew(Manager user) {
////        // todo go to DB
////    }
////
////    @Override
////    public int getUnusedId() {
////        // todo go to db
////        return 0;
////    }
//}
