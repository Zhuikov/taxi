package repository;

import core.CarsOwner;

import java.util.Optional;

// singleton
public class CarsOwnerRepository extends UserRepository<CarsOwner> {

    private static CarsOwnerRepository carsOwnerRepository = null;
    private CarsOwnerRepository() {}

    public static CarsOwnerRepository getSingleton() {
        if (carsOwnerRepository == null)
            carsOwnerRepository = new CarsOwnerRepository();
        return carsOwnerRepository;
    }

//    @Override
//    public void loginUser(CarsOwner user) {
//        // todo check user's existence in DB
//        users.add(user);
//    }

//    @Override
//    public void addNew(CarsOwner user) {
//        // todo go to DB
//    }

//    @Override
//    public int getUnusedId() {
//        // go to db
//        return 0;
//    }
}
