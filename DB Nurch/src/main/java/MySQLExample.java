import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/RestaurantMenu";
        String username = "your_username";
        String password = "your_password";

        // Подключение к базе данных
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Подключение к базе данных успешно установлено!");

            // Здесь можно выполнять SQL-запросы

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных:");
            e.printStackTrace();
        }
    }
}