package repositories;

import data.PostgresDB;
import models.User;
import models.Role; // Добавьте этот импорт!
import repositories.Interface.IRegistrationRepository;
import java.sql.*;


public class RegistrationRepository implements IRegistrationRepository {
    private PostgresDB db;

    public RegistrationRepository() {
        db = PostgresDB.getInstance();
    }
    @Override
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByUsername(String username) {
        String query = "SELECT id, username, password, role FROM users WHERE username = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Создаём пользователя
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                // Вытаскиваем роль из ResultSet
                String dbRole = rs.getString("role");
                if (dbRole != null) {
                    // Сопоставляем строку с enum Role
                    if (dbRole.equalsIgnoreCase("ADMIN")) {
                        user.setRole(Role.ADMIN);
                    } else if (dbRole.equalsIgnoreCase("MANAGER")) {
                        user.setRole(Role.MANAGER);
                    } else {
                        user.setRole(Role.CUSTOMER);
                    }
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
