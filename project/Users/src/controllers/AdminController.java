package controllers;

import controllers.interfaces.IAdminController;
import models.User;
import repositories.interfaces.IAdminRepository;


import java.util.List;

public class AdminController implements IAdminController {

    private final IAdminRepository adminRepository;
    public AdminController(IUserRepository userRepository, IAdminRepository adminRepository) {

        this.adminRepository = adminRepository;
    }
    @Override
    public String deleteAnUser(int id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            return "User do not exist";
        }
        return adminRepository.deleteAnUser(id) ? "User deleted SUCCESSFULLY" : "User NOT_FOUND";
    }


    @Override
    public String getUsersByRole(String role) {
        List<User> users = adminRepository.getUsersByRole(role);
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(user.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean isAdmin(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            System.out.println("(user == null) is true");
            return false;
        }
        if (!user.getRole().trim().equalsIgnoreCase("admin")) {
            System.out.println("(user.getRole() != \"admin\")");
            return false;
        }
        return true;

    }
}