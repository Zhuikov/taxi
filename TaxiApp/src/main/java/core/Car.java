package core;

public class Car {

    private final String licensePlate;
    private final CarsOwner owner;
    private boolean used = false;

    public Car(String licensePlate, CarsOwner owner) {

        if (!licensePlate.matches("[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}"))
            throw new IllegalArgumentException("Wrong license plate format");

        this.licensePlate = licensePlate;
        this.owner = owner;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public CarsOwner getOwner() {
        return owner;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

}
