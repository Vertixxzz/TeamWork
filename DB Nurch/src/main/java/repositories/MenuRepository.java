package repositories;

import data.interfaces.IDB;
import models.MenuItem;
import repositories.interfaces.IMenuRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository implements IMenuRepository {
    private final IDB db;

    public MenuRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addMenuItem(MenuItem item) {
        Connection connection = db.getConnection();
        try {
            String sql = "INSERT INTO menu_items (name, category, price) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, item.getName());
            st.setString(2, item.getCategory());
            st.setDouble(3, item.getPrice());
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public MenuItem getMenuItemById(int id) {
        Connection connection = db.getConnection();
        try {
            String sql = "SELECT * FROM menu_items WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        Connection connection = db.getConnection();
        List<MenuItem> menu = new ArrayList<>();
        try {
            String sql = "SELECT * FROM menu_items";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                menu.add(new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return menu;
    }
}