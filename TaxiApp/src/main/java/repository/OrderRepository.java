package repository;

import core.Order;
import core.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends TaxiItemRepository<Order> {

    @Override
    public Order getById(int id) {
        // todo go to db
        return null;
    }

    @Override
    public void addNew(Order item) {
        // todo go to db
    }

    @Override
    public int getUnusedId() {
        // todo go to db
        return 0;
    }

    public List<Order> getWithStatus(OrderStatus status) {
        // todo make specific request to db
        return new ArrayList<>();
    }
}
