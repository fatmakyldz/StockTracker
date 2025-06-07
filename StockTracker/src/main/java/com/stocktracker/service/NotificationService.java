package com.stocktracker.service;

import com.stocktracker.model.Product;
import com.stocktracker.model.User;

public class NotificationService {

    public void notifyUser(User user, Product product) {
        if (user.getNotifyPreference().equalsIgnoreCase("email")) {
            notifyUserByEmail(user, product);
        }
    }

    private void notifyUserByEmail(User user, Product product) {
        // Gerçek e-posta gönderimi değil, simülasyon
        System.out.println("📧 E-posta gönderildi → " + user.getEmail()
                + " | Ürün stokta: " + product.getProductCode());
    }
}
