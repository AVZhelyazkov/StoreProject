package org.informatics.models;

import lombok.Getter;
import org.informatics.enums.ProductCategory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Getter
public class RetailStore implements Serializable {
    private final String storeName;

    private final List<StoreCheckoutMachine> checkouts;
    private final Set<StaffPersonnel> personnel;
    private final Map<Product, BigDecimal> productsInStock;
    private final Map<Product, BigDecimal> soldProducts;

    private Map<ProductCategory, BigDecimal> categoryMarkupPercent;
    private BigDecimal productExpiryDiscountInPercent;
    private Integer productExpiryDiscountDays;

    public RetailStore(String storeName, int checkoutsAmount) {
        this.storeName = storeName;
        this.checkouts = new ArrayList<>(checkoutsAmount);
        this.personnel = null;
        this.productsInStock = null;
        this.soldProducts = null;
    }

    public RetailStore(String storeName, int checkoutsAmount, Set<StaffPersonnel> personnel, Map<Product, BigDecimal> productsInStock) {
        this.storeName = storeName;
        this.checkouts = new ArrayList<>(checkoutsAmount);
        this.personnel = personnel;
        this.productsInStock = productsInStock;
        this.soldProducts = null;
    }


}