package core;

abstract public class User {

    protected final int userId;
    protected final PersonInfo personInfo;

    public User(int userId, PersonInfo personInfo) {
        this.userId = userId;
        this.personInfo = personInfo;
    }

    public int getUserId() {
        return userId;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }
}
