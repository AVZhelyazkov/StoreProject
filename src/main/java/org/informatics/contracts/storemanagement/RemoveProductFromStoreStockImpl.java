package org.informatics.contracts.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.contracts.storemanagement.contracts.RemoveProductFromStoreStock;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;

@RequiredArgsConstructor
public class RemoveProductFromStoreStockImpl implements RemoveProductFromStoreStock {
    private final RetailStore store;
    @Override
    public void removeProductFromStoreStock(Product product) {
        store.getProductsInStock().remove(product);
    }
}
