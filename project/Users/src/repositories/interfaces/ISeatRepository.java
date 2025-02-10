package repositories.Interface;

import models.Seat;
import java.util.List;

public interface ISeatRepository {
    List<Seat> getAllSeats();
    boolean reserveSeat(int seatId, int userId);
    boolean reserveSeatByNumber(int seatNumber, int userId); // бронирование по номеру места
}
