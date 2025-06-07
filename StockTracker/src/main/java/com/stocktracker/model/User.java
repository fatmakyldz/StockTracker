package com.stocktracker.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private String notifyPreference;
    private List<Product> wishList = new ArrayList<>();

    public User(String username, String password, String email, String notifyPreference) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.notifyPreference = notifyPreference;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNotifyPreference() {
        return notifyPreference;
    }

    public void addProductToWishList(Product product) {
        wishList.add(product);
    }

    public void removeProductFromWishList(Product product) {
        wishList.remove(product);
    }

    public List<Product> getWishList() {
        return wishList;
    }

    public void printWishList() {
        if (wishList.isEmpty()) {
            System.out.println("🔍 İstek listeniz boş.");
        } else {
            System.out.println("📝 İstek Listeniz:");
            for (Product p : wishList) {
                System.out.println(" - " + p);
            }
        }
    }
}
