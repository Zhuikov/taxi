package repository;

import core.CarsOwner;

import java.util.Optional;

public class CarsOwnerRepository extends UserRepository<CarsOwner> {

    @Override
    public void loginUser(CarsOwner user) {
        // todo check user's existence in DB
        onlineUsers.add(user);
    }

    @Override
    public void addNew(CarsOwner user) {
        // todo go to DB
    }

    @Override
    public int getUnusedId() {
        // go to db
        return 0;
    }

    @Override
    public CarsOwner getById(int id) {
        Optional<CarsOwner> res = onlineUsers.stream().filter(u -> u.getId() == id).findFirst();
        if (res.isPresent())
            return res.get();

        // if user is offline,
        // todo go to DB

        throw new IllegalArgumentException("No user with required id = " + id);
    }
}
