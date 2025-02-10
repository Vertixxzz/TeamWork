package controller;

import controller.Interface.IUserController;
import models.CurrentUser;
import models.User;
import repositories.CurrentUserRepository;
import repositories.RegistrationRepository;
import java.util.Scanner;

public class UserController implements IUserController {

    private CurrentUserRepository currentUserRepository = CurrentUserRepository.getInstance();
    private RegistrationRepository registrationRepository;
    private Scanner scanner;

    public UserController() {
        registrationRepository = new RegistrationRepository();
        scanner = new Scanner(System.in);
    }

    @Override
    public void login() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        User user = registrationRepository.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            CurrentUser currentUser = new CurrentUser(user.getId(), user.getUsername());
            currentUserRepository.setCurrentUser(currentUser);
            System.out.println("Пользователь " + username + " успешно вошёл в систему.");
        } else {
            System.out.println("Неверное имя пользователя или пароль.");
        }
    }

    @Override
    public void logout() {
        currentUserRepository.setCurrentUser(null);
        System.out.println("Вы вышли из системы.");
    }
}
