package util;

public class PasswordValidator {

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.matches("\\d{4,12}");
    }
}
