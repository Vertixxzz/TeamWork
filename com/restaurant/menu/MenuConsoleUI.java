package com.restaurant.menu;

import java.util.Scanner;

public class MenuConsoleUI {
    private MenuService menuService;
    private Scanner scanner;

    public MenuConsoleUI(MenuService menuService) {
        this.menuService = menuService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nMenu Management:");
            System.out.println("1. Show Menu");
            System.out.println("2. Add Soup");
            System.out.println("3. Add Pasta");
            System.out.println("4. Add Drink");
            System.out.println("5. Remove Item");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    menuService.showMenu();
                    break;
                case 2:
                    addSoup();
                    break;
                case 3:
                    addPasta();
                    break;
                case 4:
                    addDrink();
                    break;
                case 5:
                    removeMenuItem();
                    break;
                case 6:
                    System.out.println("Exiting menu management.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addSoup() {
        System.out.print("Enter soup name: ");
        String name = scanner.nextLine();
        System.out.print("Enter soup description: ");
        String description = scanner.nextLine();
        System.out.print("Enter soup price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Is the soup vegetarian? (true/false): ");
        boolean isVegetarian = scanner.nextBoolean();
        scanner.nextLine(); // Очистка буфера

        Soup soup = new Soup(0, name, description, price, isVegetarian);
        menuService.addMenuItem(soup);
    }

    private void addPasta() {
        System.out.print("Enter pasta name: ");
        String name = scanner.nextLine();
        System.out.print("Enter pasta description: ");
        String description = scanner.nextLine();
        System.out.print("Enter pasta price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Enter pasta sauce type: ");
        String sauceType = scanner.nextLine();

        Pasta pasta = new Pasta(0, name, description, price, sauceType);
        menuService.addMenuItem(pasta);
    }

    private void addDrink() {
        System.out.print("Enter drink name: ");
        String name = scanner.nextLine();
        System.out.print("Enter drink description: ");
        String description = scanner.nextLine();
        System.out.print("Enter drink price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Is the drink alcoholic? (true/false): ");
        boolean isAlcoholic = scanner.nextBoolean();
        scanner.nextLine(); // Очистка буфера

        Drink drink = new Drink(0, name, description, price, isAlcoholic);
        menuService.addMenuItem(drink);
    }

    private void removeMenuItem() {
        System.out.print("Enter the ID of the item to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        menuService.removeMenuItem(id);
    }
}