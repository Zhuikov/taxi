package taxiApp.springapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import taxiApp.core.*;
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
            Car car4 = new Car("A385PH178", owner, "Ford Focus");
            car1 = carRepository.save(car1);
            car2 = carRepository.save(car2);
            car3 = carRepository.save(car3);
            car4 = carRepository.save(car4);

            Driver driver1 = new Driver("driver1", new PersonInfo("Alex", "Baranov", "89214320123"), car1);
            Driver driver2 = new Driver("driver2", new PersonInfo("Andrew", "Losev", "89223347832"), car2);
            Driver driver3 = new Driver("driver3", new PersonInfo("Oleg", "Kabanov", "89097721221"), car3);
            driver1.setActive(true);
            driver2.setActive(true);
            driverRepository.save(driver1); driverRepository.save(driver2); driverRepository.save(driver3);

            TaxiClient client1 = new TaxiClient("client1", new PersonInfo("Elena", "Nateeva", "89234238084"));
            TaxiClient client2 = new TaxiClient("client2", new PersonInfo("Olga", "Lebedeva", "89998346212"));
            client1 = taxiClientRepository.save(client1); client2 = taxiClientRepository.save(client2);

            CV cv1 = new CV(client2.getId(), "Slava", "Taskov", "89901347833", 4, false);
            CV cv2 = new CV(client1.getId(), "Roman", "Lonely", "89901342311", 3, true);
            cvRepository.save(cv1); cvRepository.save(cv2);

            Manager manager = new Manager("manager1", new PersonInfo("Vladimir", "Yuranov", "89244389029"));
            manager = managerRepository.save(manager);

            Order order = new Order("Street White, Building 3", "Street Red, Building 12", client2);
            order = orderRepository.save(order);
            System.out.println("Order id = " + order.getId());

            for (Car c : carRepository.findAll()) {
                System.out.println(c);
            }
        };
    }
}
