package taxiApp.core;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Owners")
@PrimaryKeyJoinColumn(name = "id_user")
public class CarsOwner extends User {

    @OneToMany(mappedBy="owner")
    private Set<Car> cars = new HashSet<>();

    public CarsOwner() {
        super();
    }

    public CarsOwner(String login, PersonInfo personInfo) {
        super(login, personInfo);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public Set<Car> getCars() {
        return cars;
    }
}
