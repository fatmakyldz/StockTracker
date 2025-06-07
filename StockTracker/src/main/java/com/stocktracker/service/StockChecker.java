package com.stocktracker.service;

import com.stocktracker.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class StockChecker {

    public boolean checkStock(Product product) {
        String url = product.getUrl();

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                    .referrer("https://www.google.com")
                    .header("Accept-Language", "tr-TR,tr;q=0.9,en-US,en;q=0.8")
                    .timeout(10000)
                    .get();

            // Önce tüm sayfa metninde arama yap
            String pageText = doc.body().text().toLowerCase();
            if (containsStockKeywords(pageText)) {
                product.setInStock(true);
                System.out.println("✅ Product is in stock (text body match).");
                return true;
            }

            // Ardından <script> içeriklerinde arama yap
            for (Element script : doc.select("script")) {
                String scriptContent = script.html().toLowerCase();

                if (containsStockKeywords(scriptContent)) {
                    product.setInStock(true);
                    System.out.println("✅ Product is in stock (script match).");
                    return true;
                }
            }

            // Hiçbir eşleşme yoksa, ürün stokta değildir
            product.setInStock(false);
            System.out.println("❌ Product is out of stock.");
            return false;

        } catch (Exception e) {
            System.out.println("🌐 Connection error or product not found: " + e.getMessage());
            product.setInStock(false);
            return false;
        }
    }

    private boolean containsStockKeywords(String text) {
        String[] stockKeywords = {
            "ekle", "sepete", "satın al", "add to bag", "add to cart", "buy now", "available", "in stock", "addtobag", "add_to_bag"
        };

        for (String keyword : stockKeywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
