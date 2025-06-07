package com.stocktracker.util;

import com.stocktracker.model.User;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {

    private static final Map<String, User> users = new HashMap<>();

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static boolean userExists(String username) {
        return users.containsKey(username);
    }

    public static Map<String, User> getAllUsers() {
        return users;
    }
}
