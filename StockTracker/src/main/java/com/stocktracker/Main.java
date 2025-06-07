package com.stocktracker;

import com.stocktracker.manager.ProductManager;
import com.stocktracker.manager.UserManager;
import com.stocktracker.model.Product;
import com.stocktracker.model.User;
import com.stocktracker.service.StockChecker;
import com.stocktracker.service.StockMonitorTask;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        ProductManager productManager = new ProductManager();
        StockChecker stockChecker = new StockChecker();
        StockMonitorTask stockMonitor = new StockMonitorTask();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        stockMonitor.startMonitoring(60000L);
        System.out.println("=== WELCOME TO THE STOCK TRACKING SYSTEM ===");

        label82:
        while (running) {
            System.out.println("\nMenu:");
            if (!userManager.isLoggedIn()) {
                System.out.println("1. Register");
                System.out.println("2. Login");
            } else {
                System.out.println("3. Add Product");
                System.out.println("4. Remove Product");
                System.out.println("5. View Wishlist");
                System.out.println("6. Check Stock");
                System.out.println("7. Logout");
                System.out.println("8. Exit Application");
            }

            System.out.print("Your choice: ");
            switch (scanner.nextLine()) {
                case "1":
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    while (true) {
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                            System.out.print("Do you want to receive product updates? (yes/no): ");
                            String notifyInput = scanner.nextLine().toLowerCase();
                            String notify = notifyInput.equals("yes") ? "email" : "none";
                            userManager.register(username, password, email, notify);
                            continue label82;
                        }

                        System.out.println("❌ Invalid email format. Please try again.");
                    }

                case "2":
                    System.out.print("Username: ");
                    String loginUser = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginPass = scanner.nextLine();
                    userManager.login(loginUser, loginPass);
                    break;

                case "3":
                    User user = userManager.getCurrentUser();
                    System.out.print("Website: ");
                    String site = scanner.nextLine();
                    System.out.print("Product code: ");
                    String code = scanner.nextLine();
                    System.out.print("Size: ");
                    String size = scanner.nextLine();
                    System.out.print("Full product URL: ");
                    String url = scanner.nextLine();
                    productManager.addProductToUser(user, site, code, size, url);
                    break;

                case "4":
                    user = userManager.getCurrentUser();
                    System.out.print("Website: ");
                    String delSite = scanner.nextLine();
                    System.out.print("Product code: ");
                    String delCode = scanner.nextLine();
                    System.out.print("Size: ");
                    String delSize = scanner.nextLine();
                    System.out.print("Full product URL: ");
                    String delUrl = scanner.nextLine();
                    productManager.removeProductFromUser(user, delSite, delCode, delSize, delUrl);
                    break;

                case "5":
                    productManager.displayUserProducts(userManager.getCurrentUser());
                    break;

                case "6":
                    user = userManager.getCurrentUser();
                    System.out.println("📦 Supported websites:");
                    System.out.println(" - zara, pullandbear, bershka, stradivarius, oysho, massimodutti");
                    System.out.print("Website: ");
                    String s = scanner.nextLine();
                    System.out.print("Product code: ");
                    String c = scanner.nextLine();
                    System.out.print("Size: ");
                    String sz = scanner.nextLine();
                    System.out.print("Full product URL: ");
                    String u = scanner.nextLine();
                    Product tempProduct = new Product(s, c, sz, u);
                    boolean inStock = stockChecker.checkStock(tempProduct);
                    if (inStock) {
                        System.out.println("✅ Product is in stock!");
                    } else {
                        System.out.println("❌ Product is not in stock.");
                        System.out.print("Would you like to track this product and add it to your wishlist? (yes/no): ");
                        String ans = scanner.nextLine().toLowerCase();
                        if (ans.equals("yes")) {
                            stockMonitor.getStockTracker().addToWaitingList(tempProduct, user);
                            productManager.addProductToUser(user, s, c, sz, u);
                            System.out.println("📝 Product added to your wishlist and tracking has started.");
                        } else {
                            System.out.println("Product was not added to the wishlist.");
                        }
                    }
                    break;

                case "7":
                    userManager.logout();
                    break;

                case "8":
                    running = false;
                    stockMonitor.stopMonitoring();
                    System.out.println("🚪 Exiting the application...");
                    break;

                default:
                    System.out.println("⚠️ Invalid selection.");
            }
        }

        scanner.close();
    }
}
