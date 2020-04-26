package repository;

import core.CV;

public class CVRepository extends TaxiItemRepository<CV> {

    @Override
    public CV getById(int id) {
        // todo go to db
        return null;
    }

    @Override
    public void addNew(CV item) {
        // todo go to db
    }

    @Override
    public int getUnusedId() {
        // todo go to db
        return 0;
    }
}
