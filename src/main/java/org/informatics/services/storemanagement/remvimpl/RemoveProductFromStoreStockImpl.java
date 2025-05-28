package org.informatics.services.storemanagement.remvimpl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.services.storemanagement.contracts.RemoveProductFromStoreStock;

@RequiredArgsConstructor
public class RemoveProductFromStoreStockImpl implements RemoveProductFromStoreStock {
    private final RetailStore store;
    @Override
    public void removeProductFromStoreStock(Product product) {
        store.getProductsInStock().remove(product);
    }
}
