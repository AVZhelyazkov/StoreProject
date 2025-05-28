package org.informatics.services.clientcheckout;

import lombok.RequiredArgsConstructor;
import org.informatics.enums.ProductCategory;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.services.clientcheckout.contracts.ClientProductSelection;
import org.informatics.services.products.ProductExpirationImpl;
import org.informatics.utils.ErrorMessagesManager;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;

@RequiredArgsConstructor
public class ClientProductSelectionImpl implements ClientProductSelection {
    final private RetailStore store;
    final private ProductExpirationImpl productExpirationService;

    @Override
    public Map.Entry<Product, BigDecimal> productSelectionByName(String name, BigDecimal productAmount) {
        if (productAmount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    ErrorMessagesManager.getErrorMessage("error.invalid_product_amount")
            );

        return store.getProductsInStock().entrySet().stream()
                .filter(pair -> pair.getKey().getProductName().contains(name))
                .filter(pair -> pair.getValue().compareTo(productAmount) >= 0)
                .filter(pair -> !productExpirationService.isExpired(pair.getKey()))
                .max(Comparator.comparing(keyPair -> keyPair.getKey().getProductExpiryDate()))
                .map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getKey(), productAmount))
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                ErrorMessagesManager.getErrorMessage("error.not_enough_stock")
                        )
                );
    }

    @Override
    public Map.Entry<Product, BigDecimal> productSelectionByCategory(ProductCategory selectedCategory, BigDecimal productAmount) {
        if (productAmount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    ErrorMessagesManager.getErrorMessage("error.invalid_product_amount")
            );

        return store.getProductsInStock().entrySet().stream()
                .filter(pair -> pair.getKey().getProductCategory().equals(selectedCategory))
                .filter(pair -> pair.getValue().compareTo(productAmount) >= 0)
                .filter(pair -> !productExpirationService.isExpired(pair.getKey()))
                .max(Comparator.comparing(keyPair -> keyPair.getKey().getProductExpiryDate()))
                .map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getKey(), productAmount))
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                ErrorMessagesManager.getErrorMessage("error.not_enough_stock")
                        )
                );
    }

}
