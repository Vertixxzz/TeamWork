package repositories;

import models.CurrentUser;
import repositories.Interface.ICurrentUserRepository;

//СИНГЛТОН для поддерживания пользователя в единственном экземпляре

public class CurrentUserRepository implements ICurrentUserRepository {

    private static CurrentUserRepository instance;

    private CurrentUser currentUser;

    private CurrentUserRepository() {}

    public static CurrentUserRepository getInstance() {
        if (instance == null) {
            instance = new CurrentUserRepository();
        }
        return instance;
    }

    @Override
    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }
}
