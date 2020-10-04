package taxiApp.springapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import taxiApp.core.Car;
import taxiApp.core.CarsOwner;
import taxiApp.core.Driver;
import taxiApp.core.PersonInfo;
import taxiApp.springapp.repos.*;

import java.util.ArrayList;


@SpringBootApplication
public class TaxiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaxiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            OrderRepository orderRepository, MessageRepository messageRepository,
            ManagerRepository managerRepository, DriverRepository driverRepository,
            TaxiClientRepository taxiClientRepository, CarRepository carRepository,
            CarsOwnerRepository carsOwnerRepository, CVRepository cvRepository
    ) {
        return args -> {

            CarsOwner owner = new CarsOwner("owner1", new PersonInfo("Ivan", "Ivanov", "89123428989"));
            owner = carsOwnerRepository.save(owner);

            Car car1 = new Car("C432KO178", owner, "Kia Rio");
            Car car2 = new Car("K999OA78", owner, "Opel Astra");
            Car car3 = new Car("O432AO78", owner, "Lada Sedan");
            car1 = carRepository.save(car1);
            car2 = carRepository.save(car2);
            car3 = carRepository.save(car3);

            Driver driver1 = new Driver("driver1", new PersonInfo("Alex", "Alex", "89214320123"), car1);
            Driver driver2 = new Driver("driver2", new PersonInfo("Andrew", "Losev", "89223347832"), car2);
            driverRepository.save(driver1); driverRepository.save(driver2);

            for (Car c : carRepository.findAll()) {
                System.out.println(c);
            }
        };
    }
}

