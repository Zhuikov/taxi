package taxiApp.core;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class User extends TaxiItem {

    protected final String login;
    @Embedded
    protected final PersonInfo personInfo;

    public User() {
        this.login = "";
        this.personInfo = null;
    }

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
