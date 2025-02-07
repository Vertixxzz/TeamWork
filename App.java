import controllers.RestaurantController;

public class App {
    public static void main(String[] args) {
        // Создаем объект RestaurantController
        RestaurantController restaurantController = new RestaurantController();

        // Запускаем процесс взаимодействия с пользователем
        restaurantController.start();
    }
}
