package controller;

import controller.Interface.IOrderController;
import models.Order;
import repositories.OrderRepository;
import repositories.CurrentUserRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderController implements IOrderController {
    private OrderRepository orderRepository;
    private CurrentUserRepository currentUserRepository;
    private Scanner scanner;

    public OrderController() {
        orderRepository = new OrderRepository();
        currentUserRepository = CurrentUserRepository.getInstance();
        scanner = new Scanner(System.in);
    }

    @Override
    public void placeOrder() {

        System.out.print("Введите ID блюда: ");
        int menuId = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        if (quantity <= 0) {
            System.out.println("Количество должно быть положительным.");
            return;
        }
        // Создание заказа
        System.out.println("Заказ размещён (пример).");
    }

    @Override
    public void viewOrders() {

        List<Order> orders = orderRepository.getOrdersByUserId(currentUserRepository.getCurrentUser().getId());
        List<Order> filtered = orders.stream()
                .filter(o -> o.getTotalPrice() > 100)
                .collect(Collectors.toList());
        System.out.println("Ваши заказы (отфильтрованные):");
        filtered.forEach(o -> System.out.println("Заказ №" + o.getId() + ", сумма: " + o.getTotalPrice()));
    }
}
