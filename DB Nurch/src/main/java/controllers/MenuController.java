package controllers;

import controllers.interfaces.IMenuController;
import models.MenuItem;
import repositories.interfaces.IMenuRepository;

import java.util.List;

public class MenuController implements IMenuController {
    private final IMenuRepository repo;

    public MenuController(IMenuRepository repo) {
        this.repo = repo;
    }

    @Override
    public String addMenuItem(String name, String category, double price) {
        MenuItem item = new MenuItem(name, category, price);
        boolean added = repo.addMenuItem(item);
        return added ? "Menu item added successfully" : "Failed to add menu item";
    }

    @Override
    public String getMenuItemById(int id) {
        MenuItem item = repo.getMenuItemById(id);
        return (item != null) ? item.toString() : "Menu item not found";
    }

    @Override
    public String getAllMenuItems() {
        List<MenuItem> items = repo.getAllMenuItems();
        StringBuilder response = new StringBuilder();
        for (MenuItem item : items) {
            response.append(item.toString()).append("\n");
        }
        return response.toString();
    }
}