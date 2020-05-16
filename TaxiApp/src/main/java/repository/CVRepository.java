package repository;

import core.CV;

// singleton
public class CVRepository extends TaxiItemRepository<CV> {

    private static CVRepository cvRepository = null;
    private CVRepository() {}

    public static CVRepository getSingleton() {
        if (cvRepository == null)
            cvRepository = new CVRepository();
        return cvRepository;
    }

//    @Override
//    public CV getById(int id) {
//        // todo go to db
//        return null;
//    }
//
//    @Override
//    public void addNew(CV item) {
//        // todo go to db
//    }
//
//    @Override
//    public int getUnusedId() {
//        // todo go to db
//        return 0;
//    }
}
