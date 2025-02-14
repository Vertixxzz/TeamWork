package models;

import java.util.List;

public class Order {
    private int id;
    private int userId;
    private List<OrderItem> orderItems;
    private double totalPrice;
    private String dishNames;

    public Order() {}

    public Order(int id, int userId, List<OrderItem> orderItems, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public String getDishNames() {
        return dishNames;
    }

    public void setDishNames(String dishNames) {
        this.dishNames = dishNames;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
