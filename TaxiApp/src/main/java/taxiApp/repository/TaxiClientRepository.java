//package taxiApp.repository;
//
//import taxiApp.core.TaxiClient;
//
//// singleton
//public class TaxiClientRepository extends UserRepository<TaxiClient> {
//
//    private static TaxiClientRepository taxiClientRepository = null;
//    private TaxiClientRepository() {}
//
//    public static TaxiClientRepository getInstance() {
//        if (taxiClientRepository == null)
//            taxiClientRepository = new TaxiClientRepository();
//        return taxiClientRepository;
//    }
//
////    @Override
////    protected void loginUser(TaxiClient user) {
////        // todo check user's existence in DB
////        entities.add(user);
////    }
////
////    @Override
////    public void addNew(TaxiClient user) {
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
