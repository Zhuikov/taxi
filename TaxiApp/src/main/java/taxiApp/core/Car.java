package taxiApp.core;

import javax.persistence.*;

@Entity
@Table(name = "Cars")
public class Car extends TaxiItem {

    @ManyToOne
    @JoinColumn(name="id_owner", nullable=false)
    private final CarsOwner owner;
    @Column
    private final String licensePlate;
    @Column
    private final String model;

    public Car(String licensePlate, CarsOwner owner, String model) {
        // TODO correct regex
//        if (!licensePlate.matches("[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}"))
//            throw new IllegalArgumentException("Wrong license plate format");
        this.licensePlate = licensePlate;
        this.owner = owner;
        this.model = model;
    }

    public Car() {
        owner = null;
        licensePlate = "";
        model = "";
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public CarsOwner getOwner() {
        return owner;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Car " + model + " [" + licensePlate + "]";
    }
}
