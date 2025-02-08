package repositories.repositories.interfaces;

import models.Menu;

import java.util.List;

public interface IMenuRepository {
    boolean createMenu(Menu menu);
    Menu getMenuById(int menu_id);
    List<Menu> getAllMenues();
    boolean deleteMenu(int menuId);
    boolean addMenu(Menu menu);

    void updateMenuRating(int menuId);
}