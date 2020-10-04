package taxiApp.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CVs")
public class CV extends TaxiItem {

    @Column
    private final Long userId;
    @Column
    private final String name;
    @Column
    private final String surname;
    @Column
    private final String phone;
    @Column
    private final int experience;
    @Column
    private boolean shown = false;

    public CV() {
        experience = 0;
        phone = "";
        userId = 0L;
        surname = "";
        name = "";
    }

    public CV(Long userId, String name, String surname, String phone, int experience, boolean shown) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.experience = experience;
        this.shown = shown;
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

    public int getExperience() {
        return experience;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean read) {
        this.shown = read;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "CV{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", experience=" + experience +
                '}';
    }

}
