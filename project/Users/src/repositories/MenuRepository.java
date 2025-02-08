package repositories.repositories;

import data.interfaces.IDB;
import models.Menu;
import repositories.interfaces.IMenuRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository implements IMenuRepository {
    private final IDB db;

    public MenuRepository(IDB db){
        this.db = db;
    }
    @Override
    public boolean createMenu(Menu menu) {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql = "INSERT INTO menues (menu_name, menu_id, menu_price, rating) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, menu.getMenu_name());
            st.setInt(2, menu.getMenu_id());
            st.setInt(3, menu.getMenu_price());
            st.setDouble(4, menu.getRating());

            st.execute();
            return true;
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Menu getMenuById(int menu_id) {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql = "SELECT m.menu_id, m.menu_name, m.menu_price, m.rating " +
                    "FROM menu m " +
                    "WHERE m.menu_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menu_id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Menu menu = new Menu(rs.getInt("menu_id"),
                        rs.getString("menu_name"),
                        rs.getInt("menu_price"),
                        rs.getDouble("rating"));
                return menu;
            }
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }


    @Override
    public List<Menu> getAllMenu() {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT m.menu_id, m.menu_name, m.menu_price, m.rating " +
                    "FROM menu m ";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Menu> menu = new ArrayList<>();
            while (rs.next()){
                Menu menu = new Menu(rs.getInt("menu_id"),
                        rs.getString("menu_name"),
                        rs.getInt("menu_price"),
                        rs.getDouble("rating"));
                menu.update(menu);
            }
            return menues;
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteMenu(int menuId) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "DELETE FROM menues WHERE menu_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menuId);
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateMenu(Menu menu) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE menues SET menu_name = ?, menu_price = ?, rating = ? WHERE menu_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, menu.getMenu_name());
            st.setInt(2, menu.getMenu_price());
            st.setDouble(3, menu.getRating());
            st.setInt(4, menu.getMenuId());
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }
    @Override
    public void updateMenuRating(int menuId) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE menu SET rating = (SELECT AVG(rating) FROM menu_reviews WHERE menu_id = ?) WHERE menu_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, menuId);
            st.setInt(2, menuId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
