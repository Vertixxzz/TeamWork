package repositories;

import models.MenuItem;
import models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    // ааа вонючией постгреsql
    private static final String URL = "jdbc:postgresql://localhost:5432/restaurant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public Order createOrder(Order order) {
        String insertOrderSQL = "INSERT INTO orders (customer_name, total_price) VALUES (?, ?) RETURNING id";
        String insertItemSQL = "INSERT INTO order_items (order_id, item_name, item_price) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant","postgres","vertix");
             PreparedStatement psOrder = conn.prepareStatement(insertOrderSQL);
             PreparedStatement psItem = conn.prepareStatement(insertItemSQL)) {

            psOrder.setString(1, order.getCustomerName());
            psOrder.setDouble(2, order.getTotalPrice());

            ResultSet rs = psOrder.executeQuery();
            int generatedOrderId = 0;
            if (rs.next()) {
                generatedOrderId = rs.getInt("id");
            }

            
            for (MenuItem item : order.getItems()) {
                psItem.setInt(1, generatedOrderId);
                psItem.setString(2, item.getName());
                psItem.setDouble(3, item.getPrice());
                psItem.addBatch();
            }
            psItem.executeBatch();

            order.setId(generatedOrderId);
            return order;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Order> getAllOrders() {
        List<Order> result = new ArrayList<>();

        String selectOrdersSQL = "SELECT id, customer_name, total_price FROM orders";
        String selectItemsSQL = "SELECT order_id, item_name, item_price FROM order_items WHERE order_id IN (SELECT id FROM orders)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rsOrders = stmt.executeQuery(selectOrdersSQL)) {

            List<Order> tempList = new ArrayList<>();
            while (rsOrders.next()) {
                int id = rsOrders.getInt("id");
                String customerName = rsOrders.getString("customer_name");
                double totalPrice = rsOrders.getDouble("total_price");

                Order ord = new Order(id, customerName, totalPrice, new ArrayList<>());
                tempList.add(ord);
            }

            try (Statement stmt2 = conn.createStatement();
                 ResultSet rsItems = stmt2.executeQuery(selectItemsSQL)) {

                while (rsItems.next()) {
                    int orderId = rsItems.getInt("order_id");
                    String itemName = rsItems.getString("item_name");
                    double itemPrice = rsItems.getDouble("item_price");

                    for (Order o : tempList) {
                        if (o.getId() == orderId) {
                            o.getItems().add(new MenuItem(itemName, itemPrice));
                            break;
                        }
                    }
                }
            }

            result.addAll(tempList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
