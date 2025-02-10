import controller.MenuController;
import controller.OrderController;
import controller.RegistrationController;
import controller.UserController;
import controller.SeatController;
import java.util.Scanner;

public class MyApplication {
    private UserController userController;
    private RegistrationController registrationController;
    private MenuController menuController;
    private OrderController orderController;
    private SeatController seatController;
    private Scanner scanner;

    public MyApplication() {
        userController = new UserController();
        registrationController = new RegistrationController();
        menuController = new MenuController();
        orderController = new OrderController();
        seatController = new SeatController();
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice = -1;
        do {
            System.out.println("\n=== Ресторанное приложение ===");
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Просмотр меню");
            System.out.println("4. Размещение заказа");
            System.out.println("5. Просмотр заказов");
            System.out.println("6. Добавить блюдо");
            System.out.println("7. Просмотр и бронирование мест");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера
            } else {
                System.out.println("Введите корректное число.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    registrationController.register();
                    break;
                case 2:
                    userController.login();
                    break;
                case 3:
                    menuController.showMenu();
                    break;
                case 4:
                    orderController.placeOrder();
                    break;
                case 5:
                    orderController.viewOrders();
                    break;
                case 6:
                    menuController.addFoodItem();
                    break;
                case 7:
                    seatController.showSeats();
                    System.out.print("Вы хотите забронировать место? (Да/Нет): ");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("Да")) {
                        seatController.reserveSeat();
                    }
                    break;
                case 0:
                    System.out.println("Выход из приложения.");
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
        } while (choice != 0);
    }
}
