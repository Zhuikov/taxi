package taxiApp.springapp.services.representations;

public class UserData {
    private String name;
    private String surname;
    private String role;

    public UserData(String username, String surname, String role) {
        this.name = username;
        this.surname = surname;
        this.role = role;
    }

    public UserData() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
