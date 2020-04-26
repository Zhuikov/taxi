package core;

public class CarsOwner extends User {

    public CarsOwner(int id, String login, PersonInfo personInfo) {
        super(id, login, personInfo, UserRole.OWNER);
    }

}
