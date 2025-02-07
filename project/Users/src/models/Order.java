package models;

import java.util.List;

/**
 * Модель заказа.
 * Содержит информацию о заказе: ID, имя клиента, список блюд и общую стоимость.
 */
public class Order {
    private int id;
    private String customerName;
    private List<MenuItem> items;
    private double totalPrice;


    // Конструктор
    public Order(int id, String customerName, List<MenuItem> items) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = calculateTotalPrice();
    }

    // Метод для расчета общей стоимости заказа
    private double calculateTotalPrice() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}