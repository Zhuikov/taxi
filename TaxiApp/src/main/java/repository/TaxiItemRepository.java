package repository;

import Exceptions.NoEntityException;
import core.TaxiItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

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

    abstract public void deleteById(int entity);

    abstract public T getById(int id) throws NoEntityException;

    abstract public void update(T entity);

//    public void removeAll() {}

}
