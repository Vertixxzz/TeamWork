package controller;

import controller.Interface.IMenuController;
import models.Menu;
import models.Role;
import models.Category;
import repositories.MenuRepository;
import repositories.CurrentUserRepository;

import java.util.Scanner;

public class MenuController implements IMenuController {
    private MenuRepository menuRepository;
    private Scanner scanner;
    private CurrentUserRepository currentUserRepository;

    public MenuController() {
        menuRepository = new MenuRepository();
        scanner = new Scanner(System.in);
        currentUserRepository = CurrentUserRepository.getInstance();
    }

    @Override
    public void showMenu() {
        //ЛЯМБДА ВЫРАЖЕНИЕ для вывода информаций в консоль
        menuRepository.getAllMenuItems().forEach(item -> {
            String category = (item.getCategory() != null) ? item.getCategory().getName() : "Без категории";
            System.out.println("ID: " + item.getId() +
                    " | " + item.getItemName() +
                    " | Цена: " + item.getPrice() +
                    " | Категория: " + category);
        });
    }

    @Override
    public void addFoodItem() {
        // Проверка ролей
        if (currentUserRepository.getCurrentUser() == null ||
                !(currentUserRepository.getCurrentUser().getRole() == Role.ADMIN ||
                        currentUserRepository.getCurrentUser().getRole() == Role.MANAGER)) {
            System.out.println("У вас нет доступа для добавления блюда.");
            return;
        }

        System.out.print("Введите название блюда: ");
        String itemName = scanner.nextLine();
        if (itemName.isEmpty()) {
            System.out.println("Название блюда не может быть пустым.");
            return;
        }

        System.out.print("Введите цену блюда: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректное значение цены.");
            return;
        }

        System.out.print("Введите категорию блюда: ");
        String categoryName = scanner.nextLine();
        Category category = new Category();
        category.setName(categoryName);

        Menu menu = new Menu();
        menu.setItemName(itemName);
        menu.setPrice(price);
        menu.setCategory(category);

        boolean success = menuRepository.addMenuItem(menu);
        if (success) {
            System.out.println("Блюдо успешно добавлено!");
        } else {
            System.out.println("Ошибка при добавлении блюда.");
        }
    }
}
