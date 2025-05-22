package org.informatics.services.products;

import lombok.RequiredArgsConstructor;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.services.products.interfaces.ProductExpirationInterface;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ProductExpirationService implements ProductExpirationInterface {
    private RetailStore store;

    @Override
    public int removeExpiredProductsFromStore() {
        List<Product> expiredProducts =
                store.getProductsInStock().stream()
                .filter(product -> {
                    return product.getProductExpiryDate().isBefore(LocalDate.now());
                }).toList();

        store.getProductsInStock().removeAll(expiredProducts);
        return expiredProducts.size();
    }
}
