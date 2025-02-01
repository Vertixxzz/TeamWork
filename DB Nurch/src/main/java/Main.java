import controllers.MenuController;
import controllers.interfaces.IMenuController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.MenuRepository;
import repositories.interfaces.IMenuRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432/restaurant", "postgres", "password");
        IMenuRepository repo = new MenuRepository(db);
        IMenuController controller = new MenuController(repo);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nRestaurant Menu Management");
            System.out.println("1. Add menu item");
            System.out.println("2. View menu item by ID");
            System.out.println("3. View all menu items");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            if (option == 0) break;
            switch (option) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter category: ");
                    String category = scanner.next();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.println(controller.addMenuItem(name, category, price));
                    break;
                case 2:
                    System.out.print("Enter item ID: ");
                    int id = scanner.nextInt();
                    System.out.println(controller.getMenuItemById(id));
                    break;
                case 3:
                    System.out.println(controller.getAllMenuItems());
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        scanner.close();
    }
}