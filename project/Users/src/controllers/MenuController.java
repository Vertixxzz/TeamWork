package repositories.controllers;

import controllers.interfaces.IMenuController;
import models.CurrentUser;
import models.Menu;
import repositories.interfaces.IMenuRepository;

import java.util.List;

public class MenuController implements IMenuController {
    private final IMenuRepository repo;


    public MenuController(IMenuRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createMenu(Menu menu) {
        boolean created = repo.createMenu(menu);
        return (created) ? "Dish has been created" : "Dish creation error";
    }

    @Override
    public String getMenuById(int id) {
        Menu menu = repo.getMenuById(id);
        return (movie == null) ? "Menu was not found" : menu.toString();
    }


    @Override
    public String getAllMenu() {
        List<Menu> movies = repo.getAllMenu();
        StringBuilder response = new StringBuilder();
        for(Menu menu: menu){
            response.append(menu.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String addReviewForMenu(int menuId, int userId, String reviewText) {
        Review review = new Review(menuId, CurrentUser.getCurrentUser().getUser_id(), reviewText);
        boolean created = reviewRepo.createReview(review);
        return created ? "Review has been added." : "Error adding review.";
    }

    @Override
    public String getReviewsByMenuId(int menuId) {
        List<Review> reviews = reviewRepo.getReviewsByMenuId(menuId);
        StringBuilder response = new StringBuilder();
        for (Review review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
    }
    @Override
    public String deleteMenu(int menuId) {
        boolean deleted = repo.deleteMenu(menuId);
        return deleted ? "Menu has been deleted" : "Error deleting menu";
    }

    @Override
    public String updateMovie(int menuId, Menu updatedMenu) {
        boolean updated = repo.updateMenu(updatedMenu);
        return updated ? "Menu has been updated" : "Error updating menu";
    }
}
