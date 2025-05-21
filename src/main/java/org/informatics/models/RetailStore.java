package org.informatics.models;

import lombok.Getter;
import org.informatics.enums.ProductCategory;

import java.util.List;
import java.util.Map;

@Getter
public class RetailStore {
    private StoreCheckoutMachine[] checkouts;
    private List<StaffPersonnel> personnel;
    private List<Product> productsInStock;
    private List<Product> soldProducts;

    private Map<ProductCategory, Integer> discountPercent;

    public RetailStore(int checkoutsAmount) {
        this.checkouts = new StoreCheckoutMachine[checkoutsAmount];
        this.personnel = null;
        this.productsInStock = null;
        this.soldProducts = null;
    }

    public RetailStore(int checkoutsAmount, List<StaffPersonnel> personnel, List<Product> productsInStock) {
        this.checkouts = new StoreCheckoutMachine[checkoutsAmount];
        this.personnel = personnel;
        this.productsInStock = productsInStock;
        this.soldProducts = null;
    }
}
