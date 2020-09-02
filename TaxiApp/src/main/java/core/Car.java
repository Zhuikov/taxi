package core;

public class Car extends TaxiItem {

    private final String licensePlate;
    private boolean used = false;

    public Car(int id, String licensePlate, CarsOwner owner) {
//        super(id);

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

}
