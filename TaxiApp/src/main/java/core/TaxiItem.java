package core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract public class TaxiItem {

    @Id
    @GeneratedValue
    protected long id = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
