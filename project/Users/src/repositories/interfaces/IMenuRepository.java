package repositories.Interface;

import models.Menu;
import java.util.List;

public interface IMenuRepository {
    List<Menu> getAllMenuItems();
    Menu getMenuItemById(int id);
    boolean addMenuItem(Menu menu);  // Новый метод для добавления блюда
}
