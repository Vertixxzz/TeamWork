package main.java.org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Step 1: Register JDBC driver
            Class.forName("org.postgresql.Driver");

            // Step 2: Open a connection
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "Y78X56Z407");

            // Step 3: Create a statement object to execute SQL queries
            stmt = conn.createStatement();

            // Step 4: Write and execute SQL query
            String sql = "SELECT * FROM dishes";
            stmt.executeQuery(sql);

            // Step 5: Process the result set (if any)
            // For example, print out the dishes
            // ResultSet rs = stmt.executeQuery(sql);
            // while (rs.next()) {
            //     System.out.println(rs.getString("name"));
            // }

        } catch (SQLException se) {
            // Handle errors for JDBC
            System.out.println("SQL Error: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            System.out.println("Class Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                System.out.println("Error closing statement: " + se.getMessage());
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("Error closing connection: " + se.getMessage());
            }
        }
    }
}
