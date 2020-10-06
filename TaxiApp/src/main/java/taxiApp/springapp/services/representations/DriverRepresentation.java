package taxiApp.springapp.services.representations;

import taxiApp.core.Driver;

public class DriverRepresentation {

    private final Long id;
    private final String name;
    private final String surname;
    private final String phone;
    private final String status;
    private final String active;

    public DriverRepresentation(Driver driver) {
        this.id = driver.getId();
        this.name = driver.getPersonInfo().getName();
        this.surname = driver.getPersonInfo().getSurname();
        this.phone = driver.getPersonInfo().getPhone();
        this.status = driver.getStatus().toString();
        this.active = driver.isActive() ? "+" : "-";
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

    public String getStatus() {
        return status;
    }

    public String getActive() {
        return active;
    }

    public Long getId() {
        return id;
    }
}
