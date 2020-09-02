package core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract public class TaxiItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int id;
}
