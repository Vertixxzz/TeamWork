package repositories.Interface;

import models.Order;
import java.util.List;

public interface IOrderRepository {
    boolean createOrder(Order order);
    List<Order> getOrdersByUserId(int userId);

    boolean deleteOrder(int orderId);
}
