package repositories;

import repositories.Interface.IOrderRepository;
import repositories.Interface.IMenuRepository;
import repositories.Interface.IRegistrationRepository;
// Главный ФАКТОРИ который используется для создания экземпляров различных репозиториев
public class RepositoryFactory {

    public static IOrderRepository getOrderRepository() {
        return new OrderRepository();
    }

    public static IMenuRepository getMenuRepository() {
        return new MenuRepository();
    }

    public static IRegistrationRepository getRegistrationRepository() {
        return new RegistrationRepository();
    }
}
