package org.informatics.contracts.products.contracts;

import org.informatics.models.Product;

public interface ProductExpiration {
    int removeExpiredProductsFromStore();
    boolean isExpired(Product product);
    void removeProductFromStore(Product product);
}
