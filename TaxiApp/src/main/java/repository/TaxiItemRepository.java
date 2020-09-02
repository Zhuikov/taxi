package repository;

import Exceptions.NoEntityException;
import core.TaxiItem;

import java.util.ArrayList;
import java.util.List;

public abstract class TaxiItemRepository<T extends TaxiItem> {

    protected List<T> entities = new ArrayList<>();
    // todo ask DB in specific constructor
    protected int unusedId = 0;

    public List<T> getAll() {
        return entities;
    }

//    public T getById(int id) throws NoEntityException {
//        return entities.stream()
//                .filter(e -> e.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new NoEntityException(id));
//    }

    public void add(T entity) {
        entities.add(entity);
    }

    public void removeAll() {
        entities.clear();
    }

    public int getUnusedId() {
        return unusedId++;
    }

}
