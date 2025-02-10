package repositories.Interface;

import models.User;

public interface IRegistrationRepository {
    boolean registerUser(User user);
    User getUserByUsername(String username);
}
