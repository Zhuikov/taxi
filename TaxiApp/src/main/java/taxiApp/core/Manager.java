package taxiApp.core;

import taxiApp.Exceptions.NoEntityException;
//import taxiApp.repository.DriverRepository;
//import taxiApp.repository.OrderRepository;

import javax.persistence.*;


@Entity
@Table(name = "Managers")
@PrimaryKeyJoinColumn(name = "id_user")
public class Manager extends User {

    public Manager() {
        super();
    }

    public Manager(String login, PersonInfo personInfo) {
        super(login, personInfo);
    }


}
