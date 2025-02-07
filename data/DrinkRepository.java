package data;

import models.Drink;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {
    public static List<Drink> getAllDrinks() {
        List<Drink> drinks = new ArrayList<>();
        String sql = "SELECT * FROM drinks";

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                drinks.add(new Drink(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drinks;
    }

    public static void addDrink(String name, double price) {
        String sql = "INSERT INTO drinks (name, price) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.executeUpdate();  // Выполняет вставку

            System.out.println("Напиток добавлен в базу данных!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
