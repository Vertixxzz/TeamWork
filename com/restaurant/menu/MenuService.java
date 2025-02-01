package com.restaurant.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private List<MenuItem> menuItems = new ArrayList<>();
    private int nextId = 1;
    public void addMenuItem(MenuItem item) {
        item.setId(nextId++);
        menuItems.add(item);
        System.out.println("Item added: " + item);
    }
    public void removeMenuItem(int id) {
        MenuItem itemToRemove = null;
        for (MenuItem item : menuItems) {
            if (item.getId() == id) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            menuItems.remove(itemToRemove);
            System.out.println("Item removed: " + itemToRemove);
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    public void showMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("The menu is empty.");
        } else {
            System.out.println("Restaurant Menu:");
            for (MenuItem item : menuItems) {
                System.out.println(item);
            }
        }
    }
}