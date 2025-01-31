package com.restaurant.menu;

public class Pasta extends MenuItem {
    private String sauceType; // Дополнительное свойство для пасты

    public Pasta(int id, String name, String description, double price, String sauceType) {
        super(id, name, description, price);
        this.sauceType = sauceType;
    }

    public String getSauceType() {
        return sauceType;
    }

    public void setSauceType(String sauceType) {
        this.sauceType = sauceType;
    }

    @Override
    public String toString() {
        return super.toString() + " [Sauce: " + sauceType + "]";
    }
}
