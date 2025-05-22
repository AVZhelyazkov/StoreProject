package org.informatics.models;

import lombok.Getter;
import lombok.ToString;
import org.informatics.enums.ProductCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
public class Product {
    private final UUID productId;
    private final String productName;
    private final ProductCategory productCategory;
    private final LocalDate productExpiryDate;
    private final BigDecimal productDeliveryPrice;

    public Product(String productName, ProductCategory productCategory, LocalDate productExpiryDate, BigDecimal productDeliveryPrice) {
        this.productId = UUID.randomUUID();
        this.productName = productName;
        this.productCategory = productCategory;
        this.productExpiryDate = productExpiryDate;
        this.productDeliveryPrice = productDeliveryPrice;
    }
}
