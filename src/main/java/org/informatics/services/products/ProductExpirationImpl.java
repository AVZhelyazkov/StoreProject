package org.informatics.services.products;

import lombok.RequiredArgsConstructor;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.services.products.contracts.ProductExpiration;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ProductExpirationImpl implements ProductExpiration {
    private final RetailStore store;

    @Override
    public int removeExpiredProductsFromStore() {
        List<Product> expiredProducts =
                store.getProductsInStock().keySet().stream()
                .filter(product -> {
                    return product.getProductExpiryDate().isBefore(LocalDate.now());
                }).toList();

        expiredProducts.forEach(store.getProductsInStock().keySet()::remove);
        return expiredProducts.size();
    }

    @Override
    public boolean isExpired(Product product) {
        return product.getProductExpiryDate().isBefore(LocalDate.now());
    }

    @Override
    public void removeProductFromStore(Product product) {
        store.getProductsInStock().remove(product);
    }
}
