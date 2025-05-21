package org.informatics.services.products;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.services.products.interfaces.ProductExpirationInterface;

@RequiredArgsConstructor
public class ProductExpirationService implements ProductExpirationInterface {
    private RetailStore store;
    @Override
    public int checkStoreForExpiredProducts() {
        return 0;
    }
}
