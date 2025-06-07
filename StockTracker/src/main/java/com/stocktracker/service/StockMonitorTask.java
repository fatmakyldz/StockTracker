package com.stocktracker.service;

import com.stocktracker.model.Product;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class StockMonitorTask {

    private final Timer timer = new Timer();
    private final StockTracker stockTracker = new StockTracker();
    private final StockChecker stockChecker = new StockChecker();
    private final NotificationService notificationService = new NotificationService();

    // Arka planda sürekli çalışan zamanlayıcı başlatılır
    public void startMonitoring(long intervalMillis) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (stockTracker.isEmpty()) return;

                System.out.println("\n⏰ Automatic stock monitoring is running...");

                Set<Product> products = stockTracker.getTrackedProducts();

                for (Product product : products) {
                    boolean inStock = stockChecker.checkStock(product);
                    if (inStock) {
                        stockTracker.notifyAndRemove(product, notificationService);
                    }
                }
            }
        }, 0, intervalMillis);
    }

    public void stopMonitoring() {
        timer.cancel();
    }

    // Ana sınıfların erişebilmesi için
    public StockTracker getStockTracker() {
        return stockTracker;
    }
}
