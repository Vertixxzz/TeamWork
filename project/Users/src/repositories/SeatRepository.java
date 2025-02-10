package repositories;

import data.PostgresDB;
import models.Seat;
import repositories.Interface.ISeatRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatRepository implements ISeatRepository {
    private PostgresDB db;

    public SeatRepository() {
        db = PostgresDB.getInstance();
    }

    @Override
    public List<Seat> getAllSeats() {
        List<Seat> seats = new ArrayList<>();
        String query = "SELECT s.id, s.seat_number, s.reserved, u.username " +
                "FROM seats s " +
                "LEFT JOIN users u ON s.user_id = u.id " +
                "ORDER BY s.seat_number";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Seat seat = new Seat();
                seat.setId(rs.getInt("id"));
                seat.setSeatNumber(rs.getInt("seat_number"));
                seat.setReserved(rs.getBoolean("reserved"));
                seat.setReservedByName(rs.getString("username"));
                seats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    @Override
    public boolean reserveSeat(int seatId, int userId) {
        String query = "UPDATE seats SET reserved = true, user_id = ? WHERE id = ? AND reserved = false";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, seatId);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean reserveSeatByNumber(int seatNumber, int userId) {
        String query = "UPDATE seats SET reserved = true, user_id = ? WHERE seat_number = ? AND reserved = false";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, seatNumber);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
