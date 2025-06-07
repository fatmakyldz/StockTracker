package com.stocktracker.manager;

import com.stocktracker.model.Product;
import com.stocktracker.model.User;

import java.util.List;

public class ProductManager {

    private static final List<String> SUPPORTED_SITES = List.of(
            "zara", "pullandbear", "bershka", "stradivarius", "oysho", "massimodutti"
    );

    public void addProductToUser(User user, String site, String code, String size, String url) {
        if (!SUPPORTED_SITES.contains(site.toLowerCase())) {
            System.out.println("❌ Unsupported site: " + site);
            System.out.println("Supported sites: " + String.join(", ", SUPPORTED_SITES));
            return;
        }

        Product product = new Product(site.toLowerCase(), code, size, url);
        if (!user.getWishList().contains(product)) {
            user.addProductToWishList(product);
            System.out.println("✅ Product added: " + product);
        } else {
            System.out.println("⚠️ This product is already in your wishlist.");
        }
    }

    public void removeProductFromUser(User user, String site, String code, String size, String url) {
        Product product = new Product(site.toLowerCase(), code, size, url);
        if (user.getWishList().contains(product)) {
            user.removeProductFromWishList(product);
            System.out.println("🗑️ Product removed: " + product);
        } else {
            System.out.println("Product not found in your wishlist.");
        }
    }

    public void displayUserProducts(User user) {
        user.printWishList();
    }
}
