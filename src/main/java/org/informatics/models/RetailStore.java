package org.informatics.models;

import lombok.Getter;
import lombok.Setter;
import org.informatics.enums.ProductCategory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class RetailStore implements Serializable {
    private final String storeName;

    private final Map<Integer, StoreCheckoutMachine> checkouts;
    private final Set<StaffPersonnel> personnel;
    private final Map<Product, BigDecimal> productsInStock;
    private final Map<Product, BigDecimal> soldProducts;


    private final Map<ProductCategory, BigDecimal> categoryMarkupPercent;
    @Getter @Setter
    private BigDecimal productExpiryDiscountInPercent;
    @Getter @Setter
    private Integer productExpiryDiscountDays;

    public RetailStore(String storeName, int checkoutsAmount) {
        this.storeName = storeName;
        this.checkouts = new HashMap<>(checkoutsAmount);
        this.personnel = null;
        this.productsInStock = null;
        this.soldProducts = null;

        this.categoryMarkupPercent = new EnumMap<>(ProductCategory.class);

        for (int i = 0; i < checkoutsAmount; i++)
            checkouts.put(i, new StoreCheckoutMachine());
    }

    public RetailStore(String storeName, int checkoutsAmount, Set<StaffPersonnel> personnel, Map<Product, BigDecimal> productsInStock) {
        this.storeName = storeName;
        this.checkouts = new HashMap<>(checkoutsAmount);
        this.personnel = personnel;
        this.productsInStock = productsInStock;
        this.soldProducts = null;

        this.categoryMarkupPercent = new EnumMap<>(ProductCategory.class);

        for (int i = 0; i < checkoutsAmount; i++)
            checkouts.put(i, new StoreCheckoutMachine());
    }

    public BigDecimal getCategoryMarkupPercent(ProductCategory category) {
        return categoryMarkupPercent.get(category);
    }

    public void setCategoryMarkupPercent(ProductCategory category, BigDecimal categoryMarkupPercent) {
        this.categoryMarkupPercent.put(category, categoryMarkupPercent);
    }
}