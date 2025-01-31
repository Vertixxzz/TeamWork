package controllers;

import models.Dish;
import repositories.DishRepository;

import java.util.List;

public class DishController {
    private DishRepository dishRepository;

    public DishController() {
        this.dishRepository = new DishRepository();
    }

    public void displayMenu() {
        List<Dish> dishes = dishRepository.getAllDishes();
        for (Dish dish : dishes) {
            System.out.println(dish.getName() + " - $" + dish.getPrice());
        }
    }
}