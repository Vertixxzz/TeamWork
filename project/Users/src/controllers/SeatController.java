package controller;

import models.Seat;
import models.CurrentUser;
import repositories.SeatRepository;
import repositories.CurrentUserRepository;
import java.util.List;
import java.util.Scanner;

public class SeatController {
    private SeatRepository seatRepository;
    private Scanner scanner;

    private CurrentUserRepository currentUserRepository;

    public SeatController() {
        seatRepository = new SeatRepository();
        scanner = new Scanner(System.in);
        currentUserRepository = CurrentUserRepository.getInstance();
    }

    // Метод для отображения списка мест
    public void showSeats() {
        List<Seat> seats = seatRepository.getAllSeats();
        System.out.println("\n=== Список мест ===");
        for (Seat seat : seats) {
            if (seat.isReserved()) {
                System.out.println("Место " + seat.getSeatNumber() + " (Забронировано: " + seat.getReservedByName() + ")");
            } else {
                System.out.println("Место " + seat.getSeatNumber() + " (Свободно)");
            }
        }
        System.out.println("===================");
    }

//DATA VALIDATION
    public void reserveSeat() {
        CurrentUser currentUser = currentUserRepository.getCurrentUser();
        if (currentUser == null) {
            System.out.println("Сначала войдите в систему.");
            return;
        }

        showSeats();
        System.out.print("Введите номер места для бронирования: ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine();

        boolean success = seatRepository.reserveSeatByNumber(seatNumber, currentUser.getId());
        if (success) {
            System.out.println("Место " + seatNumber + " успешно забронировано!");
            //DATA VALIDATION
        } else {
            System.out.println("Не удалось забронировать место. Возможно, оно уже занято.");
        }
    }
}
