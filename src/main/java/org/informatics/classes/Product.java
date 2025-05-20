package org.informatics.classes;

import org.informatics.enums.ProductCategory;
import org.informatics.services.ProductIdentificationService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {
    private static BigDecimal deliveryPrice;

    private final long productId;
    private final String productName;
    private final ProductCategory productCategory;
    private final LocalDate productExpiryDate;

    public Product(String productName, ProductCategory productCategory, LocalDate productExpiryDate) {
        this.productId = ProductIdentificationService.generateProductId();

        this.productName = productName;
        this.productCategory = productCategory;
        this.productExpiryDate = productExpiryDate;
    }

    public String getProductName() {
        return productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public LocalDate getProductExpiryDate() {
        return productExpiryDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCategory=" + productCategory +
                ", productExpiryDate=" + productExpiryDate +
                '}';
    }
}
