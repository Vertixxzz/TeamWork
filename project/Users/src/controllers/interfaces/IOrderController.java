package controllers.interfaces;

import models.Order;

import java.util.List;

/**
 * Интерфейс контроллера для управления заказами.
 */
public interface IOrderController {
    void createOrder(String customerName, List<MenuItem> items);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    void updateOrder(int orderId, String customerName, List<MenuItem> items);
    void deleteOrder(int orderId);
}