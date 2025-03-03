package auth;


import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static final List<user> users = new ArrayList<>();

    public static void addUser(user user) {
        users.add(user);
    }

    public static boolean verifyUser(String email, String password) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }
}
