package models;

import repositories.MenuRepository;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int dish_id;
    private String dish_name;
    private int dishPrice;


    public Menu(String dish_name, int dish_id, double dish_price) {
        setDish_name(dish_name);
        setDish_id(dish_id);
        setDish_price(dish_price);
    }

    public Menu(int dish_id, String dish_name, int dish_price) {
        this(dish_name, dish_price);
        this.dish_id = dish_id;
    }

    public int getDishId() {
        return dish_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public int getDish_price() {
        return dish_price;
    }

    public void setDish_price(int dish_price) {
        this.dish_price = dish_price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }





    @Override
    public String toString() {
        return String.format("Dish ID: %-4d | Name: %-20s | Price: %-3d | Rating: %-4.1f",
                dish_id, dish_name, dish_price, rating);
    }
}