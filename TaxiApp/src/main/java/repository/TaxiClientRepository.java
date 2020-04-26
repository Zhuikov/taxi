package repository;

import core.TaxiClient;

import java.util.Optional;

public class TaxiClientRepository extends UserRepository<TaxiClient> {

    @Override
    protected void loginUser(TaxiClient user) {
        // todo check user's existence in DB
        onlineUsers.add(user);
    }

    @Override
    public void addNew(TaxiClient user) {
        // todo go to DB
    }

    @Override
    public int getUnusedId() {
        // todo go to db
        return 0;
    }

    @Override
    public TaxiClient getById(int id) {
        Optional<TaxiClient> res = onlineUsers.stream().filter(u -> u.getId() == id).findFirst();
        if (res.isPresent())
            return res.get();

        // if user is offline,
        // todo go to DB

        throw new IllegalArgumentException("No user with required id = " + id);
    }

}
