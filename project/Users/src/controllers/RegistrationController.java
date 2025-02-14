package controller;

import controller.Interface.IRegistrationController;
import models.User;
import repositories.RegistrationRepository;
import java.util.Scanner;

public class RegistrationController implements IRegistrationController {
    private RegistrationRepository registrationRepository;
    private Scanner scanner;

    public RegistrationController() {
        registrationRepository = new RegistrationRepository();
        scanner = new Scanner(System.in);
    }

    @Override
    public void register() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        User user = new User(0, username, password);
        boolean success = registrationRepository.registerUser(user);
        if (success) {
            System.out.println("Регистрация успешна!");
            //DATA VALIDATION
        } else {
            System.out.println("Ошибка регистрации.");
        }
    }
}
