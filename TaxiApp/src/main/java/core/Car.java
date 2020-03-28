package core;

public class Car {

    public Car(String licensePlate) {

        if (!licensePlate.matches("[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}"))
            throw new IllegalArgumentException("Wrong license plate format");

        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    private final String licensePlate;
    private boolean used = false;

}
