package taxiApp.repository;

import taxiApp.Exceptions.NoEntityException;
import taxiApp.core.TaxiItem;

import java.sql.Connection;

public abstract class TaxiItemRepository<T extends TaxiItem> {

    static final protected String URL = "jdbc:mysql://localhost:3306/taxi_db?serverTimezone=UTC";
    static final protected String USER = "root";
    static final protected String PASS = "root";

    protected Connection connection = null;

//    abstract public List<T> getAll();

//    public T getById(int id) throws NoEntityException {
//        return entities.stream()
//                .filter(e -> e.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new NoEntityException(id));
//    }

    abstract public long add(T entity);

    abstract public void deleteById(Long entity);

    abstract public T getById(Long id) throws NoEntityException;

    abstract public void update(T entity);

//    public void removeAll() {}

}
