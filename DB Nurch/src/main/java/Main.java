import controllers.DishController;

public class Main {
    public static void main(String[] args) {
        DishController dishController = new DishController();
        System.out.println("Welcome to our Restaurant Menu:");
        dishController.displayMenu();
    }
}

