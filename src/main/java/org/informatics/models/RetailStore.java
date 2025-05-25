package org.informatics.models;

import lombok.Getter;
import org.informatics.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.*;

@Getter
public class RetailStore {
    private final List<StoreCheckoutMachine> checkouts;
    private final Set<StaffPersonnel> personnel;
    private final Map<Product, BigDecimal> productsInStock;
    private final Map<Product, BigDecimal> soldProducts;

    private Map<ProductCategory, BigDecimal> categoryMarkupPercent;
    private BigDecimal productExpiryDiscountInPercent;
    private Integer productExpiryDiscountDays;

    public RetailStore(int checkoutsAmount) {
        this.checkouts = new ArrayList<>(checkoutsAmount);
        this.personnel = null;
        this.productsInStock = null;
        this.soldProducts = null;
    }

    public RetailStore(int checkoutsAmount, Set<StaffPersonnel> personnel, Map<Product, BigDecimal> productsInStock) {
        this.checkouts = new ArrayList<>(checkoutsAmount);
        this.personnel = personnel;
        this.productsInStock = productsInStock;
        this.soldProducts = null;
    }
}