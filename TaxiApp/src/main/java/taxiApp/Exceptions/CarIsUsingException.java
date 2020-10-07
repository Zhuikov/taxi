package taxiApp.Exceptions;

import taxiApp.core.Car;
import taxiApp.core.Driver;

public class CarIsUsingException extends Exception {
    private final Car car;
    private final Driver driver;

    public CarIsUsingException(Car car, Driver driver) {
        super("Car is using");
        this.car = car;
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public Driver getDriver() {
        return driver;
    }
}
