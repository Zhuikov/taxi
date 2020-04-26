package repository;

import core.Driver;

import java.util.Optional;

public class DriverRepository extends UserRepository<Driver> {

    @Override
    protected void loginUser(Driver user) {
        // todo check user's existence in DB
        onlineUsers.add(user);
    }

    @Override
    public void addNew(Driver user) {
        // todo go to DB
    }

    @Override
    public int getUnusedId() {
        // todo go to db
        return 0;
    }

    @Override
    public Driver getById(int id) {
        Optional<Driver> res = onlineUsers.stream().filter(u -> u.getId() == id).findFirst();
        if (res.isPresent())
            return res.get();

        // if user is offline,
        // todo go to DB

        throw new IllegalArgumentException("No user with required id = " + id);
    }
}
