package repositories.Interface;

import models.CurrentUser;

public interface ICurrentUserRepository {
    CurrentUser getCurrentUser();
    void setCurrentUser(CurrentUser currentUser);
}
