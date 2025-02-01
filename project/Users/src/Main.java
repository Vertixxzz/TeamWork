import controllers.OrderController;
import models.MenuItem;
import models.Order;
import repositories.OrderRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Главный класс для запуска приложения.
 */
public class Main {
    public static void main(String[] args) {
        // Инициализация репозитория и контроллера
        OrderRepository orderRepository = new OrderRepository();
        OrderController orderController = new OrderController(orderRepository);

        // Создание тестовых данных
        List<MenuItem> items1 = Arrays.asList(
                new MenuItem("Пицца", 10.99),
                new MenuItem("Кола", 1.99)
        );

        List<MenuItem> items2 = Arrays.asList(
                new MenuItem("Бургер", 8.99),
                new MenuItem("Фри", 3.99)
        );

        // Создание заказов
        orderController.createOrder("Иван", items1);
        orderController.createOrder("Мария", items2);

        // Получение всех заказов
        List<Order> orders = orderController.getAllOrders();
        for (Order order : orders) {
            System.out.println("Заказ #" + order.getId() + ": " + order.getCustomerName() + ", Сумма: $" + order.getTotalPrice());
        }
    }
}