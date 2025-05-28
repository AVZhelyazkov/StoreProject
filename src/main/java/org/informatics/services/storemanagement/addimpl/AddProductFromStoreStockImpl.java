package org.informatics.services.storemanagement.addimpl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.services.storemanagement.contracts.AddProductToStoreStock;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class AddProductFromStoreStockImpl implements AddProductToStoreStock {
    private final RetailStore store;
    @Override
    public void addProductToStoreStock(Product product, BigDecimal productAmount) {
        store.getProductsInStock().put(product, productAmount);
    }
}
