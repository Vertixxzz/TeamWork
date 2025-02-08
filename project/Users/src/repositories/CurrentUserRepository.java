package repositories;

import data.interfaces.IDB;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentUserRepository implements repositories.interfaces.ICurrentUserRepository {
    private final IDB db;

    public CurrentUserRepository(IDB db){
        this.db = db;
    }

    @Override
    public User getUserInfo(String name) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM users WHERE user_name = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getInt("user_age"),
                        rs.getBoolean("user_gender"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE users SET user_name = ?, user_age = ?, user_gender = ?, password = ? WHERE user_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getUser_name());
            st.setInt(2, user.getUser_age());
            st.setBoolean(3, user.getUser_gender());
            st.setString(5, user.getPassword());
            st.setInt(6, user.getUser_id());

            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(String name) {
        try (Connection connection = db.getConnection()) {
            String sql = "DELETE FROM users WHERE user_name = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);

            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }
}