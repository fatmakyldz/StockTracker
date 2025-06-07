package com.stocktracker.service;

import com.stocktracker.model.Product;
import com.stocktracker.model.User;
import java.util.*;

public class StockTracker {

    private final Map<Product, List<User>> waitingList = new HashMap<>();

    public void addToWaitingList(Product product, User user) {
        waitingList.putIfAbsent(product, new ArrayList<>());
        List<User> users = waitingList.get(product);
        if (!users.contains(user)) {
            users.add(user);
            System.out.println("⏳ The product is out of stock. You've been added to the watchlist: " + product);
        } else {
            System.out.println("✔️ You are already tracking this product.");
        }
    }

    public Set<Product> getTrackedProducts() {
        return waitingList.keySet();
    }

    public void notifyAndRemove(Product product, NotificationService notifier) {
        List<User> users = waitingList.get(product);
        if (users != null) {
            for (User user : users) {
                notifier.notifyUser(user, product);
            }
            waitingList.remove(product);
        }
    }

    public boolean isEmpty() {
        return waitingList.isEmpty();
    }
}
