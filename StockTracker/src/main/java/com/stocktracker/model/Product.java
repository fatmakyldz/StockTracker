package com.stocktracker.model;

public class Product {
    private String site;
    private String productCode;
    private String size;
    private String url;
    private boolean inStock;

    public Product(String site, String productCode, String size, String url) {
        this.site = site;
        this.productCode = productCode;
        this.size = size;
        this.url = url;
        this.inStock = false;
    }

    public String getSite() { return site; }
    public String getProductCode() { return productCode; }
    public String getSize() { return size; }
    public String getUrl() { return url; }
    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        return site.equals(other.site)
                && productCode.equals(other.productCode)
                && size.equals(other.size)
                && url.equals(other.url);
    }

    @Override
    public int hashCode() {
        return (site + productCode + size + url).hashCode();
    }

    @Override
    public String toString() {
        return "Site: " + site + " | Kod: " + productCode + " | Beden: " + size + " | URL: " + url + " | Stok: " + (inStock ? "Var" : "Yok");
    }
}
