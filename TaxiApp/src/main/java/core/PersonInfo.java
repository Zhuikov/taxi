package core;

public class PersonInfo {

    private final String name;
    private final String surname;
    private final String phone;

    public PersonInfo(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
