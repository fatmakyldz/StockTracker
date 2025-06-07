package com.stocktracker.manager;

import com.stocktracker.model.User;

public class UserManager {
    private User currentUser;

    public void register(String username, String password, String email, String notifyPref) {
        if (!isValidEmail(email)) {
            System.out.println("❌ Invalid email address!");
            return;
        }

        if (!notifyPref.equalsIgnoreCase("email")) {
            System.out.println("⚠️ Currently, only email notifications are supported. Notification preference set to 'email'.");
            notifyPref = "email";
        }

        currentUser = new User(username, password, email, notifyPref);
        System.out.println("✅ Registration successful!");
    }

    public void login(String username, String password) {
        if (currentUser != null &&
                currentUser.getUsername().equals(username) &&
                currentUser.getPassword().equals(password)) {
            System.out.println("✅ Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("❌ Incorrect username or password!");
        }
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("👋 Görüşmek üzere, " + currentUser.getUsername());
            currentUser = null;
        }
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}

