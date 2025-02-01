package repositories.interfaces;

import models.Order;

import java.util.List;

/**
 *тут заказы
 */
public interface IOrderRepository {
    void addOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void deleteOrder(int orderId);
}
