package repository;

import core.TaxiItem;

// This repos are for CVs, Cars and Orders
// they doesnt store items in List<T> but ask DB for it
public abstract class TaxiItemRepository<T extends TaxiItem> {

    abstract public T getById(int id);

    abstract public void addNew(T item);

    abstract public int getUnusedId();

}
