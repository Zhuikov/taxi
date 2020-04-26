package repository;

import core.Manager;

import java.util.Optional;

public class ManagerRepository extends UserRepository<Manager> {

    @Override
    protected void loginUser(Manager user) {
        // todo check user's existence in DB
        onlineUsers.add(user);
    }

    @Override
    public void addNew(Manager user) {
        // todo go to DB
    }

    @Override
    public int getUnusedId() {
        // todo go to db
        return 0;
    }

    @Override
    public Manager getById(int id) {
        Optional<Manager> res = onlineUsers.stream().filter(u -> u.getId() == id).findFirst();
        if (res.isPresent())
            return res.get();

        // if user is offline,
        // todo go to DB

        throw new IllegalArgumentException("No user with required id = " + id);
    }
}
