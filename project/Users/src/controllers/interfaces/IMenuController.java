package repositories.controllers.interfaces;

import models.Menu;

public interface IMenuController {
    String createMenu(Menu menu);
    String getMenuById(int dish_id);
    String getAllMenu();
    String deleteMenu(int menuId);
    String updateMenu(int menuId, Menu updatedMenu);
}