package controllers;

import data.DishRepository;
import data.DrinkRepository;
import models.Dish;
import models.Drink;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantController {
    private Scanner scanner;
    private List<Dish> orderedDishes;
    private List<Drink> orderedDrinks;

    public RestaurantController() {
        scanner = new Scanner(System.in);
        orderedDishes = new ArrayList<>();
        orderedDrinks = new ArrayList<>();
    }

    public void start() {
        System.out.print("Добрый день! Как вас зовут? ");
        String userName = scanner.nextLine();
        System.out.println("Приятно познакомиться, " + userName + "! Чем могу помочь?");

        boolean ordering = true;
        while (ordering) {
            System.out.println("Выберите категорию:");
            System.out.println("1. Блюда");
            System.out.println("2. Напитки");
            System.out.println("3. Завершить заказ");
            System.out.print("Введите номер категории: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    orderDish();
                    break;
                case "2":
                    orderDrink();
                    break;
                case "3":
                    ordering = false;
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
        choosePaymentMethod(userName);
    }

    private void orderDish() {
        try {
            List<Dish> dishes = DishRepository.getAllDishes();
            for (int i = 0; i < dishes.size(); i++) {
                System.out.println((i + 1) + ". " + dishes.get(i).getName() + " - " + dishes.get(i).getPrice() + "₽");
            }
            System.out.print("Введите номер блюда: ");
            int dishNumber = Integer.parseInt(scanner.nextLine()) - 1;
            if (dishNumber >= 0 && dishNumber < dishes.size()) {
                orderedDishes.add(dishes.get(dishNumber));
                System.out.println("Добавлено в заказ: " + dishes.get(dishNumber).getName());
            } else {
                System.out.println("Неверный номер.");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void orderDrink() {
        try {
            List<Drink> drinks = DrinkRepository.getAllDrinks();
            for (int i = 0; i < drinks.size(); i++) {
                System.out.println((i + 1) + ". " + drinks.get(i).getName() + " - " + drinks.get(i).getPrice() + "₽");
            }
            System.out.print("Введите номер напитка: ");
            int drinkNumber = Integer.parseInt(scanner.nextLine()) - 1;
            if (drinkNumber >= 0 && drinkNumber < drinks.size()) {
                orderedDrinks.add(drinks.get(drinkNumber));
                System.out.println("Добавлено в заказ: " + drinks.get(drinkNumber).getName());
            } else {
                System.out.println("Неверный номер.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void choosePaymentMethod(String userName) {
        System.out.println("Как вы хотите оплатить заказ?");
        System.out.println("1. Каспи QR");
        System.out.println("2. Халык QR");
        System.out.println("3. Рассрочка");
        System.out.println("4. Наличными");
        System.out.print("Введите номер способа оплаты: ");

        String choice = scanner.nextLine();
        String paymentMethod;
        switch (choice) {
            case "1": paymentMethod = "Каспи QR"; break;
            case "2": paymentMethod = "Халык QR"; break;
            case "3": paymentMethod = "Рассрочка"; break;
            case "4": paymentMethod = "Наличными"; break;
            default: paymentMethod = "Каспи QR"; break;
        }
        confirmOrder(userName, paymentMethod);
    }

    private void confirmOrder(String userName, String paymentMethod) {
        System.out.println("Ваш заказ:");
        for (Dish dish : orderedDishes) {
            System.out.println("- " + dish.getName());
        }
        for (Drink drink : orderedDrinks) {
            System.out.println("- " + drink.getName());
        }
        System.out.println("Способ оплаты: " + paymentMethod);
        System.out.println("Спасибо за заказ, " + userName + "!");
    }
}
