package repositories.repositories;

import models.Order;
import repositories.interfaces.IOrderRepository;

import java.util.ArrayList;
import java.util.List;



public class OrderRepository implements IOrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Заказ добавлен: " + order.getId());
    }

    @Override
    public Order getOrderById(int orderId) {
        return orders.stream()
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        Order existingOrder = getOrderById(order.getId());
        if (existingOrder != null) {
            orders.remove(existingOrder);
            orders.add(order);
            System.out.println("Заказ обновлен: " + order.getId());
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            orders.remove(order);
            System.out.println("Заказ удален: " + orderId);

        }
    }
}