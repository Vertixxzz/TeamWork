package controllers.interfaces;

public interface IMenuController {
    String addMenuItem(String name, String category, double price);
    String getMenuItemById(int id);
    String getAllMenuItems();
}