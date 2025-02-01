package com.restaurant.menu;

public class Drink extends MenuItem {
    private boolean isAlcoholic;

    public Drink(int id, String name, String description, double price, boolean isAlcoholic) {
        super(id, name, description, price);
        this.isAlcoholic = isAlcoholic;
    }
    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    @Override
    public String toString() {
        return super.toString() + " [Alcoholic: " + isAlcoholic + "]";
    }
}