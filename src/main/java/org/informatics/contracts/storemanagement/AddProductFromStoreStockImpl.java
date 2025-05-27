package org.informatics.contracts.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.contracts.storemanagement.contracts.AddProductToStoreStock;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class AddProductFromStoreStockImpl implements AddProductToStoreStock {
    private final RetailStore store;
    @Override
    public void addProductToStoreStock(Product product, BigDecimal productAmount) {
        store.getProductsInStock().put(product, productAmount);
    }
}
