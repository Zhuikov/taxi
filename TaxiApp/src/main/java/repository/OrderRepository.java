package repository;

import core.Order;
import core.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getAll() {
        return orders;
    }

    public List<Order> getWithStatus(OrderStatus status) {
        return orders.stream().filter(order -> order.getStatus() == status).collect(Collectors.toList());
    }
}
