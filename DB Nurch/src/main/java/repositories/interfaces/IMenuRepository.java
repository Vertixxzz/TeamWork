package repositories.interfaces;

import models.MenuItem;

import java.util.List;

public interface IMenuRepository {
    boolean addMenuItem(MenuItem item);
    MenuItem getMenuItemById(int id);
    List<MenuItem> getAllMenuItems();
}