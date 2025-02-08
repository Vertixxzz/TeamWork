package repositories.controllers.interfaces;
import model.User;
public interface ICurrentUserController {
    User getUserInfo(String name);
    String updateUser(String name, User updateUser);
    String deleteUser(String name);
}
