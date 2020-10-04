package taxiApp.repository;

import taxiApp.core.User;

// some methods are abstract because of DB tables
// specificity of different roles
public abstract class UserRepository<T extends User> extends TaxiItemRepository<T> {

//    public void logoutUser(T user) {
//        if (!users.removeIf(u -> u.getId() == user.getId()))
//            throw new IllegalArgumentException("Cannot find user with id = " + user.getId());
//    }

//    abstract protected void loginUser(T user);

}
