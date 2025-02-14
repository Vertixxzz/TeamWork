package repositories;

import data.PostgresDB;
import models.Order;
import models.OrderItem;
import repositories.Interface.IOrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private PostgresDB db;

    public OrderRepository() {
        db = PostgresDB.getInstance();
    }
//Полное описание(JOIN)
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
    public boolean deleteOrder(int orderId) {
        String deleteOrderItemsQuery = "DELETE FROM order_items WHERE order_id = ?";
        String deleteOrderQuery = "DELETE FROM orders WHERE id = ?";

        try (Connection conn = db.getConnection()) {
            conn.setAutoCommit(false);

            // Удаляем сначала позиции заказа
            try (PreparedStatement stmtItems = conn.prepareStatement(deleteOrderItemsQuery)) {
                stmtItems.setInt(1, orderId);
                stmtItems.executeUpdate();
            }

            // Затем удаляем сам заказ
            try (PreparedStatement stmtOrder = conn.prepareStatement(deleteOrderQuery)) {
                stmtOrder.setInt(1, orderId);
                int affectedRows = stmtOrder.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createOrder(Order order) {
        String orderQuery = "INSERT INTO orders (user_id, total_price) VALUES (?, ?)";
        String orderItemQuery = "INSERT INTO order_items (order_id, menu_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement orderStmt = conn.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            // Вставляем заказ
            orderStmt.setInt(1, order.getUserId());
            orderStmt.setDouble(2, order.getTotalPrice());
            int affectedRows = orderStmt.executeUpdate();

            if (affectedRows == 0) {
                conn.rollback();
                return false;
            }

            // Получаем сгенерированный id заказа
            try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    order.setId(orderId);

                    // Вставляем позиции заказа
                    try (PreparedStatement orderItemStmt = conn.prepareStatement(orderItemQuery)) {
                        for (OrderItem item : order.getOrderItems()) {
                            orderItemStmt.setInt(1, orderId);
                            orderItemStmt.setInt(2, item.getMenuId());
                            orderItemStmt.setInt(3, item.getQuantity());
                            orderItemStmt.addBatch();
                        }
                        orderItemStmt.executeBatch();
                    }
                } else {
                    conn.rollback();
                    return false;
                }
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//Соеденяет несколько информаций из БД (JOIN)
    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT o.id AS order_id, o.user_id, " +
                "COALESCE(SUM(m.price * oi.quantity), 0) AS total_price, " +
                "string_agg(m.item_name, ', ') AS dish_names " +
                "FROM orders o " +
                "LEFT JOIN order_items oi ON oi.order_id = o.id " +
                "LEFT JOIN menu m ON oi.menu_id = m.id " +
                "WHERE o.user_id = ? " +
                "GROUP BY o.id, o.user_id";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setTotalPrice(rs.getDouble("total_price"));
                    order.setDishNames(rs.getString("dish_names"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
