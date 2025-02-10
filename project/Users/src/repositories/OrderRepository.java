package repositories;

import data.PostgresDB;
import models.Menu;
import models.Order;
import models.OrderItem;
import models.User;
import repositories.Interface.IOrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private PostgresDB db;

    public OrderRepository() {

        db = PostgresDB.getInstance();
    }


    public Order getFullOrderDescription(int orderId) {
        Order order = null;
        String query = "SELECT o.id as order_id, o.user_id, o.total_price, " +
                "u.username, " +
                "oi.id as order_item_id, oi.menu_id, oi.quantity, " +
                "m.item_name, m.price " +
                "FROM orders o " +
                "JOIN users u ON o.user_id = u.id " +
                "JOIN order_items oi ON oi.order_id = o.id " +
                "JOIN menu m ON oi.menu_id = m.id " +
                "WHERE o.id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            List<OrderItem> items = new ArrayList<>();
            while (rs.next()) {
                if (order == null) {
                    order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setTotalPrice(rs.getDouble("total_price"));
                }

                OrderItem item = new OrderItem();
                item.setId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setMenuId(rs.getInt("menu_id"));
                item.setQuantity(rs.getInt("quantity"));
                items.add(item);
            }

            if (order != null) {
                order.setOrderItems(items);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public boolean createOrder(Order order) {
        return false;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return new ArrayList<>();
    }
}
