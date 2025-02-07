package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static Connection connect() throws SQLException {
        // Указываем данные для подключения к базе данных
        String url = "jdbc:postgresql://localhost:5432/postgres";  // Убедись, что название базы данных правильное
        String user = "postgres";  // Укажи свой логин
        String password = "Y78X56Z407";  // Укажи свой пароль
        return DriverManager.getConnection(url, user, password);
    }
}
