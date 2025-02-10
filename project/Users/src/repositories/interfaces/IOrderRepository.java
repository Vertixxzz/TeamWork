package repositories.Interface;

import models.Order;
import java.util.List;

public interface IOrderRepository {
    List<Order> getOrdersByUserId(int userId);
    boolean createOrder(Order order);
}
