package util;

public class Validation {

    public static boolean isValidId(int id) {
        return id > 0;
    }
    public static boolean isValidPassword(String password) {
        return PasswordValidator.isValidPassword(password);
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidAge(int age) {
        return age > 0;
    }
}
