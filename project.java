import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        // добавление блюд в меню. пока что меню очень примерное. потом будет интеграция с партом где вся вся еда
        restaurant.addMenuItem(new MenuItem(1, "Чизбургер", "Сочный бургер с сыром", 5.99, "Бургеры"));
        restaurant.addMenuItem(new MenuItem(2, "Пепперони", "Пицца с пепперони и сыром", 8.99, "Пицца"));
        restaurant.addMenuItem(new MenuItem(3, "Кока-Кола", "Газированный напиток", 2.50, "Напитки"));

        Scanner scanner = new Scanner(System.in); // чтобы читать

        while (true) {
            System.out.println("\n1. Посмотреть меню");
            System.out.println("2. Создать заказ");
            System.out.println("3. Посмотреть заказы");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
//через свитч будет быстрее и менее запарнее
            switch (choice) {
                case 1:
                    System.out.println("Меню ресторана:"); //вот сюда вот будем впихивать или импортировать меню
                    for (MenuItem item : restaurant.getMenu()) {
                        System.out.println(item);
                    }
                    break;
                case 2:
                    List<MenuItem> orderItems = new ArrayList<>();
                    while (true) {
                        System.out.print("Введите ID блюда (или 0 для завершения заказа): "); //вероятнее всего будем переписывать систему заканчивания заказа. 0 выглядит убого
                        int itemId = scanner.nextInt();
                        scanner.nextLine();
                        if (itemId == 0) break; //выход из системы
                        MenuItem item = restaurant.getMenuItemById(itemId);
                        if (item != null) {
                            orderItems.add(item);
                        } else {
                            System.out.println("Блюдо не найдено.");
                        }
                    }
                    if (!orderItems.isEmpty()) {
                        Order order = restaurant.createOrder(orderItems);
                        System.out.println("Создан заказ #" + order.getId() + " на сумму $" + order.getTotalPrice());
                    }
                    break;
                case 3: //будущая система истории заказов наверное??
                    List<Order> orders = restaurant.getOrders();
                    if (orders.isEmpty()) {
                        System.out.println("Заказов пока нет.");
                    } else {
                        for (Order order : orders) {
                            System.out.println(order);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Выход...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
    }
}
