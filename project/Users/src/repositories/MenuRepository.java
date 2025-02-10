package repositories;

import data.PostgresDB;
import models.Menu;
import repositories.Interface.IMenuRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository implements IMenuRepository {
    private PostgresDB db;

    public MenuRepository() {
        db = PostgresDB.getInstance();
    }

    @Override
    public List<Menu> getAllMenuItems() {
        List<Menu> menuItems = new ArrayList<>();
        String query = "SELECT id, item_name, price FROM menu";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Menu menu = new Menu();
                menu.setId(rs.getInt("id"));
                menu.setItemName(rs.getString("item_name"));
                menu.setPrice(rs.getDouble("price"));
                menuItems.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    @Override
    public Menu getMenuItemById(int id) {
        String query = "SELECT id, item_name, price FROM menu WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Menu menu = new Menu();
                    menu.setId(rs.getInt("id"));
                    menu.setItemName(rs.getString("item_name"));
                    menu.setPrice(rs.getDouble("price"));
                    return menu;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addMenuItem(Menu menu) {
        String query = "INSERT INTO menu (item_name, price) VALUES (?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, menu.getItemName());
            stmt.setDouble(2, menu.getPrice());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
