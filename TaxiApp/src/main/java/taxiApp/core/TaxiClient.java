package taxiApp.core;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
//import taxiApp.repository.OrderRepository;

@Entity
@Table(name = "Clients")
@PrimaryKeyJoinColumn(name = "id_user")
public class TaxiClient extends User {

    public TaxiClient() {
        super();
    }

    public TaxiClient(String login, PersonInfo personInfo) {
        super(login, personInfo);
    }

}
