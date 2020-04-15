package core;

abstract public class User {

    protected final String login;
    protected final PersonInfo personInfo;

    public User(String login, PersonInfo personInfo) {
        this.login = login;
        this.personInfo = personInfo;
    }

    public String getLogin() {
        return login;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }
}
