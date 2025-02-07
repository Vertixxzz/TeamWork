package controllers;

import models.Order;
import models.MenuItem;
import repositories.interfaces.IOrderRepository;
import controllers.interfaces.IOrderController;

import java.util.List;


public class OrderController implements IOrderController {
    private final IOrderRepository orderRepository;

    public OrderController(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void createOrder(String customerName, List<MenuItem> items) {
        int newOrderId = orderRepository.getAllOrders().size() + 1; // Генерация ID
        Order order = new Order(newOrderId, customerName, items);
        orderRepository.addOrder(order);

    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.getOrderById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public void updateOrder(int orderId, String customerName, List<MenuItem> items) {
        Order order = new Order(orderId, customerName, items);
        orderRepository.updateOrder(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderRepository.deleteOrder(orderId);
    }
}
