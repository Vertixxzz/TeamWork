package models;

public class CurrentUser {
    private int id;
    private String username;
    private Role role;

    // Пустой конструктор (может понадобиться для сериализации/десериализации)
    public CurrentUser() {}

    // Основной конструктор, который обязательно требует указания роли
    public CurrentUser(int id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // Удалён конструктор по умолчанию, который устанавливал роль CUSTOMER,
    // чтобы не допустить автоматическое присвоение неверной роли.

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
