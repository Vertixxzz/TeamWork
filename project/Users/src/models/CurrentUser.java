package models;

public class CurrentUser {
    private int id;
    private String username;
    private Role role;

    public CurrentUser() {}

    public CurrentUser(int id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public CurrentUser(int id, String username) {
        this(id, username, Role.CUSTOMER);
    }

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
