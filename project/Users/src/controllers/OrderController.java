package controller;

import controller.Interface.IOrderController;
import models.Order;
import repositories.OrderRepository;
import repositories.CurrentUserRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import models.OrderItem;
import java.util.ArrayList;



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
    public void viewOrders() {

        if (currentUserRepository.getCurrentUser() == null) {
            System.out.println("Сначала войдите в систему.");
            return;
        }
        List<Order> orders = orderRepository.getOrdersByUserId(currentUserRepository.getCurrentUser().getId());
        System.out.println("Ваши заказы:");

        //Короткая ЛЯМБДА ВЫРАЖЕНИЕ
        orders.forEach(o -> System.out.println("Заказ №" + o.getId() +
                ", сумма: " + o.getTotalPrice() +
                ", блюда: " + o.getDishNames()));
    }

    public void deleteOrder() {
        if (currentUserRepository.getCurrentUser() == null) {
            System.out.println("Сначала войдите в систему.");
            return;
        }
        System.out.print("Введите ID заказа для удаления: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        boolean success = orderRepository.deleteOrder(orderId);
        if (success) {
            System.out.println("Заказ успешно удалён.");
        } else {
            System.out.println("Ошибка при удалении заказа или заказ не найден.");
        }
    }

    @Override
    public void placeOrder() {
        if (currentUserRepository.getCurrentUser() == null) {
            System.out.println("Сначала войдите в систему.");
            return;
        }

        System.out.print("Введите ID блюда: ");
        int menuId = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        if (quantity <= 0) {
            System.out.println("Количество должно быть положительным.");
            return;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setMenuId(menuId);
        orderItem.setQuantity(quantity);


        Order order = new Order();
        order.setUserId(currentUserRepository.getCurrentUser().getId());

        order.setTotalPrice(0.0);

        List<OrderItem> items = new ArrayList<>();
        items.add(orderItem);
        order.setOrderItems(items);

        boolean success = orderRepository.createOrder(order);
        if (success) {
            System.out.println("Заказ размещён!");
        } else {
            System.out.println("Ошибка при размещении заказа.");
        }
    }







}
