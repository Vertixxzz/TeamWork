package com.restaurant.menu;

public class Soup extends MenuItem {
    private boolean isVegetarian; // Дополнительное свойство для супов

    public Soup(int id, String name, String description, double price, boolean isVegetarian) {
        super(id, name, description, price);
        this.isVegetarian = isVegetarian;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    @Override
    public String toString() {
        return super.toString() + " [Vegetarian: " + isVegetarian + "]";
    }
}