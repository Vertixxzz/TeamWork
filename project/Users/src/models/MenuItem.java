package repositories.interfaces;

import models.Order;

import java.util.List;

/**
 * Интерфейс для работы с заказами.
 * Определяет методы для добавления, получения, обновления и удаления заказов.
 */
public interface IOrderRepository {
    void addOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void deleteOrder(int orderId);
}